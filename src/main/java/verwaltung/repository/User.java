package verwaltung.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//User POJO
@Entity
public class User {
	
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String benutzerName;
	private String passwort;
	
	public User() {};
	
	public User(int userId) {
		super();
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", benutzerName=" + benutzerName + ", passwort=" + passwort + "]";
	}
	
}
