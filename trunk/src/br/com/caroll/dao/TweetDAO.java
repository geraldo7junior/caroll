package br.com.caroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caroll.conexao.ConnectionFactory;
import br.com.caroll.vo.TweetVO;

public class TweetDAO {
	
	Connection con;
	
	public TweetDAO () throws ClassNotFoundException, SQLException {
		
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void inserir (TweetVO tweetVO) {
		String sql = "INSERT INTO Tweet (postTweet, dataTweet, Usuario_idUsuario) "
				+ "VALUES (?, ?, ?)";
		
		
		try {
			
			PreparedStatement stmt =  con.prepareStatement(sql);
			
			stmt.setString(1, tweetVO.getPostTweet());
			stmt.setString(2, tweetVO.getDataTweet());
			stmt.setInt   (3, tweetVO.getUsuarioFk());
			
			//stmt.setInt(4, tweetVO.getSentimentoFk());
			
			stmt.execute();
			stmt.close();
			
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
		
	}
	
	
	public List <TweetVO> listarTweet (TweetVO tweetVO) {
		
		List <TweetVO> listaTweet = new ArrayList<TweetVO>();
		String sql = "SELECT * FROM Tweet WHERE postTweet = ?";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, tweetVO.getPostTweet());
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				tweetVO.setIdTweet(rs.getInt("idTweet"));
				tweetVO.setPostTweet(rs.getString("postTweet"));
				tweetVO.setDataTweet(rs.getString("dataTweet"));
				tweetVO.setUsuarioFk(rs.getInt("Usuario_idUsuario"));
				
				
				listaTweet.add(tweetVO);

			}
			
			return listaTweet;
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}
	
	
	
	public int selecionarId (TweetVO tweetVO) {
		
		List <TweetVO> listaTweet = new ArrayList<TweetVO>();
		String sql = "SELECT idTweet FROM Tweet WHERE idTweet = ?";
		
		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, tweetVO.getIdTweet());
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				tweetVO.setIdTweet(rs.getInt("idTweet"));
				tweetVO.setPostTweet(rs.getString("postTweet"));
				tweetVO.setDataTweet(rs.getString("dataTweet"));
				tweetVO.setUsuarioFk(rs.getInt("Usuario_idUsuario"));
				tweetVO.setIdTweet(rs.getInt("idTweet"));

			}
			
			return 0;
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		}
	}

}
