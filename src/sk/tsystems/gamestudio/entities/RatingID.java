package sk.tsystems.gamestudio.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RatingID implements Serializable {

	@ManyToOne
	@JoinColumn(name = "userid")
	private Player player;
	@ManyToOne
	@JoinColumn(name = "gameid")
	private Game game;

	public RatingID(int userid, int gameid) {
		game = new Game();
		player=new Player();
		player.setUserid(userid);
		game.setGameid(gameid);
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

	
}
