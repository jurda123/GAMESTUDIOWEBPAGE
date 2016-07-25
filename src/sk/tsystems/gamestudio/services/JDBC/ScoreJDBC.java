package sk.tsystems.gamestudio.services.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sk.tsystems.gamestudio.entities.Score;
import sk.tsystems.gamestudio.entities.ScoreStringRepresentation;
import sk.tsystems.gamestudio.services.ScoreService;

public class ScoreJDBC   extends ConfigureJDBC implements ScoreService{
	
	public static final String ADDSCORE = "INSERT INTO score (scoreid,  userid, gameid,score) VALUES (?, ?, ?, ?)";
	public static final String SCORENEXTVAL = "SELECT scoreid.nextval from dual";
	public static final String GETALLSCORE = "SELECT player.name,game.name,score.score from score join player on score.USERID=player.USERID join game on GAME.GAMEID = score.gameid where game.gameid = ? order by score desc ";
	private int score;
	private int userID;
	private int gameID;
	public ScoreJDBC(String gameName, int score){
		this.userID=getUserId(System.getProperty("user.name"));
		 this.gameID = getGameId(gameName);
		 this.score=score;
	}
	
	@Override
	public void addScore() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = con.prepareStatement(ADDSCORE);) {
			Statement scoreNextVal = con.createStatement();
			ResultSet rs = scoreNextVal.executeQuery(SCORENEXTVAL);
			int index = 0;
			while (rs.next()) {
				index = rs.getInt(1);
			}
			stmt.setInt(1, index);
			stmt.setInt(2, userID);
			stmt.setInt(3, gameID);
			stmt.setInt(4, score);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Error reading database (addScore)");
			e.printStackTrace();
			
		}
	}

	@Override
	public ArrayList<ScoreStringRepresentation> getTopTenScore() {
		ArrayList<ScoreStringRepresentation> list = new ArrayList<ScoreStringRepresentation>();
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = con.prepareStatement(GETALLSCORE);) {
			stmt.setInt(1, gameID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String userName = rs.getString(1);
				String playerName = rs.getString(2);
				int score  = rs.getInt(3);
				
				list.add(new ScoreStringRepresentation(userName,playerName,score));
			}
		}
		catch (SQLException e) {
			System.err.println("Error reading database (getAllCommentsForGame)");
			e.printStackTrace();
		}
		return list;
		
	}

}
