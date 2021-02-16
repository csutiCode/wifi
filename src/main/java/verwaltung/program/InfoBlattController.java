package verwaltung.program;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import verwaltung.db.Dao;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
import verwaltung.repository.FirmenAdresse;
import verwaltung.repository.Geschlecht;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import antlr.collections.List;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class InfoBlattController {
	@FXML
	private Button btnOK;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtGeschlecht;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtGeburtsdatum;
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
		txtGeburtsdatum.setEditable(false);
		txtBeruf.setEditable(false);
		txtEmail.setEditable(false);
		txtTelefon.setEditable(false);
		txtBewertungen.setEditable(false);
		txtDurchschnitt.setEditable(false);
		
		//itt az objektum mar nulla
//		System.out.println(dienstleister + " initialize");
	}

	// Event Listener on Button[#btnOK].onAction
	@FXML
	public void onOk(ActionEvent event) {
		((Stage)txtName.getScene().getWindow()).close();
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void onBewerten(ActionEvent event) {
		BewertungWindow bW = new BewertungWindow();
		bW.showWindow();
	}

	public void zeigeDatei() {
		System.out.println(dienstleister + " zeigeDatei");
		//die Datei anzeigen
		txtName.setText(dienstleister.getName());	
		FirmenAdresse adresse = new FirmenAdresse();
		adresse = dienstleister.getAdresse();
		String txtFirmenAdresse = adresse.getPlz() + " " + adresse.getStadt() + " " 
		+ adresse.getStrasse() + " " + adresse.getHausnummer();
		txtAddress.setText(txtFirmenAdresse);
		txtGeburtsdatum.setText(String.valueOf(dienstleister.getGeburtsDatum()));
		txtBeruf.setText(dienstleister.getBeruf());
		txtEmail.setText(dienstleister.getEmail());
		txtTelefon.setText(dienstleister.getTelefon());
		if (dienstleister.getGeschlecht().equals(Geschlecht.MANN)) {
			txtGeschlecht.setText("Mann"); 
		} else {
			txtGeschlecht.setText("Frau"); 
		}
		
		Set<Bewertung> set = new HashSet<Bewertung>();
		set = dienstleister.getBewertungen();
		//TODO: iterate through set
		StringBuilder sb = new StringBuilder();
		
		for (Bewertung bewertung : set) {
	
			sb.append(bewertung.getText() + " / " + bewertung.getBewerter() + " / " 
											+ bewertung.getDatum() + "\n");
			
		}
		txtBewertungen.setText(sb.toString());
		txtDurchschnitt.setText(String.valueOf(dienstleister.getDurchschnittsBewertung()));
		
		
	}

	

	
	
	
}
