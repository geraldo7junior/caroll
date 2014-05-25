package br.com.caroll.vo;

public class TweetVO {
	
	private int idTweet;
	private String postTweet;
	private String dataTweet;
	private int usuarioFk;
	private int sentimentoFk;
	
	public int getIdTweet() {
		return idTweet;
	}
	public void setIdTweet(int idTweet) {
		this.idTweet = idTweet;
	}
	
	public String getPostTweet() {
		return postTweet;
	}
	public void setPostTweet(String postTweet) {
		this.postTweet = postTweet;
	}
	public String getDataTweet() {
		return dataTweet;
	}
	public void setDataTweet(String dataTweet) {
		this.dataTweet = dataTweet;
	}
	public int getUsuarioFk() {
		return usuarioFk;
	}
	public void setUsuarioFk(int usuarioFk) {
		this.usuarioFk = usuarioFk;
	}
	public int getSentimentoFk() {
		return sentimentoFk;
	}
	public void setSentimentoFk(int sentimentoFk) {
		this.sentimentoFk = sentimentoFk;
	}

}
