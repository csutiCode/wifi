package verwaltung.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Bewertung {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bewertungsId;
	private String text;
	private LocalDate datum;
	private double note;
	private String bewerter;
	
	private int serviceId;
	
	public int getBewertungsId() {
		return bewertungsId;
	}
	public void setBewertungsId(int bewertungsId) {
		this.bewertungsId = bewertungsId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public String getBewerter() {
		return bewerter;
	}
	public void setBewerter(String bewerter) {
		this.bewerter = bewerter;
	}
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

}
