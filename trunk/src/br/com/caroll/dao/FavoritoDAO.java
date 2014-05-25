package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.FavoritoVO;

public class FavoritoDAO {
	
	Connection con;
	
	FavoritoDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	
		
	}
	
	public void inserir (FavoritoVO favoritoVO) {
		String sql = "INSERT INTO Favorito (Usuario_idUsuario, Tweet_idTweet) "
				+ "VALUES (?, ?)";
		
		try {
			
			PreparedStatement stmt =  con.prepareStatement(sql);
			
			stmt.setInt(1, favoritoVO.getUsuarioFk());
			stmt.setInt(2, favoritoVO.getTweetFk());
			
			stmt.execute();
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

}

}