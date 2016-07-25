package sk.tsystems.gamestudio.services.jpa;
import javax.persistence.EntityManager;
import sk.ness.jpa.JpaHelper;

import sk.tsystems.gamestudio.entities.Comment;
import sk.tsystems.gamestudio.services.CommentService;

public class CommentHibernate extends UtilHibernate implements CommentService {
	
	private String comment;
	private String gameName;
	private EntityManager m= JpaHelper.getEntityManager();
	public CommentHibernate(String comment, String gameName) {
		this.comment = comment;
		this.gameName = gameName;
	}

	public CommentHibernate() {

	}

	@Override
	public void addComment() {
		JpaHelper.beginTransaction();
		Comment c = new Comment();
		c.getGame().setGameid(getGameId(gameName));
		c.getPlayer().setUserid(getUserID());
		c.setCommentar(comment);
		m.persist(c);
		JpaHelper.commitTransaction();
	}

	@Override
	public String[] getAllCommentsForGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfComments(String gameName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
