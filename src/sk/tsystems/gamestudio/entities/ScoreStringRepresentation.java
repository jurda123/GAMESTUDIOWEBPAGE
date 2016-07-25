package sk.tsystems.gamestudio.entities;

public class ScoreStringRepresentation {
	private String userName;
	private String GameName;
	private int score;
	
	
public ScoreStringRepresentation(String userName, String gameName,int score){
		this.userName = userName;
		this.GameName = gameName;
		this.score = score;
		
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGameName() {
		return GameName;
	}

	public void setGameName(String playerName) {
		this.GameName = playerName;
	}
	
}
