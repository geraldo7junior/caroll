package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.LocalVO;

public class LocalDAO {
	
Connection con;
	
	public LocalDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (LocalVO localVO) {
		
		String sql = "INSERT Local (latitudeLocal, longitudeLocal, "
				+ "cidadeLocal, estadoLocal, paisLocal, Tweet_idTweet) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setFloat (1, localVO.getLatitudeLocal());
			stmt.setFloat (2, localVO.getLongitudeLocal());
			stmt.setString(3, localVO.getCidadeLocal());
			stmt.setString(4, localVO.getEstadoLocal());
			stmt.setString(5, localVO.getPaisLocal());
			stmt.setInt   (6, localVO.getTweetFk());

			stmt.execute();
			
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}


}
