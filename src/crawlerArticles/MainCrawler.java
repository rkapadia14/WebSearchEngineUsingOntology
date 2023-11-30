package crawlerArticles;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDF;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AntColonyOptimization.AntColonyOptimization;
import KeyphraseExtraction.KeyphraseExtractor;
import cosine_similarity.cosine_similarity;


public class MainCrawler {
	private static final String NAMESPACE = "https://en.wikipedia.org/wiki/";
	//static OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	public static void main(String args[]) throws UnsupportedEncodingException {
		ArrayList<Articles> Articles = new ArrayList<Articles>();
		String SearchTerm = "Animals";
		long startTimeForProject = System.nanoTime();

		Articles = ArticleNameCrawler(SearchTerm, Articles);
		try {
			Articles = ArticleContentCrawler(Articles);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Articles = KeyphraseExtractor.KeyphraseExtractor(Articles);

        
		// /////////////////-Articles Iterator-///////////////////////////////
		double[][] similarityMatrix = new double[Articles.size()][Articles.size()];

		long startTime = System.nanoTime();
		for (int i = 0; i < Articles.size(); i++) {
			Articles art1 = Articles.get(i);
			for (int x = 0; x < Articles.size(); x++) {
				Articles art2 = Articles.get(x);
				similarityMatrix[i][x] = cosine_similarity.cosine_similarity(art1.getContent(), art2.getContent());
			}
		}
		
		
		long endTime = System.nanoTime();
		long duration = endTime - startTime;	        
	       
		
		
		System.out.println("Runtime for Cosine Similarity::: " + duration + " nano seconds");
		System.out.println("Start time for Cosine Similarity::: " + startTime + " nano seconds");
		System.out.println("End time for Cosine Similarity::: " + endTime + " nano seconds");
		
		/*
		System.out.println("Similarity Matrix");
		for (int i = 0; i < Articles.size(); i++) {
			for (int x = 0; x < Articles.size(); x++) {
				System.out.print("\t" + similarityMatrix[i][x]);
			}
			System.out.println("");
		}
		*/
		
		AntColonyOptimization.main(similarityMatrix);
		
		ontologyCreation(SearchTerm, Articles);
		
		long endTimeForProject = System.nanoTime();
		long durationForProject = endTimeForProject - startTimeForProject;
		System.out.println("Runtime for Project::: " + durationForProject + " nano seconds");
		System.out.println("Start time for Project::: " + startTimeForProject + " nano seconds");
		System.out.println("End time for Project::: " + endTimeForProject + " nano seconds");
		
	}
	
	public static void ontologyCreation(String SearchTerm, ArrayList<Articles> Articles)
	{
		
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		
		//Defining the root class as the search term
		OntClass rootClass = model.createClass(NAMESPACE + SearchTerm.replaceAll(" ", ""));
		
		for (int i = 0; i < Articles.size(); i++) {
			Articles art1 = Articles.get(i);
			 OntClass subClass = model.createClass(NAMESPACE + art1.getTitle().replaceAll(" ", "_"));
			 	
			ArrayList<Articles> articlesRelated = new ArrayList<Articles>();
			articlesRelated = ArticleNameCrawler(art1.getTitle(), articlesRelated);
			for (int k = 1; k < 3; k++) {
				OntClass nestedSubClass = model.createClass(NAMESPACE + articlesRelated.get(k).getTitle()
					    .replaceAll("\\(", "_")
					    .replaceAll("\\)", "_")
					    .replaceAll(" ", "")
					    .replace("disambiguation", ""));
				
				// Create an individual of the class
				Individual individual = model.createIndividual(NAMESPACE + "IndividualURI", nestedSubClass);
				
				String relatedArticleName = articlesRelated.get(k).getTitle()
					    .replaceAll("\\(", "_")
					    .replaceAll("\\)", "_")
					    .replace("disambiguation", "");
				
				articlesRelated.get(k).setTitle(relatedArticleName);
				
				individual.addProperty(RDF.type, articlesRelated.get(k).getTitle());
				// Define a property
				DatatypeProperty hasNameProperty = model.getDatatypeProperty(NAMESPACE + "hasName");

				subClass.addSubClass(nestedSubClass);
			}
			rootClass.addSubClass(subClass);
			 // Save the ontology as an OWL file (RDF/XML format)
	        try (FileOutputStream fileOutputStream = new FileOutputStream("nested_ontology.owl")) {
	            model.write(fileOutputStream, "RDF/XML");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	

		

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Articles> ArticleNameCrawler(String SearchTerm, ArrayList<Articles> Articles) {
		String theUrl = "https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + SearchTerm
				+ "&format=json&srprop=snippet&srlimit=20";
		theUrl = theUrl.replaceAll("\\s+", "%20");
		String ArticlesName = getUrlContents(theUrl);
		try {
			JSONObject json = new JSONObject(ArticlesName.toString());
			json = json.getJSONObject("query");
			JSONArray search = json.getJSONArray("search");
			for (int i = 0; i < search.length() && search.length()>2; i++) {
				json = (JSONObject) search.get(i);
				Articles.add(new Articles(json.get("title").toString().trim(), "", ""));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Articles;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Articles> ArticleContentCrawler(ArrayList<Articles> Articles) throws UnsupportedEncodingException {
		String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
		for (Articles art : Articles) {
			String content = ArticleContentParser(url +  URLEncoder.encode(art.getTitle(), "UTF-8"));
			content = content.replaceAll("[&\\/\\\\#,+()$~%.'\":*?<>{}^@!]à¥¤'â€™â€ â€œé", "");
			art.setContent(content);
			art.setOG_content(content);
		}
		return Articles;
	}

	public static String ArticleContentParser(String theUrl) {
		theUrl = theUrl.replaceAll("\\s+", "%20");
		String ArticlesContent = getUrlContents(theUrl);
		JSONObject json;
		try {
			json = new JSONObject(ArticlesContent.toString());
			json = json.getJSONObject("query");
			// json = json.getJSONObject("pages");
			json = json.getJSONObject("pages");
			Iterator keys = json.keys();
			while (keys.hasNext()) {
				// loop to get the dynamic key
				String currentDynamicKey = (String) keys.next();
				// get the value of the dynamic key
				JSONObject currentDynamicValue = json.getJSONObject(currentDynamicKey);
				// do something here with the value...
				return currentDynamicValue.get("extract").toString().trim();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static String getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();
		// many of these calls can throw exceptions, so i've just
		// wrapped them all in one try/catch statement.
		try {
			// create a url object
			URL url = new URL(theUrl);
			// create a urlconnection object
			URLConnection urlConnection = url.openConnection();
			// wrap the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// read from the urlconnection via the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
