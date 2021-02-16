package verwaltung.program;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.common.FxmlSource;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;

public class BewertungWindow {
	

	
	public void showWindow() {
		FxmlSource<BewertungController> source = new FxmlSource<BewertungController>(
				"/verwaltung/views/BewertungWindow.fxml", null); 
		
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(source.getRoot(), 600, 500));
		
		stage.setTitle("Bewerten");

		source.getController();
		
		stage.show();
			
	}



	
}
