package sk.tsystems.gamestudio.services.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entities.Game;
import sk.tsystems.gamestudio.entities.Player;

public class UtilHibernate {

	private int userID = getUserId(System.getProperty("user.name"));
	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	private Player player;

	public int getUserId(String playerName) {
		try {
			player = new Player();

			EntityManager m = JpaHelper.getEntityManager();
			Query query = m.createQuery("select p from Player p where name = :name");
			query.setParameter("name", playerName);
			List<Player> list = query.getResultList();
			player = list.get(0);
			return player.getUserid();
		} catch (Exception e) {
			return insertPlayer(playerName);

		}
	}

	private int insertPlayer(String playerName) {
		EntityManager m = JpaHelper.getEntityManager();
		JpaHelper.beginTransaction();
		Player player = new Player();
		player.setEmail("asd");
		player.setPassword("1234");
		player.setRegistrationDate("14.07.16");
		player.setName(playerName);
		m.persist(player);
		JpaHelper.commitTransaction();
		return getUserId(playerName);

	}

	public int getGameId(String gameName) {
		Game game = new Game();
		EntityManager m = JpaHelper.getEntityManager();
		Query query = m.createQuery("select g from Game g where name = :name");
		query.setParameter("name", gameName);
		
		game = (Game) query.getSingleResult();
		return game.getGameid();
	}
}
