package verwaltung.program;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.common.FxmlSource;
import verwaltung.repository.Dienstleister;

public class EditDienstleisterWindow {
	
	private Dienstleister dienstleister;
	//Konstruktor um neuen Dienstleister her zu stellen
	public EditDienstleisterWindow() {
	}
	
	//Konstruktor um Dienstleister zu bearbeiten
	public EditDienstleisterWindow(Dienstleister dienstleister) {
		this.dienstleister = dienstleister;
	}
	
	public Dienstleister getDienstleister() {
		return dienstleister;
	}

	public void setDienstleister(Dienstleister dienstleister) {
		this.dienstleister = dienstleister;
	}

	public Dienstleister showWindow() {
		FxmlSource<EditDienstleisterController> source = new FxmlSource<EditDienstleisterController>(
			"/verwaltung/views/EditDienstleisterView.fxml", null); 
	
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(source.getRoot(), 600, 700));
	
		stage.setTitle("Dienstleister erfassen");
		stage.initModality(Modality.APPLICATION_MODAL);
		// dem Controller den Dienstleister übergeben
		source.getController().setEditDienstleister(dienstleister);
	
		stage.showAndWait();
		// den Dienstleister zurückliefern
		dienstleister = source.getController().getEditDienstleister();
		return dienstleister;
	}
}
