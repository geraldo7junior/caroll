package br.com.caroll.utilitarios;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.analisesentimento.classes.Tweet;
import br.com.caroll.dao.FavoritoDAO;
import br.com.caroll.dao.HashtagDAO;
import br.com.caroll.dao.HashtagTweetDAO;
import br.com.caroll.dao.LocalDAO;
import br.com.caroll.dao.SentimentoDAO;
import br.com.caroll.dao.TweetDAO;
import br.com.caroll.dao.UsuarioDAO;
import br.com.caroll.sentimento.Classificador;
import br.com.caroll.sentimento.FerramentaTwitter;
import br.com.caroll.sentimento.Programa;
import br.com.caroll.vo.FavoritoVO;
import br.com.caroll.vo.HashtagTweetVO;
import br.com.caroll.vo.HashtagVO;
import br.com.caroll.vo.LocalVO;
import br.com.caroll.vo.SentimentoVO;
import br.com.caroll.vo.TweetVO;
import br.com.caroll.vo.UsuarioVO;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;

public class PesquisarTermo {

	 public void pesquisarTermo (String termo) throws TwitterException, ClassNotFoundException, SQLException, FileNotFoundException {	
		 // The factory instance is re-useable and thread safe.
			 Twitter twitter = TwitterFactory.getSingleton();
			    Query query = new Query(termo);
			    QueryResult result = twitter.search(query);
			    HashtagEntity[] hashtags = null;
			    URLEntity [] urlEntity = null;
			    List<Status> tweets = new ArrayList<Status>();
			    FerramentaTwitter ferramentaTwitter = new FerramentaTwitter();
			    
			    for (Status status : result.getTweets()) {
			    	
			    	if (status.getLang().equals("pt")) {
			    	//prints no terminal
			        //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			        System.out.println("nome do usuário: "+status.getUser().getName());
			        System.out.println("texto do tweet: "+status.getText());
			        System.out.println("Data do tweet: "+status.getCreatedAt());
			        hashtags = status.getHashtagEntities();
			        System.out.println("Tweet favoritado: "+status.isFavorited());
			        System.out.println("geolocalização ativada:"+status.getUser().isGeoEnabled());
			      //  System.out.println("latitude: "+status.getGeoLocation().getLatitude());
			      //  System.out.println("latitude: "+status.getGeoLocation().getLongitude());
			        System.out.println("Place: "+status.getPlace());
			       // System.out.println("Local: "+status.getUser().getLocation());
			        System.out.println("Time zone: "+status.getUser().getTimeZone());
			        System.out.println("Quantidade de retweets: "+status.getRetweetCount());
			        System.out.println("Informações do usuário: "+status.getUser().getDescription());
			        //System.out.println("Lugar: "+status.getPlace().getGeometryCoordinates().toString());
			        System.out.println("resposta: "+status.isRetweetedByMe());
			        
			        System.out.println("usuário criado em: "+status.getUser().getCreatedAt());
			        System.out.println("Possivelmente sensível: "+status.isPossiblySensitive());
			        System.out.println("Link no perfil: "+status.getSource());
			        System.out.println("ScreenName: "+status.getUser().getScreenName());
			        System.out.println("Status: "+status.getUser().getStatus());
			        System.out.println("Idioma : "+status.getLang());
			        System.out.println("ScreenName:"+status.getInReplyToScreenName());
			        System.out.println("Sensível: "+status.isPossiblySensitive());
				     System.out.println("Source "+status.getSource()+"\n");
			        /*###################################################################*/
			        
				     
					
			        //inserir usuário
			        
			        //objeto usuário
			        UsuarioVO usuarioVO = new UsuarioVO();
			        
			        //inserir no objeto usuarioVO o nome do usuário
			        usuarioVO.setNomeUsuario(status.getUser().getName().toString());
			        
			        //inserir no objeto o login do usuário
					usuarioVO.setLoginUsuario(status.getUser().getScreenName()); 
			        
					//classe para manipulação de dados 
					//e persistência de usuário no banco
			        UsuarioDAO usuarioDAO = new UsuarioDAO();
			        
			        //salvar usuário no banco
					usuarioDAO.inserir(usuarioVO);
					


			        //objeto Tweet
					TweetVO tweetVO = new TweetVO();
					//Inserir no objeto tweetVO o texto do tweet
					tweetVO.setPostTweet(status.getText().toString());
					
					//captura do dia do tweet
					String dia = String.valueOf(status.getCreatedAt().getDate());
					
					//captura do mes do tweet
					String mes = String.valueOf(status.getCreatedAt().getMonth()+1);
					
					//captura do ano do tweet
					String ano = String.valueOf((status.getCreatedAt().getYear() + 1900));
					
					//formatação da data
					String dataTweet = dia+"/"+mes+"/"+ano;
					
					//Inserir no objeto tweetVO a data formatada do tweet
					tweetVO.setDataTweet(dataTweet);
					
					
					//tipo de tweet
					if (status.isRetweet() == true) {
						tweetVO.setTipoTweet("retweet");
					}else if (status.isRetweeted() == true) {
						tweetVO.setTipoTweet("resposta");
					}else {
						tweetVO.setTipoTweet("tweet");
					}
					
					
					//lista para capturar as informações do autor do tweet
					List <UsuarioVO> listaUsuario =  usuarioDAO.listarUsuario(usuarioVO);
					
					//captura do id do autor do tweet a partir da lista
					tweetVO.setUsuarioFk(listaUsuario.get(0).getIdUsuario());
					
					//análise de sentimento de tweet
					
			
					
					Classificador classificador = new Classificador();
					
					String [] teste = classificador.getSentencaAnalisadaScore(status.getText().toString());
					
					if (teste [1] == "neutro") {
						tweetVO.setSentimentoFk(1);
					}else if(teste[1] == "negativo") {
						tweetVO.setSentimentoFk(2);
					}else if (teste[1] == "positivo") {
						tweetVO.setSentimentoFk(3);

					}

					TweetDAO tweetDAO = new TweetDAO();
					
					//salvar tweet no banco
					tweetDAO.inserir(tweetVO);
					
					
				
					
					//lista para receber as informações do tweet
					List <TweetVO> listaTweet2 = tweetDAO.listarTweet(tweetVO);
							
					//se o tweet foi favoritado insira na tabela favoritos
					if (status.isFavorited()) { 
				
						//classe para manipulação de dados 
						//e persistência de favorito no banco
						FavoritoDAO favoritoDao = new FavoritoDAO();
						
				        //objeto FavoritoVO
						FavoritoVO favoritoVo = new FavoritoVO();
						
						//captura o id do usuário a partir da lista de usuários
						favoritoVo.setUsuarioFk(listaUsuario.get(0).getIdUsuario());
						//captura o id do tweet a partir da lista de tweets
						favoritoVo.setTweetFk(listaTweet2.get(0).getIdTweet());
						
						//insere as informações na tabela favorito
						favoritoDao.inserir(favoritoVo);
					}
					
					LocalVO localVO = new LocalVO();
					LocalDAO localDAO = new LocalDAO();
					
					if (status.getPlace() == null) {
						
						localVO.setLatitudeLocal(0);
						localVO.setLongitudeLocal(0);
						localVO.setCidadeLocal(null);
						localVO.setEstadoLocal(null);
						localVO.setPaisLocal(null);
						localVO.setTweetFk(listaTweet2.get(0).getIdTweet());
						
						localDAO.inserir(localVO);
						
					}
					
					
					//objeto hashTagVO
					HashtagVO hashTagVO = new HashtagVO();
					
					//classe para manipulação de dados 
					//e persistência de favorito no banco
					HashtagDAO hashtagDAO = new HashtagDAO();
					
					//objeto hashtagVO
					HashtagTweetVO hashtagTweetVO = new HashtagTweetVO();
					
					//classe para manipulação de dados 
					//e persistência de favorito no banco
					HashtagTweetDAO hashtagTweetDAO = new HashtagTweetDAO();
					
					
						//verificação se o tweet possui hashtags
			    		if (hashtags.length != 0) {
			    		
			
			    		for (int i = 0; i < hashtags.length; i++) {
			    			
			    			hashTagVO.setDadoHashtag(hashtags[i].getText());
			    			hashtagDAO.inserir(hashTagVO);
			    			
							//lista para receber as informações da hashtag
			    			List <HashtagVO> listaHashtag = hashtagDAO.listarHashTag(hashTagVO);
			    			//lista para receber as informações do tweet
			    			List <TweetVO> listaTweet = tweetDAO.listarTweet(tweetVO);
			    			
			    			hashtagTweetVO.setHashtagFk(listaHashtag.get(0).getIdHashtag());
			    			hashtagTweetVO.setTweetFk(listaTweet.get(0).getIdTweet());
			    			
			    			//inserir informações na tabela hashtag tweet
			    			hashtagTweetDAO.inserir(hashtagTweetVO);
			    			
			    		}
			    
			    	}else {
			    		
			    		System.out.println("Esse tweet não possui hashtags");
			    	}
			    	System.out.println("Informações pesquisadas e inseridas no banco com sucesso \n");
			    	
			    	
			    
			    	
			    /*	Sentimento sentimento = new Sentimento ();
			    	
			    	Classificador classificador = new Classificador();
			    	
			     // classificador.vocabulario.add("felicidade");
			    	
			       String [] matriz = classificador.getSentencaAnalisadaBayes(status.getText());
			    	
			    	System.out.println("texto do tweet: "+matriz[0]);
			    	
			    	System.out.println("classificacao: "+matriz[1]+"\n");

			  		*/
					//Classificador classificador = new Classificador();
					
					///classificador.getSentencaAnalisadaScore("ódio");
			    }	    
			   
			    }
			
		}
}
