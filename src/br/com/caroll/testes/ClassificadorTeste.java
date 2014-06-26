package br.com.caroll.testes;

import java.io.FileNotFoundException;

import br.com.caroll.sentimento.Classificador;

public class ClassificadorTeste {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Classificador classificador = new Classificador();
		
		 String [] teste = classificador.getSentencaAnalisadaScore("Muitas pessoas ainda estão presas no trânsito.");
		
		
		 for (String a : teste) {
			 System.out.println(a);
		 }
		 
	}

}
