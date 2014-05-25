package br.com.caroll.vo;

public class FavoritoVO {
	
	private  int idFavorito;
	private int usuarioFk;
	private int tweetFk;
	
	
	public int getIdFavorito() {
		return idFavorito;
	}
	public void setIdFavorito(int idFavorito) {
		this.idFavorito = idFavorito;
	}
	public int getUsuarioFk() {
		return usuarioFk;
	}
	public void setUsuarioFk(int usuarioFk) {
		this.usuarioFk = usuarioFk;
	}
	public int getTweetFk() {
		return tweetFk;
	}
	public void setTweetFk(int tweetFk) {
		this.tweetFk = tweetFk;
	}
	
	

}
