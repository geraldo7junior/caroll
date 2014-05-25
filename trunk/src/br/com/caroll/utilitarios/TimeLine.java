	package br.com.caroll.utilitarios;
	import java.util.List;

	import twitter4j.HashtagEntity;
	import twitter4j.Status;
	import twitter4j.Twitter;
	import twitter4j.TwitterException;
	import twitter4j.TwitterFactory;

	public class TimeLine {
		
		public void capturarTimeLine() throws TwitterException {
			 // The factory instance is re-useable and thread safe.
		    Twitter twitter = TwitterFactory.getSingleton();
		    List<Status> statuses = twitter.getHomeTimeline();
		    System.out.println("Showing home timeline.");
		    	
		    HashtagEntity[] hashtags = null;
		    
		    for (Status status : statuses) {
		        System.out.println("nome do usuário: "+status.getUser().getName());
		        System.out.println("texto do tweet: "+status.getText());
		        System.out.println("Data do tweet: "+status.getCreatedAt());
		        hashtags = status.getHashtagEntities();
		        for (int i = 0; i < hashtags.length; i++) {
					System.out.println("hashtags do tweet "+i+": "+hashtags[i].getText());
				}
		        System.out.println("Tweet favoritado: "+status.isFavorited());
		        System.out.println("geolocalização ativada:"+status.getUser().isGeoEnabled());
		        System.out.println("Local: "+status.getUser().getLocation());
		        System.out.println("Time zone: "+status.getUser().getTimeZone());
		        System.out.println("Quantidade de retweets: "+status.getRetweetCount());
		        System.out.println("Informações do usuário: "+status.getUser().getDescription());
		        //System.out.println("Lugar: "+status.getPlace().getGeometryCoordinates().toString());

		        System.out.println("Possivelmente sensível: "+status.isPossiblySensitive());
		        System.out.println("Link no perfil: "+status.getSource());
		        System.out.println("ScreenName: "+status.getUser().getScreenName());
		        System.out.println("Status: "+status.getUser().getStatus());
		        System.out.println("Idioma : "+status.getLang()+"\n");
		        
		      
		    }	
			
		}

	}



