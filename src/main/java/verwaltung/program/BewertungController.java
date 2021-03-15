package verwaltung.program;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import verwaltung.db.Dao;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
import javafx.scene.control.Slider;

import javafx.scene.control.TextArea;
//Kontrollerklasse fürs BewertungFenster
public class BewertungController {
	
	@FXML
	private TextField txtName;
	@FXML
	private TextArea txtBewertung;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private Slider sldBewertung;
	
	public int id;
	
	private Dienstleister dienstleister;
	
	private Bewertung bewertung;
	
	public Dienstleister getDienstleister() {
		return dienstleister;
	}

	public void setDienstleister(Dienstleister dienstleister) {
		this.dienstleister = dienstleister;
		
	}

	public Bewertung getBewertung() {
		return bewertung;
	}

	public void setBewertung(Bewertung bewertung) {
		this.bewertung = bewertung;
	}
	public void initialize() {
		
		//holt das Dienstleisterobjekt und bindet das Ok-Button mit den Felder
		dienstleister = Dao.getOneById(TableViewController.getDienstleisterId());
		BooleanBinding isValid = Bindings.createBooleanBinding(this::isValid, txtName.textProperty(),
					txtBewertung.textProperty());
		
		btnOk.disableProperty().bind(isValid.not());
		

		 
		
	}
	//speichert die Bewertung und schliesst das Fenster
	public void onSpeichern() {
		controlsToBewertung();
		((Stage)txtName.getScene().getWindow()).close();
	}
	//schliesst das Fenster
	public void onBeenden() {
		((Stage)txtName.getScene().getWindow()).close();
	}
	
	//übergibt die Daten aus den Felder dem Bewertung-Objekt 
	public void controlsToBewertung() {
		bewertung = new Bewertung();
		//die Bewertungsdatei speichern
		bewertung.setBewerter(txtName.getText());
		bewertung.setText(txtBewertung.getText());
		bewertung.setDatum(LocalDate.now());
		bewertung.setNote(sldBewertung.getValue());

		//das Bewertungsobjekt dem aktuellen Dienstleister übergeben
		dienstleister.getBewertungen().add(bewertung);
		Dao.editDienstleister(dienstleister);
		
		
	}
	//kontrolliert, ob die Felder ausgefüllt sind
	public boolean isValid() {
		String text;
		boolean ok = (text = txtName.getText()) != null && !text.isEmpty()
				&& (text = txtBewertung.getText()) != null && !text.isEmpty();
		return ok;
	}
}
