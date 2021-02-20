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
		System.out.println(dienstleister + " BewertungSindowbol meghivott setter");
	}

	public Bewertung getBewertung() {
		return bewertung;
	}

	public void setBewertung(Bewertung bewertung) {
		this.bewertung = bewertung;
	}
	public void initialize() {
				
		dienstleister = Dao.getOneById(TableViewController.getDienstleisterId());
		BooleanBinding isValid = Bindings.createBooleanBinding(this::isValid, txtName.textProperty(),
					txtBewertung.textProperty());
		
		btnOk.disableProperty().bind(isValid.not());
		
//		System.out.println(dienstleister + " initialize bewertungwindow");
//		System.out.println("id =" + id);
		 
		
	}

	public void onSpeichern() {
		controlsToBewertung();
		((Stage)txtName.getScene().getWindow()).close();
	}
	
	public void onOk() {
		((Stage)txtName.getScene().getWindow()).close();
	}
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
	
	
	public boolean isValid() {
		String text;
		boolean ok = (text = txtName.getText()) != null && !text.isEmpty()
				&& (text = txtBewertung.getText()) != null && !text.isEmpty();
		return ok;
	}
	
	
	

}
