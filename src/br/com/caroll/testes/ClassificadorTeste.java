package br.com.caroll.testes;

import br.com.caroll.analisesentimento.Classificador;

public class ClassificadorTeste {
	
	public static void main(String[] args) {
		
		Classificador classificador = new Classificador();
		
		int i = classificador.classificaBayesiano("ótimo dia");
		
		System.out.println("resultado: "+i);
		
	}

}
