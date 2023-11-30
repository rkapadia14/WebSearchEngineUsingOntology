package cosine_similarity;

import java.text.DecimalFormat;

import info.debatty.java.stringsimilarity.Cosine;

public class cosine_similarity {

	public static void main(String[] args) {
		cosine_similarity("stupid", "dumb");
	}

	public static double cosine_similarity(String s1, String s2) {
		Cosine cos = new Cosine(2);
		DecimalFormat formatter = new DecimalFormat("#0.00");
		return Double.parseDouble(formatter.format(cos.similarity(s1, s2)));
	}
}
