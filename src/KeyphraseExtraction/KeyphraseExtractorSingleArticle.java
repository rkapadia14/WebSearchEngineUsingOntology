package KeyphraseExtraction;

import java.util.Arrays;
import java.util.List;

import opennlp.tools.stemmer.*;

import crawlerArticles.Articles;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;

public class KeyphraseExtractorSingleArticle {
	
	public static List<String> KeyphraseExtractorSingleArticle(Articles article) {
		Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        //PorterStemmer stemmer = new PorterStemmer();
		String content = StopwordsLoaderAndRemoval.StopwordsRemoval(article.getOG_content());
        String[] tokens = tokenizer.tokenize(article.getOG_content());
       // for (int i = 0; i < tokens.length; i++) {
         //   tokens[i] = stemmer.stem(tokens[i]);
        //}

        return Arrays.asList(tokens);
		
	}

}
