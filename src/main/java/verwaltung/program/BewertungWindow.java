package verwaltung.program;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.common.FxmlSource;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
//öffnet die BewertungFenster
public class BewertungWindow {
	

	
	public void showWindow() {
		//FXML-Quelle
		FxmlSource<BewertungController> source = new FxmlSource<BewertungController>(
				"/verwaltung/views/BewertungWindow.fxml", null); 
		
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(source.getRoot(), 500, 400));
		
		stage.setTitle("Bewerten");

		source.getController();
		
		stage.showAndWait();
			
	}



	
}
