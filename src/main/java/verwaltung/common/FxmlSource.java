package verwaltung.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


// <T> ist der Platzhalter für den Controller-Typ
public class FxmlSource <T>{
	private String viewName, cssFilename;
	private Parent root;
	private T controller;
	
	public FxmlSource(String viewName, String cssName) {
		super();
		this.viewName = viewName;
		try {
			// das FXML laden  
			FXMLLoader loader = new FXMLLoader(getClass().getResource(viewName));
			root = loader.load();
			controller = loader.getController();

			// den Pfad zur CSS-Datei erstellen
			if(cssName != null && !cssName.isEmpty()) {
				cssFilename = getClass().getResource(cssName).toExternalForm();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IllegalArgumentException("Fehler beim laden des FXML views", e);
		}

	}

	public final String getViewName() {
		return viewName;
	}

	public final String getCssFilename() {
		return cssFilename;
	}

	public final Parent getRoot() {
		return root;
	}

	public final T getController() {
		return controller;
	}
	
	
	
	
	
}
