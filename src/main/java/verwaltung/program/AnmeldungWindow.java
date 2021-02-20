package verwaltung.program;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.common.FxmlSource;


public class AnmeldungWindow {
	
	



	public void showWindow() {
		FxmlSource<AnmeldungController> source = new FxmlSource<AnmeldungController>(
				"/verwaltung/views/AnmeldungWindow.fxml", null); 
		
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(source.getRoot(), 300, 150));
		
		stage.setTitle("Anmelden");
//		stage.initModality(Modality.APPLICATION_MODAL);
		
		//
		source.getController();
		// zeigt das Fenster an und kehrt sofort zurück
//		stage.show();
		
		// blockiert, bis das Fenster geschlossen wurde
		stage.showAndWait();
		System.out.println("Anmeldung-Fenster öffnet sich.");
	}

}
