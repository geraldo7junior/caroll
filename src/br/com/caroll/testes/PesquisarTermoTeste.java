package br.com.caroll.testes;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import twitter4j.TwitterException;
import br.com.caroll.utilitarios.PesquisarTermo;

public class PesquisarTermoTeste {

	public static void main(String[] args) throws TwitterException, ClassNotFoundException, SQLException, FileNotFoundException {
		
		PesquisarTermo pesquisarTermo = new PesquisarTermo();
		
		
		pesquisarTermo.pesquisarTermo("Celular Tablet Android");
		pesquisarTermo.pesquisarTermo("Celular Android");
		pesquisarTermo.pesquisarTermo("Tablet Android");

		pesquisarTermo.pesquisarTermo("Celular Tablet IOS");
		pesquisarTermo.pesquisarTermo("Celular IOS");
		pesquisarTermo.pesquisarTermo("Tablet IOS");
		pesquisarTermo.pesquisarTermo("Ipad");

		pesquisarTermo.pesquisarTermo("Celular Tablet Windows Phone");
		pesquisarTermo.pesquisarTermo("Celular Windows Phone");
		pesquisarTermo.pesquisarTermo("Tablet Windows Phone");





		
	}
}
