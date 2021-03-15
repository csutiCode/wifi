package verwaltung.repository;

import javax.persistence.Embeddable;

//eine Klasse, um die Adressendaten zu speichern
@Embeddable
public class FirmenAdresse {
	
	private int plz;
	private String stadt;
	private String strasse;
	private int hausnummer;
	
	
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getStadt() {
		return stadt;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public int getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	
}
