package br.com.caroll.testes;

import twitter4j.TwitterException;
import br.com.caroll.utilitarios.TimeLine;

public class TimeLineTeste {
	
	public static void main(String[] args) throws TwitterException {
		
		TimeLine timeLine = new TimeLine();
		
		timeLine.capturarTimeLine();
	}

}
