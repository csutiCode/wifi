package verwaltung.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Target;
@Entity
public class Dienstleister {
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	private String name;
	@Embedded 
	private FirmenAdresse adresse;
	private String beruf;
	private LocalDate geburtsDatum;
	private String telefon;
	private String email;
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="serviceId")
	private Set<Bewertung> bewertungen;
	@Transient
	private double durchschnittsBewertung;
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FirmenAdresse getAdresse() {
		return adresse;
	}
	public void setAdresse(FirmenAdresse adresse) {
		this.adresse = adresse;
	}
	public LocalDate getGeburtsDatum() {
		return geburtsDatum;
	}
	public void setGeburtsDatum(LocalDate geburtsDatum) {
		this.geburtsDatum = geburtsDatum;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Bewertung> getBewertungen() {
		return bewertungen;
	}
	public void setBewertungen(Set<Bewertung> bewertungen) {
		this.bewertungen = bewertungen;
	}
	
	public double getDurchschnittsBewertung() {
		Set<Bewertung> set = new HashSet<Bewertung>();
		set = getBewertungen();
		
		double sum = 0;
		for (Bewertung bewertung : set) {
			sum+= bewertung.getNote();	
		}
		durchschnittsBewertung = sum/set.size();
		return durchschnittsBewertung;
	}
	public void setDurchschnittsBewertung(int durchschnittsBewertung) {
		this.durchschnittsBewertung = durchschnittsBewertung;
	}
	public String getBeruf() {
		return beruf;
	}
	public void setBeruf(String beruf) {
		this.beruf = beruf;
	}
	
	public void setDurchschnittsBewertung(double durchschnittsBewertung) {
		this.durchschnittsBewertung = durchschnittsBewertung;
	}
	@Override
	public String toString() {
		return "Dienstleister [serviceId=" + serviceId + ", name=" + name + ", user=" + user + "]";
	}
	

}
