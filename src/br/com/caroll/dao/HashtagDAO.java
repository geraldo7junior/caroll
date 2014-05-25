package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.HashtagVO;
import br.com.caroll.vo.TweetVO;

public class HashtagDAO {
	
Connection con;
	
	public HashtagDAO () throws ClassNotFoundException, SQLException {
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (HashtagVO hashTagVO) {
		
		String sql = "INSERT Hashtag (dadoHashtag) VALUES (?)";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, hashTagVO.getDadoHashtag());

			stmt.execute();
			
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}

public List <HashtagVO> listarHashTag (HashtagVO hashtagVO) {
		
		List <HashtagVO> listaHashtag = new ArrayList<HashtagVO>();
		String sql = "SELECT * FROM Hashtag WHERE dadoHashtag = ?";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, hashtagVO.getDadoHashtag());
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				hashtagVO.setIdHashtag(rs.getInt("idHashtag"));
				hashtagVO.setDadoHashtag(rs.getString("dadoHashtag"));
				
				listaHashtag.add(hashtagVO);
	
			}
			
			return listaHashtag;
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}

}
