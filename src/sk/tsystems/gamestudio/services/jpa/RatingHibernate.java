package sk.tsystems.gamestudio.services.jpa;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entities.Rating;
import sk.tsystems.gamestudio.entities.RatingID;
import sk.tsystems.gamestudio.services.RatingService;

public class RatingHibernate extends UtilHibernate implements RatingService {
	private int rating;
	private String gameName;
	private EntityManager m= JpaHelper.getEntityManager();
	public RatingHibernate(int rating, String gameName) {
		this.rating = rating;
		this.gameName = gameName;
	}

	public RatingHibernate() {

	}

	public RatingHibernate(String gameName) {
		this.gameName = gameName;
	}

	@Override
	public void addRating() {
		try {
			JpaHelper.beginTransaction();
			Rating r = new Rating();
			RatingID rid = new RatingID(getUserID(), getGameId(gameName));
			r.setRatingid(rid);
			r.setRating(rating);
			m.persist(r);
			JpaHelper.commitTransaction();
		} catch (Exception e) {
			m.getTransaction().rollback();
			updateRating();
			// System.err.print("tu sa to zrubalo");
		//	e.printStackTrace();
		}
	}

	private void updateRating() {
		JpaHelper.beginTransaction();
		RatingID rid = new RatingID(getUserID(), getGameId(gameName));
		Rating ratingToBeChanged = m.find(Rating.class, rid);
		ratingToBeChanged.setRating(rating);
		JpaHelper.commitTransaction();
	}

	// public static void main(String[] args) {
	// RatingHibernate r = new RatingHibernate(4, "Minesweeper");
	// r.addRating();
	//
	// System.out.print("avg : " + r.getAverageRatingForGame("Minesweeper") + "
	// total: "
	// + r.getNumberOfRatingForGame("Minesweeper"));
	//
	// }

	@Override
	public double getAverageRatingForGame(String game) {
		try {
			Query query = m.createQuery("select avg(r.rating) from Rating r where r.ratingid.game.gameid = :gameid");
			query.setParameter("gameid", getGameId(game));
			double averageRating = (double) query.getSingleResult();
			return averageRating;		
		} catch (NullPointerException e) {
			return 0;

		}
	}

	@Override
	public long getNumberOfRatingForGame(String game) {
			Query query = m.createQuery("select count(*) from Rating r where r.ratingid.game.gameid = :gameid");
			query.setParameter("gameid", getGameId(game));
			return (long) query.getSingleResult();
	}

}
