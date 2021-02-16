package verwaltung.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;


@Entity
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String benutzerName;
	private String passwort;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBenutzerName() {
		return benutzerName;
	}
	public void setBenutzerName(String benutzerName) {
		this.benutzerName = benutzerName;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	
	

}
