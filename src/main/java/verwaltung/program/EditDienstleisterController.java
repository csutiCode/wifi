package verwaltung.program;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import verwaltung.db.Dao;
import verwaltung.repository.Dienstleister;
import verwaltung.repository.FirmenAdresse;
import verwaltung.repository.Geschlecht;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;

public class EditDienstleisterController {
	
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtPlz;
	@FXML
	private TextField txtStadt;
	@FXML
	private RadioButton rbMann;
	@FXML
	private RadioButton rbFrau;
	@FXML
	private TextField txtTelefon;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtBeruf;
	@FXML
	private DatePicker dtpGeburtsDatum;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtStrasse;
	@FXML
	private TextField txtNummer;
	
	private ToggleGroup group = new ToggleGroup();
	
	// der Dienstleister, der gerade bearbeitet wird, unser Objekt
	private Dienstleister editDienstleister;
	
	private FirmenAdresse editAdresse;
		
	public Dienstleister getEditDienstleister() {
		return editDienstleister;
	}
	public void setEditDienstleister(Dienstleister editDienstleister) {
		if (editDienstleister==null) {
			//ein neuer Dienstleister wir erfasst
			this.editDienstleister = new Dienstleister();
		} else {
			this.editDienstleister = editDienstleister;
			dienstleisterToControls();			
		}
		
	}
	
	public FirmenAdresse getEditAdresse() {
		return editAdresse;
	}
		
	public void setEditAdresse(FirmenAdresse editAdresse) {
		this.editAdresse = editAdresse;
		}	
	@FXML
	public void initialize() {
		//das Objekt setzen oder aus der Datenbank holen
		if (TableViewController.getId()==0) {
			editDienstleister = new Dienstleister();
		} else {
			editDienstleister = Dao.getOneById(TableViewController.getId());
		}
		System.out.println(editDienstleister + " from initialize");
		
	    rbMann.setToggleGroup(group);
	    rbFrau.setToggleGroup(group);
	    rbMann.setSelected(true);
	    
	    BooleanBinding isValid = Bindings.createBooleanBinding(this::isValid, txtName.textProperty(),
				dtpGeburtsDatum.valueProperty(), txtBeruf.textProperty(), txtBeruf.textProperty(), txtTelefon.textProperty(),
				txtStadt.textProperty(), txtPlz.textProperty(), txtStrasse.textProperty(), txtNummer.textProperty(),
				txtEmail.textProperty(),txtTelefon.textProperty());
		btnOk.disableProperty().bind(isValid.not());
		
	}
	// Event Listener on Button[#btnOK].onAction
	@FXML
	public void onOk(ActionEvent event) {
		
		//ändert das Dienstleister Objekt in der Datenbank
		//oder erstellt ein neues Dienstleister Objekt in der Datenbank
		
		controlsToDienstleister();

		((Stage)txtName.getScene().getWindow()).close();
	}
	
	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		editDienstleister = null;
		((Stage)txtName.getScene().getWindow()).close();
		
	}
	
	
	public void controlsToDienstleister() {

		// zu dem Dienstleister OBjekt
//		editDienstleister = new Dienstleister();
		//zu der AdressenObjekt
		editAdresse = new FirmenAdresse();
			//Daten zur Firmenadresse setzen
			editAdresse.setPlz(Integer.parseInt(txtPlz.getText()));
			editAdresse.setStadt(txtStadt.getText());
			editAdresse.setStrasse(txtStrasse.getText());
			editAdresse.setHausnummer(Integer.parseInt(txtNummer.getText()));
		//Das FirmenAdresse-Objekt dem Dienstleister übergeben
		editDienstleister.setAdresse(editAdresse);
		//Daten zum Dienstleister OBjekt setzen
		editDienstleister.setBeruf(txtBeruf.getText());
		editDienstleister.setName(txtName.getText());
		editDienstleister.setEmail(txtEmail.getText());
		editDienstleister.setTelefon(txtTelefon.getText());
		editDienstleister.setGeburtsDatum(dtpGeburtsDatum.getValue());
		Toggle selToggle = group.getSelectedToggle();
			if (selToggle == rbMann) {
				editDienstleister.setGeschlecht(Geschlecht.MANN);
			} else {
				editDienstleister.setGeschlecht(Geschlecht.FRAU);
			}

	}

	public void dienstleisterToControls() {
		try {
		txtName.setText(editDienstleister.getName());
		txtPlz.setText(String.valueOf(editDienstleister.getAdresse().getPlz()));
		txtStadt.setText(editDienstleister.getAdresse().getStadt());
		txtStrasse.setText(editDienstleister.getAdresse().getStrasse());
		txtNummer.setText(String.valueOf(editDienstleister.getAdresse().getHausnummer()));
		txtBeruf.setText(editDienstleister.getBeruf());
		txtTelefon.setText(editDienstleister.getTelefon());
		txtEmail.setText(editDienstleister.getEmail());
		dtpGeburtsDatum.setValue(editDienstleister.getGeburtsDatum());
		
		switch (editDienstleister.getGeschlecht()) {
			case MANN:
				rbMann.setSelected(true);
				break;
			case FRAU:
				rbFrau.setSelected(true);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void updateDienstleister() {
//		editDienstleister = Dao.getOneById(editDienstleister.getServiceId());
//		editAdresse = editDienstleister.getAdresse();
//		//Daten zur Firmenadresse setzen
//		editAdresse.setPlz(Integer.parseInt(txtPlz.getText()));
//		editAdresse.setStadt(txtStadt.getText());
//		editAdresse.setStrasse(txtStrasse.getText());
//		editAdresse.setHausnummer(Integer.parseInt(txtNummer.getText()));
//		//Das FirmenAdresse-Objekt dem Dienstleister übergeben
//		editDienstleister.setAdresse(editAdresse);
//		//Daten zum Dienstleister OBjekt setzen
//		editDienstleister.setBeruf(txtBeruf.getText());
//		editDienstleister.setName(txtName.getText());
//		editDienstleister.setEmail(txtEmail.getText());
//		editDienstleister.setTelefon(txtTelefon.getText());
//		editDienstleister.setGeburtsDatum(dtpGeburtsDatum.getValue());
//		Toggle selToggle = group.getSelectedToggle();
//		if (selToggle == rbMann) {
//			editDienstleister.setGeschlecht(Geschlecht.MANN);
//		} else {
//			editDienstleister.setGeschlecht(Geschlecht.FRAU);
//		}
//		
//		
//	}
	
	
	public boolean isValid() {
		String text;
		boolean ok = (text = txtName.getText()) != null && !text.isEmpty()
				&& (text = txtStadt.getText()) != null && !text.isEmpty()
				&& (text = txtPlz.getText()) != null && !text.isEmpty()
				&& (text = txtStrasse.getText()) != null && !text.isEmpty()
				&& (text = txtNummer.getText()) != null && !text.isEmpty()
				&& (text = txtBeruf.getText()) != null && !text.isEmpty()
				&& dtpGeburtsDatum.getValue() != null
				&& (text = txtEmail.getText()) != null && !text.isEmpty()
				&& (text = txtTelefon.getText()) != null && !text.isEmpty();
		return ok;
	}
	
}
