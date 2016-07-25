package sk.tsystems.gamestudio.services.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class ConfigureJDBC {
	public static final String DRIVER_CLASS = "org.apache.derby.jdbc.ClientDriver";
	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	public static final String USER = "gamestudio";
	public static final String PASSWORD = "password";
	public static final String GETUSERID = "select userid from player where name like ?";
	public static final String GETGAMEID = "select gameid from game where name like ?";
	public static final String INSERTUSER = "insert into player(userid,name,password,email,registrationdate) values (?,?,?,?,?);";
	public static final String USERNEXTVAL = "SELECT userid.nextval from dual";
	
	
	
	public int getUserId(String userName) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
			PreparedStatement stmt = con.prepareStatement(GETUSERID);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return  rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error reading database (getUserId)");
		}
		return 0;
	}
	
	public int getGameId(String gameName){
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
			PreparedStatement stmt = con.prepareStatement(GETGAMEID);
			stmt.setString(1, gameName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return  rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error reading database (getGameId)");
		}
		return 0;
	}

	public void addNewUserInDatabase(String userName){
		if(getUserId(userName) == 0){
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
				PreparedStatement stmt = con.prepareStatement(GETUSERID);
				Statement userNextVal = con.createStatement();
				ResultSet rs = userNextVal.executeQuery(USERNEXTVAL);
				int index = 0;
				while (rs.next()) {
					index = rs.getInt(1);
				}
				stmt.setInt(1, index);
				stmt.setString(2, userName);
				stmt.setString(3, "passwd");
				stmt.setString(4, "email");
				stmt.setString(5, "28.6.2016");
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				System.err.println("Error reading database (getUserId)");
			}
			
		}
		
	}
	
	
}
