package verwaltung.program;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import verwaltung.common.FxmlSource;

//diese Klasse startet die Application
public class TableViewMain extends Application{
	

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		// FXML Quelle
		FxmlSource<TableViewController> source = new FxmlSource<TableViewController>("/verwaltung/views/TableView.fxml",  "/verwaltung/views/styles.css");				
		source.getController();
		Scene scene = new Scene(source.getRoot(), 500, 400);
		// das CSS-Stylesheet laden und setzen
		scene.getStylesheets().add(source.getCssFilename());
		// das ganze anzeigen
		primaryStage.setScene(scene);
		primaryStage.setTitle("Service-Verwaltung");
		primaryStage.show();
		System.out.println("Das Programm läuft...");
	
	
	}
	
	

}
