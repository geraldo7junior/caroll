package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.HashtagTweetVO;

public class HashtagTweetDAO {

	Connection con;
	
	public HashtagTweetDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (HashtagTweetVO hashTagTweetVO) {
		
		String sql = "INSERT Hashtag_has_Tweet (Hashtag_idHashtag, "
				+ "Tweet_idTweet) VALUES (?, ?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, hashTagTweetVO.getHashtagFk());
			stmt.setInt(2, hashTagTweetVO.getTweetFk());

			stmt.execute();
			
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}

}
