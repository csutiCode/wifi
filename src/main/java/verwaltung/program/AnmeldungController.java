package verwaltung.program;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import verwaltung.common.MessageBox;
import verwaltung.db.Dao;

public class AnmeldungController{
	@FXML
	private TextField txtBenutzerName;
	@FXML
	private TextField txtPasswort;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	
	@FXML
	private void onOK() {
		
			//gewisse Buttons werden sichtbar, wenn die Anmeldung erfolgreich war
			if (isValid()) {
				System.out.println("Anmeldung OK.");
				Stage stage = (Stage) btnCancel.getScene().getWindow();
				stage.close();
				TableViewController.isAdmin=true;
			//TODO: erinnert den Benutzer, dass die Daten falsch sind, schliesst das Fenster
			} 
	}
	
	//kontrolliert, ob die eingegebene Daten richtig sind
	//wenn richtig, liefert true zurück
	private boolean isValid() {
		String bName = txtBenutzerName.getText();
		String passwort = txtPasswort.getText(); 
		try {
		if (Dao.readBenutzerName(bName).equals(bName)
			&&	Dao.readPasswort(passwort).equals(passwort)){
			return true;
		} else {
			return false;
		}
		} catch (Exception e) {
			MessageBox.show("Anmeldung fehlgeschlagen", "Benutzername oder Passwort falsch.");
			Stage stage = (Stage) btnCancel.getScene().getWindow();
			stage.close();
			e.printStackTrace();
			return false;
		}
	}
	@FXML
	private void onClose(){
	    // managiert das Stage
	    Stage stage = (Stage) btnCancel.getScene().getWindow();
	    // beendet das Stage
	    stage.close();
	}
	
	
	
	

}
