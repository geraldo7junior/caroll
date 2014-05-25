package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.HashtagVO;

public class HashtagDAO {
	
Connection con;
	
	public HashtagDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (HashtagVO hashTagVO) {
		
		String sql = "INSERT Hashtag(idHashtag, "
				+ "dadoHashtag) VALUES (?, ?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, hashTagVO.getIdHashtag());
			stmt.setString(2, hashTagVO.getDadoHashtag());

			stmt.execute();
			
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}


}
