package sk.ness.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

	private String meno;
	
	private String priezvisko;
	
	private int vek;
	@Id
	@GeneratedValue
	private int id;
	
	
	@Override
	public String toString() {
		return "Student [meno=" + meno + ", priezvisko=" + priezvisko + ", vek=" + vek + ", id=" + id + "]";
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getPriezvisko() {
		return priezvisko;
	}
	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}
	public int getVek() {
		return vek;
	}
	public void setVek(int vek) {
		this.vek = vek;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
