package br.com.caroll.sentimento;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class LimpadorTexto {

	public List<String> ajustaTweets(List<Status> tweets) {
		List<String> tweetsLimpos = new ArrayList<String>();

		for (Status tweet : tweets) {
			if (!tweet.isRetweet()) {
				String tw = tweet.getText();
				tw = tw.toLowerCase();
				tw = retiraLinks(tw);
				tw = corrigeString(tw);
				tw = retiraAcentuacao(tw);
				tweetsLimpos.add(tw);
			}
		}

		return tweetsLimpos;
	}
	
	public void limpaLista(List<String> lista){
	  for (String st : lista){
	    st = corrigeString(st);
	    st = retiraAcentuacao(st);
	  }
	  
	}

	public String retiraLinks(String sentenca) {
		if (sentenca.contains("http://")) {
			int i = sentenca.lastIndexOf("http://");
			int j;
			for (j = i; j < sentenca.length(); j++) {
				String s = String.valueOf(sentenca.charAt(j));
				if (s.equals(" "))
					break;
			}
			String s = sentenca.substring(i, j);
			sentenca = sentenca.replace(s, "");
		}
		if (sentenca.contains("https://")) {
			int i = sentenca.lastIndexOf("https://");
			int j;
			for (j = i; j < sentenca.length(); j++) {
				String s = String.valueOf(sentenca.charAt(j));
				if (s.equals(" "))
					break;
			}
			String s = sentenca.substring(i, j);
			sentenca = sentenca.replace(s, "");

		}
		if (sentenca.contains("migre.me")) {
			int i = sentenca.lastIndexOf("migre.me");
			int j;
			for (j = i; j < sentenca.length(); j++) {
				String s = String.valueOf(sentenca.charAt(j));
				if (s.equals(" "))
					break;
			}
			String s = sentenca.substring(i, j);
			sentenca = sentenca.replace(s, "");

		}

		return sentenca;
	}

	public String corrigeString(String sentenca) {
		sentenca = sentenca.replace(".", "");
		sentenca = sentenca.replace(",", "");
		sentenca = sentenca.replace(":", "");
		sentenca = sentenca.replace(";", "");
		sentenca = sentenca.replace("!", "");
		sentenca = sentenca.replace("$", "");
		sentenca = sentenca.replace("%", "");
		sentenca = sentenca.replace("&", "");
		sentenca = sentenca.replace("*", "");
		sentenca = sentenca.replace("(", "");
		sentenca = sentenca.replace(")", "");
		sentenca = sentenca.replace("=", "");
		sentenca = sentenca.replace("+", "");
		sentenca = sentenca.replace("�", "");
		sentenca = sentenca.replace("/", "");
		sentenca = sentenca.replace("?", "");
		sentenca = sentenca.replace("[", "");
		sentenca = sentenca.replace("{", "");
		sentenca = sentenca.replace("~", "");
		sentenca = sentenca.replace("^", "");
		sentenca = sentenca.replace("]", "");
		sentenca = sentenca.replace("}", "");
		sentenca = sentenca.replace("\\", "");
		sentenca = sentenca.replace("|", "");
		sentenca = sentenca.replace("<", "");
		sentenca = sentenca.replace(">", "");
		sentenca = sentenca.replace("-", "");
		sentenca = sentenca.replace("_", "");
		sentenca = sentenca.replace("RT", "");
		sentenca = sentenca.replace("\"", "");
		sentenca = sentenca.replace("'", "");

		return sentenca;
	}

	public String retiraAcentuacao(String sentenca) {

		sentenca = sentenca.replace("�", "c");
		sentenca = sentenca.replace("�", "a");
		sentenca = sentenca.replace("�", "a");
		sentenca = sentenca.replace("�", "a");
		sentenca = sentenca.replace("�", "a");

		sentenca = sentenca.replace("�", "e");
		sentenca = sentenca.replace("�", "e");
		sentenca = sentenca.replace("�", "e");

		sentenca = sentenca.replace("�", "i");
		sentenca = sentenca.replace("�", "i");
		sentenca = sentenca.replace("�", "i");

		sentenca = sentenca.replace("�", "o");
		sentenca = sentenca.replace("�", "o");
		sentenca = sentenca.replace("�", "o");
		sentenca = sentenca.replace("�", "o");

		sentenca = sentenca.replace("�", "u");
		sentenca = sentenca.replace("�", "u");
		sentenca = sentenca.replace("�", "u");

		return sentenca;
	}

}
