package br.com.caroll.vo;

public class LocalVO {
	
	private float latitudeLocal;
	private float longitudeLocal;
	private String cidadeLocal;
	private String estadoLocal;
	private String paisLocal;
	private int tweetFk;
	
	
	public float getLatitudeLocal() {
		return latitudeLocal;
	}
	public void setLatitudeLocal(float latitudeLocal) {
		this.latitudeLocal = latitudeLocal;
	}
	public float getLongitudeLocal() {
		return longitudeLocal;
	}
	public void setLongitudeLocal(float longitudeLocal) {
		this.longitudeLocal = longitudeLocal;
	}

	public String getCidadeLocal() {
		return cidadeLocal;
	}
	public void setCidadeLocal(String cidadeLocal) {
		this.cidadeLocal = cidadeLocal;
	}
	public String getEstadoLocal() {
		return estadoLocal;
	}
	public void setEstadoLocal(String estadoLocal) {
		this.estadoLocal = estadoLocal;
	}
	public String getPaisLocal() {
		return paisLocal;
	}
	public void setPaisLocal(String paisLocal) {
		this.paisLocal = paisLocal;
	}

	
	public int getTweetFk() {
		return tweetFk;
	}
	public void setTweetFk(int tweetFk) {
		this.tweetFk = tweetFk;
	}
	
	
}
