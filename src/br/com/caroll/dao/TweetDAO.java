package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.TweetVO;

public class TweetDAO {
	
	Connection con;
	
	TweetDAO () throws ClassNotFoundException, SQLException {
		
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (TweetVO tweetVO) {
		String sql = "INSERT INTO Tweet(postTweet, dataTweet, Usuario_idUsuario"
				+ "Sentimento_idSentimento) VALUES (?, ?, ? ,?)";
		
		
		try {
			
			PreparedStatement stmt =  con.prepareStatement(sql);
			
			stmt.setString(1, tweetVO.getPostTweet());
			stmt.setString(2, tweetVO.getDataTweet());
			stmt.setInt(3, tweetVO.getUsuarioFk());
			stmt.setInt(4, tweetVO.getSentimentoFk());
			
			stmt.execute();
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
		
	}

}
