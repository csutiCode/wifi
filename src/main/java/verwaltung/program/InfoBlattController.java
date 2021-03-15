package verwaltung.program;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
import verwaltung.repository.FirmenAdresse;
import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

//Kontrollerklasse für InfoFenster
public class InfoBlattController {
	@FXML
	private Button btnOK;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtBeruf;
	@FXML
	private TextField txtTelefon;
	@FXML
	private TextArea txtBewertungen;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtDurchschnitt;
	@FXML
	private TextField txtAnzahlBewertungen;
	
	private Dienstleister dienstleister;
	
	public Dienstleister getDienstleister() {
		return dienstleister;
	}
	
	public void setDienstleister(Dienstleister dienstleister) {
		this.dienstleister = dienstleister;
		zeigeDatei();
	}
	
	public void initialize() {
		//die Felder only read setzen
		txtName.setEditable(false);
		txtAddress.setEditable(false);
		txtBeruf.setEditable(false);
		txtEmail.setEditable(false);
		txtTelefon.setEditable(false);
		txtBewertungen.setEditable(false);
		txtDurchschnitt.setEditable(false);
		txtAnzahlBewertungen.setEditable(false);
		
	}

	// Event Listener für Ok-Button
	@FXML
	public void onOk(ActionEvent event) {
		//schliesst das Fenster
		((Stage)txtName.getScene().getWindow()).close();
		
	}
	// Event Listener für Bewerten-Button, öffnet das Bewertung-Fenster
	@FXML
	public void onBewerten(ActionEvent event) {
		BewertungWindow bW = new BewertungWindow();
		bW.showWindow();
		((Stage)txtName.getScene().getWindow()).close();
		
	}
	//zeigt die Daten von dem gewünschten Dienstleister
	public void zeigeDatei() {
		System.out.println(dienstleister + " zeigeDatei");
		//die Datei anzeigen
		txtName.setText(dienstleister.getName());	
		FirmenAdresse adresse = new FirmenAdresse();
		adresse = dienstleister.getAdresse();
		String txtFirmenAdresse = adresse.getPlz() + " " + adresse.getStadt() + " " 
		+ adresse.getStrasse() + " " + adresse.getHausnummer();
		txtAddress.setText(txtFirmenAdresse);
		txtBeruf.setText(dienstleister.getBeruf());
		txtEmail.setText(dienstleister.getEmail());
		txtTelefon.setText(dienstleister.getTelefon());

		
		Set<Bewertung> set = new HashSet<Bewertung>();
		set = dienstleister.getBewertungen();
		
		StringBuilder sb = new StringBuilder();
		
		for (Bewertung bewertung : set) {
	
			sb.append(bewertung.getText() + " / " + bewertung.getBewerter() + " / " 
											+ bewertung.getDatum() + "\n");
			
		}
		
		
		txtAnzahlBewertungen.setText(String.valueOf(dienstleister.getBewertungen().size()));
		txtBewertungen.setText(sb.toString());
		if (dienstleister.getBewertungen().size()==0) {
			txtDurchschnitt.setText(" ");
		} else {
			txtDurchschnitt.setText(String.valueOf(dienstleister.getDurchschnittsBewertung()));
		}
			
	}
	
}
