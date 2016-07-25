package sk.tsystems.gamestudio.games.minesweeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;



/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
    /** List of best player times. */
    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();
    public static final String DRIVER_CLASS = "org.apache.derby.jdbc.ClientDriver";
	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	public static final String USER = "register";
	public static final String PASSWORD = "password";
	

    /**
     * Returns an iterator over a set of  best times.
     * @return an iterator
     */
    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }

    /**
     * Adds player time to best times.
     * @param name name ot the player
     * @param time player time in seconds
     */
    public void addPlayerTime(String name, int time) {
    	PlayerTime playerTime = new PlayerTime(name,  time);
    	addPlayerTimeToDatabase(playerTime);
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
    	selectFromDB();
    	sortPlayerlist();
    	Iterator<PlayerTime> iterator = this.iterator();
    	Formatter f = new Formatter();
    	int i=1;
    	while (iterator.hasNext()) { 
			PlayerTime p = iterator.next(); 
			
			f.format(i + ". " + parsePlayerTime(p) + "\n");		
			i++;
    	
			}
    	return f.toString();
    }
    
    void reset(){
    	playerTimes.clear();
    }
    private String parsePlayerTime(PlayerTime playerTime){
    	Formatter f = new Formatter();
    	f.format(playerTime.getName() + " " + playerTime.getTime());
    	 return f.toString();
    }
			
    private void sortPlayerlist(){
    	Collections.sort(playerTimes);
    	//playerTimes.sort();
    }
    public void addPlayerTimeToDatabase(PlayerTime playerTime) {
		String insertInto = "INSERT INTO besttimes (id,   name , time) VALUES (?, ?, ?)";
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
	
	public void selectFromDB(){
		
		
		String getPerson = "SELECT name,time from besttimes";
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
			PreparedStatement stmt2 = con.prepareStatement(getPerson);
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
						PlayerTime playerTime = new PlayerTime(rs2.getString(1), rs2.getInt(2));
						playerTimes.add(playerTime);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
		}
	
    

    /**
     * Player time.
     */
    public static class PlayerTime implements Comparable<PlayerTime> {
        /** Player name. */
        private final String name;

        /** Playing time in seconds. */
        private final int time;

        /**
         * Constructor.
         * @param name player name
         * @param time playing game time in seconds
         */
        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }
        

		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}
		@Override
		 public int compareTo(PlayerTime o){
			
			 if (time<o.getTime()){
				 return -1;
			 }
			 else if (time==o.getTime()){
				 return 0;
			 }
			 else if (time>o.getTime()){
				 return 1;
			 }
			 return 0;
		 }
    }
}