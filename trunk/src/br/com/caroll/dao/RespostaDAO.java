package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.RespostaVO;

public class RespostaDAO {
	
Connection con;
	
	public RespostaDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (RespostaVO respostaVO) {
		
		String sql = "INSERT Resposta (idReposta, Tweet_idTweet) VALUES (?, ?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setFloat (1, respostaVO.getIdResposta());
			stmt.setFloat (2, respostaVO.getTweetFk());
			
			stmt.execute();
			
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}



}
