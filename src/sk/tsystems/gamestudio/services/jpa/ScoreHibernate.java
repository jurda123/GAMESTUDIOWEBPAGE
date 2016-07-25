package sk.tsystems.gamestudio.services.jpa;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entities.Player;
import sk.tsystems.gamestudio.entities.Score;
import sk.tsystems.gamestudio.entities.ScoreStringRepresentation;
import sk.tsystems.gamestudio.services.ScoreService;


public class ScoreHibernate extends UtilHibernate implements ScoreService {
	private EntityManager m= JpaHelper.getEntityManager();
	
	private int score;

	private String gameName;

	public ScoreHibernate(int score, String gameName) {
		this.score = score;
		this.gameName = gameName;
	}

	public ScoreHibernate() {

	}
	public ScoreHibernate(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public void addScore() {
		JpaHelper.beginTransaction();
		Score s = new Score();
		s.getGame().setGameid(getGameId(gameName));
		s.getPlayer().setUserid(getUserID());
		s.setScore(score);
		m.persist(s);
		JpaHelper.commitTransaction();

	}
	// public static void main(String[] args) {
	// ScoreHibernate s= new ScoreHibernate(5,"GuessTheNumber");
	// ArrayList<ScoreEntity> list = s.getTopTenScore();
	// for (int i = 0; i < list.size(); i++) {
	// ScoreEntity se= list.get(i);
	//
	// System.out.println(se.getUserName()+" " + se.getGameName() + " " +
	// se.getScore());
	//
	// }
	// ScoreHibernate s = new ScoreHibernate(4, "Minesweeper");
	// s.addScore();
	// }

	@Override
	public ArrayList<ScoreStringRepresentation> getTopTenScore() {// //
		int gameid = getGameId(gameName);
		ArrayList<ScoreStringRepresentation> list = new ArrayList<ScoreStringRepresentation>();
		Query query = m.createQuery(
				"select s from Score s join s.player p join s.game g where g.gameid = :gameid order by s.score desc");
		query.setParameter("gameid", gameid);
		List<Score> queryList = query.getResultList();
		for (Score s : queryList) {
			ScoreStringRepresentation se = new ScoreStringRepresentation(getUserName(s.getPlayer().getUserid()), gameName, s.getScore());
			list.add(se);
		}
		return list;

	}

	private String getUserName(int userId) {
		Player player = new Player();
		Query query = m.createQuery("select p from Player p where userid = :userid");
		query.setParameter("userid", userId); 
		player =(Player)query.getSingleResult();
		return player.getName();
	}

}
