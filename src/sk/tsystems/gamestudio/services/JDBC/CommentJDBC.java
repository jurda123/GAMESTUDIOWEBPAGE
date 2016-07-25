package sk.tsystems.gamestudio.services.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sk.tsystems.gamestudio.services.CommentService;

public class CommentJDBC extends ConfigureJDBC implements CommentService{
	public static final String GETCOMMENTCOUNT = "SELECT count(*) from commentar where gameid = ?";
	public static final String ADDCOMMENT = "INSERT INTO commentar (commentid,  userid, gameid,commentar) VALUES (?, ?, ?, ?)";
	public static final String COMMENTNEXTVAL = "SELECT commentid.nextval from dual";
	public static final String GETALLCOMMENTS = "SELECT commentar from commentar where gameid = ? ";
	private String comment;
	private int userID;
	private int gameID;
	
	
	public CommentJDBC(String comment,String gameName){
		this.userID=getUserId(System.getProperty("user.name"));
		 this.gameID = getGameId(gameName);
		 this.comment=comment;
	}
	@Override
	public void addComment() {
		
		 try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement stmt = con.prepareStatement(ADDCOMMENT);) {
				Statement scoreNextVal = con.createStatement();
				ResultSet rs = scoreNextVal.executeQuery(COMMENTNEXTVAL);
				int index = 0;
				while (rs.next()) {
					index = rs.getInt(1);
				}
				stmt.setInt(1, index);
				stmt.setInt(2, userID);
				stmt.setInt(3, gameID);
				stmt.setString(4, comment);
				stmt.executeUpdate();
				stmt.close();
		 } catch (SQLException e) {
			System.err.println("Error reading database (addComment)");
		}
	}

	@Override
	public String[] getAllCommentsForGame() {
		ArrayList<String> list = new ArrayList<String>();
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = con.prepareStatement(GETALLCOMMENTS);) {
			stmt.setInt(1, gameID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		}
		catch (SQLException e) {
			System.err.println("Error reading database (getAllCommentsForGame)");
			e.printStackTrace();
		}
		String[] array = new String[list.size()];
		array = list.toArray(array);
		return array;
	}

	@Override
	public int getNumberOfComments(String gameName) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
			PreparedStatement scoreNextVal = con.prepareStatement(GETCOMMENTCOUNT);
			scoreNextVal.setInt(1, getGameId(gameName));
			ResultSet rs = scoreNextVal.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error reading database (getNumberOfRatingForGame)");
		}
		
		return 0;
	}

}
