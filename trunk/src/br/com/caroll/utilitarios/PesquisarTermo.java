package br.com.caroll.utilitarios;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;

public class PesquisarTermo {

	 public void pesquisarTermo (String termo) throws TwitterException {	
		 // The factory instance is re-useable and thread safe.
			 Twitter twitter = TwitterFactory.getSingleton();
			    Query query = new Query(termo);
			    QueryResult result = twitter.search(query);
			    HashtagEntity[] hashtags = null;
			    URLEntity [] urlEntity = null;

			    
			    for (Status status : result.getTweets()) {
			        //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			    	 System.out.println("nome do usuário: "+status.getUser().getName());
				        System.out.println("texto do tweet: "+status.getText());
				        System.out.println("Data do tweet: "+status.getCreatedAt());
				        hashtags = status.getHashtagEntities();
				        System.out.println("Hash tags do tweet: ");
				        for (int i = 0; i < hashtags.length; i++) {
							System.out.println("hashtags do tweet: "+hashtags[i].getText());
						}
				        System.out.println("Tweet favoritado: "+status.isFavorited());
				        System.out.println("geolocalização ativada:"+status.getUser().isGeoEnabled());
				        System.out.println("Local: "+status.getUser().getLocation());
				        System.out.println("Time zone: "+status.getUser().getTimeZone());
				        System.out.println("Informações do usuário: "+status.getUser().getDescription());
				        System.out.println("Possivelmente sensível: "+status.isPossiblySensitive());
				        
				      /*  for (int i = 0; i < urlEntity.length; i++) {
				        	System.out.println("entidade url: "+urlEntity[i].getText());
				        }*/
				        System.out.println("Idioma : "+status.getLang()+"\n");
			    }
		}
}
