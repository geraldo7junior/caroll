package br.com.caroll.testes;

import twitter4j.TwitterException;
import br.com.caroll.utilitarios.PesquisarTermo;

public class PesquisarTermoTeste {

	public static void main(String[] args) throws TwitterException {
		
		PesquisarTermo pesquisarTermo = new PesquisarTermo();
		
		pesquisarTermo.pesquisarTermo("brasil");
	}
}
