package sk.tsystems.gamestudio.games.minesweeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sk.tsystems.gamestudio.games.minesweeper.BestTimes.PlayerTime;
import sk.tsystems.gamestudio.games.minesweeper.core.Field;


public class DatabaseSetting {
	public static final String DRIVER_CLASS = "org.apache.derby.jdbc.ClientDriver";
	// public static final String URL =
	// "jdbc:derby://localhost:1527/sample;create=true";
	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	public static final String USER = "register";
	public static final String PASSWORD = "password";
	private BestTimes bestTimes;

	public DatabaseSetting(BestTimes bestTimes) {
		this.bestTimes = bestTimes;
	}

	public void addPlayerTimeToDatabase(PlayerTime playerTime) {
		String insertInto = "INSERT INTO besttimes (id,  time, name) VALUES (?, ?, ?)";
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = con.prepareStatement(insertInto);) {

			Statement stmt2 = con.createStatement();
			String getIndex = "SELECT seq1.nextval from dual";
			ResultSet rs = stmt2.executeQuery(getIndex);
			int index = 0;
			while (rs.next()) {
				index = rs.getInt(1);
			}
			stmt.setInt(1, index);
			stmt.setString(2, playerTime.getName());
			stmt.setInt(3, playerTime.getTime());
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
