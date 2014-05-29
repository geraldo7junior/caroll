package br.com.caroll.analisesentimento;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;
import br.com.analisesentimento.classes.Tweet;
import br.com.analisesentimento.classificador.Classificador;
import br.com.analisesentimento.twitterUtils.ConverteStatusParaTweet;
import br.com.analisesentimento.twitterUtils.FerramentaTwitter;

public class Sentimento {
	
	 private Classificador classificador = new Classificador();
	  
	  private List<Tweet> tweets;
	  private List<Tweet> tweetsSelecionados;
	  private FerramentaTwitter twitter;
	  private int classificacao;
	  
	  
	  public List <Tweet> buscarEClassificar (String termoBusca) {
		   // List<Status> tempTweets = new ArrayList<Status>();
		   
		        Tweet tweet = new Tweet();
		        tweet.setClassificacao(classificador.classificaBayesiano(termoBusca));
		        tweets.add(tweet);
		    return tweets;
		  }

}
