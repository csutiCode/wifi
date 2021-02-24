package verwaltung.program;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import verwaltung.common.MessageBox;
import verwaltung.common.RegistrationValidator;
import verwaltung.common.ValidationState;
import verwaltung.db.Dao;
import verwaltung.repository.Dienstleister;
import verwaltung.repository.FirmenAdresse;
import verwaltung.repository.User;
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
	private TextField txtTelefon;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtBeruf;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtStrasse;
	@FXML
	private TextField txtNummer;
	
	
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
		if (TableViewController.getDienstleisterId()==0) {
			editDienstleister = new Dienstleister();
		} else {
			editDienstleister = Dao.getOneById(TableViewController.getDienstleisterId());
		}
		System.out.println(editDienstleister + " from initialize");
		
	 
	    
	    BooleanBinding isValid = Bindings.createBooleanBinding(this::isValid, txtName.textProperty(),
				txtBeruf.textProperty(), txtTelefon.textProperty(), txtStadt.textProperty(), 
				txtPlz.textProperty(), txtStrasse.textProperty(), txtNummer.textProperty(),
				txtEmail.textProperty(),txtTelefon.textProperty());
		btnOk.disableProperty().bind(isValid.not());
		
	}
	// Event Listener on Button[#btnOK].onAction
	@FXML
	public void onOk(ActionEvent event) {
		
		//ändert das Dienstleister Objekt in der Datenbank
		//oder erstellt ein neues Dienstleister Objekt in der Datenbank
		
		if (validateEmailAndPhone(txtEmail.getText(), txtTelefon.getText()).equals(ValidationState.SUCCES)) {
			controlsToDienstleister();
			((Stage)txtName.getScene().getWindow()).close();
		} else if (validateEmailAndPhone(txtEmail.getText(), txtTelefon.getText())
				.equals(ValidationState.EMAIL_NOT_VALID)){
			MessageBox.show("Problem", "E-mail Adresse nicht gültig!");
		} else if (validateEmailAndPhone(txtEmail.getText(), txtTelefon.getText())
				.equals(ValidationState.PHONE_NUMBER_NOT_VALID)) 
			MessageBox.show("Problem", "Telefonnummer nicht gültig!");
		
	}
	
	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void onCancel(ActionEvent event) {
		editDienstleister = null;
		((Stage)txtName.getScene().getWindow()).close();
		
	}
	
	
	public void controlsToDienstleister() {
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
		User user = Dao.getUserById(TableViewController.getUserId());
		editDienstleister.setUser(user);
		System.out.println(user);
		
		

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
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	
	public boolean isValid() {
		String text;
		boolean ok = (text = txtName.getText()) != null && !text.isEmpty()
				&& (text = txtStadt.getText()) != null && !text.isEmpty()
				&& (text = txtPlz.getText()) != null && !text.isEmpty()
				&& (text = txtStrasse.getText()) != null && !text.isEmpty()
				&& (text = txtNummer.getText()) != null && !text.isEmpty()
				&& (text = txtBeruf.getText()) != null && !text.isEmpty()
				&& (text = txtEmail.getText()) != null && !text.isEmpty()
				&& (text = txtTelefon.getText()) != null && !text.isEmpty();
		return ok;
	}
	
	public ValidationState validateEmailAndPhone(String email, String phone) {
		
		if (RegistrationValidator.validateEmail(email)
			&& RegistrationValidator.validatePhone(phone)) {
			return ValidationState.SUCCES;
		} else if (!RegistrationValidator.validateEmail(email)) {
			return ValidationState.EMAIL_NOT_VALID;
		} else
			return ValidationState.PHONE_NUMBER_NOT_VALID;

	}
	
}
