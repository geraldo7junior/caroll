package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.SentimentoVO;

public class SentimentoDAO {
Connection con;
	
	public SentimentoDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (SentimentoVO sentimentoVO) {
		
		String sql = "INSERT Sentimento (idSentimento, valorSentimento) VALUES (?, ?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt    (1, sentimentoVO.getIdSentimento());
			stmt.setString (2, sentimentoVO.getValorSentimento());
			stmt.execute();
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}


}
