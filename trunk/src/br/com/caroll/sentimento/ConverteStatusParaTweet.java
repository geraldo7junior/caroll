package br.com.caroll.sentimento;
import java.util.Calendar;

import twitter4j.Status;
import twitter4j.User;

/**
 * @author richard.santana
 * 
 */
public class ConverteStatusParaTweet {

	public Tweet converte(Status status) {
		Tweet t = new Tweet();
		Calendar cal = Calendar.getInstance();
		cal.setTime(status.getCreatedAt());
		t.setDataCriacao(cal);
		t.setEhRetweet(status.isRetweet());
		t.setFavoritos(0);
		t.setId(status.getId());
		User user = status.getUser();
		t.setIdUser(user.getId());
 		t.setNomeUser(user.getScreenName());
		Long retweets = new Long(status.getRetweetCount());
		t.setRetweets(retweets.intValue());
		t.setTweet(status.getText());
		return t;
	}
}
