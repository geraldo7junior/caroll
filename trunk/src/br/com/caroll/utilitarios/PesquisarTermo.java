package br.com.caroll.utilitarios;

import java.sql.SQLException;
import java.util.List;

import br.com.analisesentimento.classes.Tweet;
import br.com.caroll.analisesentimento.Classificador;
import br.com.caroll.analisesentimento.Sentimento;
import br.com.caroll.dao.FavoritoDAO;
import br.com.caroll.dao.HashtagDAO;
import br.com.caroll.dao.HashtagTweetDAO;
import br.com.caroll.dao.RespostaDAO;
import br.com.caroll.dao.RetweetDAO;
import br.com.caroll.dao.TweetDAO;
import br.com.caroll.dao.UsuarioDAO;
import br.com.caroll.vo.FavoritoVO;
import br.com.caroll.vo.HashtagTweetVO;
import br.com.caroll.vo.HashtagVO;
import br.com.caroll.vo.RespostaVO;
import br.com.caroll.vo.RetweetVO;
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

	 public void pesquisarTermo (String termo) throws TwitterException, ClassNotFoundException, SQLException {	
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
			       /* for (int i = 0; i < hashtags.length; i++) {
						System.out.println("hashtags do tweet "+i+": "+hashtags[i].getText());
					}*/
			        System.out.println("Tweet favoritado: "+status.isFavorited());
			        System.out.println("geolocalização ativada:"+status.getUser().isGeoEnabled());
			        System.out.println("Local: "+status.getUser().getLocation());
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
				       
			        UsuarioVO usuarioVO = new UsuarioVO();
			        UsuarioDAO usuarioDAO = new UsuarioDAO();
					TweetVO tweetVO = new TweetVO();
					TweetDAO tweetDAO = new TweetDAO();
					
					HashtagTweetVO hashtagTweetVO = new HashtagTweetVO();
					HashtagTweetDAO hashtagTweetDAO = new HashtagTweetDAO();
					
					HashtagVO hashTagVO = new HashtagVO();
					HashtagDAO hashtagDAO = new HashtagDAO();
					 
					usuarioVO.setNomeUsuario(status.getUser().getName().toString());
					usuarioVO.setLoginUsuario(status.getUser().getScreenName());
					
					usuarioDAO.inserir(usuarioVO);
					
					//System.out.println("Usuario inserido");
					List <UsuarioVO> listaUsuario =  usuarioDAO.listarUsuario(usuarioVO);
			
					if (listaUsuario.isEmpty()) {
						//System.out.println("A lista de usuários está vazia");
					}else
						//System.out.println("A lista de usuários não está vazia");
					tweetVO.setPostTweet(status.getText().toString());
					
					String dia = String.valueOf(status.getCreatedAt().getDate());
					String mes = String.valueOf(status.getCreatedAt().getMonth()+1);
					String ano = String.valueOf((status.getCreatedAt().getYear() + 1900));
					String dataTweet = dia+"/"+mes+"/"+ano;
				
					tweetVO.setDataTweet(dataTweet);
					tweetVO.setUsuarioFk(listaUsuario.get(0).getIdUsuario());
					
					List <TweetVO> listaTweet2 = tweetDAO.listarTweet(tweetVO);
					
				
					tweetDAO.inserir(tweetVO);
					//System.out.println("Tweet inserido com sucesso");
		
					if (status.isFavorited()) { 
						FavoritoDAO favoritoDao = new FavoritoDAO();
						FavoritoVO favoritoVo = new FavoritoVO();
						favoritoVo.setUsuarioFk(listaUsuario.get(0).getIdUsuario());
						favoritoVo.setTweetFk(listaTweet2.get(0).getIdTweet());
						
						favoritoDao.inserir(favoritoVo);
					}
					
					
					if (status.isRetweetedByMe() == true) {
						RespostaDAO respostaDao = new RespostaDAO();
						RespostaVO respostaVo = new RespostaVO();
						respostaVo.setTweetFk(listaTweet2.get(0).getIdTweet());
						respostaDao.inserir(respostaVo);
					}
					
					System.out.println("passou do retweet");
					
					RetweetDAO retweetDao = new RetweetDAO ();
					RetweetVO retweetVo = new RetweetVO();
					
				/*	if (status.getRetweetCount() != 0) {
						//retweetVo.setTweetFk(listaTweet2.get(0).getIdTweet());
						System.out.println("ta passando do lista");
						System.out.println("ta entrando no if");
						int i = 0;
						
							//retweetDao.inserir(retweetVo);
						}*/
						
					
						
					
			    	if (hashtags.length != 0) {
			    		
			    		System.out.println("Esse tweet possui hashtags!");
			    		for (int i = 0; i < hashtags.length; i++) {
			    			
			    			hashTagVO.setDadoHashtag(hashtags[i].getText());
			    			hashtagDAO.inserir(hashTagVO);
			    			//System.out.println("Hashtag inserida com sucesso");
			    			
			    			List <HashtagVO> listaHashtag = hashtagDAO.listarHashTag(hashTagVO);
			    			List <TweetVO> listaTweet = tweetDAO.listarTweet(tweetVO);
			    			
			    			if (listaHashtag.isEmpty()) {
			    				//System.out.println("Lista hashtag está vazia");
			    			}else
			    				//System.out.println("Lista hashtag não está vazia");
			    			
			    			if (listaTweet.isEmpty()) {
			    				//System.out.println("Lista tweet está vazia");
			    			}else
			    				//System.out.println("Lista tweet não está vazia");
			    			
			    			
			    			
			    			hashtagTweetVO.setHashtagFk(listaHashtag.get(0).getIdHashtag());
			    			hashtagTweetVO.setTweetFk(listaTweet.get(0).getIdTweet());
			    			
			    			hashtagTweetDAO.inserir(hashtagTweetVO);
			    		//	System.out.println("HashtagTweet inserida com sucesso");
			    			
			    		}
			    
			    	}else {
			    		
			    		System.out.println("Esse tweet não possui hashtags");
			    	}
			    	System.out.println("Informações pesquisadas e inseridas no banco com sucesso \n");
			    	
			    	/*Sentimento sentimento = new Sentimento ();
			    	
			    	Classificador classificador = new Classificador();
			    	
			      classificador.vocabulario.add("felicidade");
			    	
			       String [] matriz = classificador.getSentencaAnalisadaBayes("felicidade");
			    	
			    	//System.out.println("texto: "+matriz[0]);
			    	
			    	//System.out.println("classificacao: "+matriz[1]+"\n");*/

			  		
			    }
			    
			    
			
		}
}
