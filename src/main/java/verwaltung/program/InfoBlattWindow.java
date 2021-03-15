package verwaltung.program;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.common.FxmlSource;
import verwaltung.repository.Dienstleister;


//diese Klasse öffnet das InfoFenster
public class InfoBlattWindow {
	
	
	private Dienstleister dienstleister;
	
	public InfoBlattWindow(Dienstleister dienstleister) {
		this.dienstleister=dienstleister;
	}
	

	public void showWindow(Dienstleister dienstleister) {
		FxmlSource<InfoBlattController> source = new FxmlSource<InfoBlattController>(
				"/verwaltung/views/InfoBlattWindow.fxml", "/verwaltung/views/styles.css"); 
		
		Stage stage = new Stage(StageStyle.DECORATED);
		
		stage.setScene(new Scene(source.getRoot(), 550, 600));
	
		stage.setTitle("Dienstleister erfassen");
		stage.initModality(Modality.APPLICATION_MODAL);
	
		
		stage.setTitle("Informationen");
		
		source.getController().setDienstleister(dienstleister);

		stage.showAndWait();
		System.out.println("Info-Fenster öffnet sich.");
			}
}
