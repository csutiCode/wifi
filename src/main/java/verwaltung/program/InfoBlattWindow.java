package verwaltung.program;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.common.FxmlSource;
import verwaltung.repository.Dienstleister;

public class InfoBlattWindow {
	
	
	private Dienstleister dienstleister;
	
	public InfoBlattWindow(Dienstleister dienstleister) {
		this.dienstleister=dienstleister;
	}
	

	public void showWindow(Dienstleister dienstleister) {
		FxmlSource<InfoBlattController> source = new FxmlSource<InfoBlattController>(
				"/verwaltung/views/InfoBlattWindow.fxml", null); 
		
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene(source.getRoot(), 500, 400));
		
		stage.setTitle("Informationen");
		
		source.getController().setDienstleister(dienstleister);

//		source.getController();
		// zeigt das Fenster an und kehrt sofort zurück
		stage.showAndWait();
		System.out.println("Info-Fenster öffnet sich.");
		
	}
}
