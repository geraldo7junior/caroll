package br.com.caroll.testes;
import java.sql.SQLException;
import br.com.caroll.conexao.ConnectionFactory;

public class ConexaoBanco {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		connectionFactory.getConnection();
		
		System.out.println("Conexão efetuada com sucesso");
	}

}
