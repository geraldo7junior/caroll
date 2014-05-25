package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.RetweetVO;

public class RetweetDAO {
	
Connection con;
	
	public RetweetDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (RetweetVO retweetVO) {
		
		String sql = "INSERT Resposta (idReposta, Tweet_idTweet) VALUES (?, ?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setFloat (1, retweetVO.getRetweetId());
			stmt.setFloat (2, retweetVO.getTweetFk());
			
			stmt.execute();
			
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}


}
