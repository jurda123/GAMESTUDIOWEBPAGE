package sk.tsystems.gamestudio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Score {
	@Id
	@GeneratedValue
	private int scoreid;
//	private int userid;
//	private int gameid;
	private int score;
	@ManyToOne
	@JoinColumn (name="gameid")
	private Game game;
	@ManyToOne
	@JoinColumn (name="userid")
	private Player player;
	 
	public Score() {
		game= new Game();
		player = new Player();
	}
	
//	
//	public int getUserid() {
//		return userid;
//	}
//	public void setUserid(int playerid) {
//		this.userid = playerid;
//	}
//	public int getGameid() {
//		return gameid;
//	}
//	public void setGameid(int gameid) {
//		this.gameid = gameid;
//	}

	public int getScoreid() {
		return scoreid;
	}

	public void setScoreid(int scoreid) {
		this.scoreid = scoreid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	 
}
