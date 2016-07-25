package sk.tsystems.gamestudio.services;

public interface CommentService {
	
	public void addComment();

	public String[] getAllCommentsForGame();
	
	public int getNumberOfComments(String gameName);
}
