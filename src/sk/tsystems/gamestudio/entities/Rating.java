package sk.tsystems.gamestudio.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Rating {
	@EmbeddedId
	RatingID ratingid;
	
	private int rating;
	
	 
	public Rating() {
		
	}


	public RatingID getRatingid() {
		return ratingid;
	}


	public void setRatingid(RatingID ratingid) {
		this.ratingid = ratingid;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
