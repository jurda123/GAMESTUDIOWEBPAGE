package sk.tsystems.gamestudio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Game {
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAddeddate() {
		return addeddate;
	}
	public void setAddeddate(String addeddate) {
		this.addeddate = addeddate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Id
	@GeneratedValue
	private int gameid;
	private String name;
	private String author;
	private String addeddate;
	private String url;
}
