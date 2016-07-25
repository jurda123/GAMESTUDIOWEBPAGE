package sk.tsystems.gamestudio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "COMMENTARY")
public class Comment {
	@Id
	@GeneratedValue
	private int commentid;
	
	@ManyToOne
	@JoinColumn (name="userid")
	private Player player;
	
	@ManyToOne
	@JoinColumn (name="gameid")
	private Game game;
	private String commentar;
	
	 
	public Comment() {
		game = new Game();
		player = new Player();
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getCommentar() {
		return commentar;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public void setCommentar(String commentar) {
		this.commentar = commentar;
	}
	 
	 
}
