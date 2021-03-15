package verwaltung.program;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import verwaltung.common.MessageBox;
import verwaltung.db.Dao;
import verwaltung.repository.Bewertung;
import verwaltung.repository.Dienstleister;
import verwaltung.repository.FirmenAdresse;
import verwaltung.repository.User;
//Controllerklasse fürs Main-View
public class TableViewController{
	
	
	
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnInfo;
	@FXML
	private Button btnNew;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private TextField txtSearch;
	@FXML
	private TableView<Dienstleister> tblDienstleistern;
	@FXML
    private TableColumn<Dienstleister, String> name;
    @FXML
    private TableColumn<Dienstleister, String> beruf;
    @FXML
    private TableColumn<Dienstleister, String> email;
    @FXML
    private TableColumn<Dienstleister, String> telefon;
    
    private ObservableList<Dienstleister> oblist;
    
    private FilteredList<Dienstleister> filteredData;
    
    SortedList<Dienstleister> sortedData;
    
    private ObjectProperty<Dienstleister> selectedDienstleister = new SimpleObjectProperty<>();
    
    public static boolean isUser = false;
    
    private static int userId;
    
	private static int dienstleisterId;
	
	public static int getUserId() {
		return userId;
	}
	public static void setUserId(int userId) {
		TableViewController.userId = userId;
	}
	public static int getDienstleisterId() {
		return dienstleisterId;
	}
	public static void setDienstleisterIid(int dienstleisterIid) {
		TableViewController.dienstleisterId = dienstleisterIid;
	}
	
	
	@FXML
	private void initialize() {
	
		//übergibt die Daten aus der DatenBank der Observablelist
		createTableView();
		//die nur einem Benutzer erreichbare Funktionen verstecken
		setVisible(isUser);
		

	}	
	//öffnet das Fenster, um ein neues Dienstleister Objekt zu herstellen und in der Datanbank speichern
	@FXML
	public void neu() {
		EditDienstleisterWindow editW = new EditDienstleisterWindow();
		Dienstleister result = editW.showWindow();
		if (result != null) {
			Dao.saveDienstleister(result);
			System.out.println("Dienstleister wird zur Datenbank gefügt: " + result);
			MessageBox.show("Info", "Mitarbeiter " + result.toString() 
				+ " wurde zur Datenbank gefügt. \n Nach Neu Öffnen wird sichtbar" );
			createTableView();
		} else {
			// es wurde Abbrechen geklickt
			System.out.println("Cancelled");
		}
	}
	//einen schon gespeicherten Dienstleister-Objekt ändern
	@FXML
	public void editDienstleister() {
		Dienstleister editDienstleister = selectedDienstleister.get();
		dienstleisterId = editDienstleister.getServiceId();
		if (editDienstleister!= null) {
			EditDienstleisterWindow wd = new EditDienstleisterWindow(editDienstleister);
			Dienstleister result = wd.showWindow();
			if (result != null) {
				
				Dao.editDienstleister(result);
				System.out.println("Daten vom " + result + "sind geändert.");
				createTableView();
			}
		}
	}
	//löscht einen Dienstleister
	@FXML
	public void delete() {
		Dienstleister del =selectedDienstleister.get();
		Dao.deleteDienstleister(del);
		System.out.println("Dienestleister " + del.toString() + " wurde gelöscht.");
		createTableView();
	}
	//öffnet das Anmeldung-Pane
	@FXML
	public void login() {
		AnmeldungWindow dlg = new AnmeldungWindow();
		dlg.showWindow();
		setVisible(isUser);
		
	}
	//abmelden
	@FXML
	public void logout() {
		isUser = false;
		userId = 0;
		createTableView();
		setVisible(isUser);
		
	}
	//öffnet das Anmeldung-Fenster, um die erweiterte Datei eines Dienstleisters an zu schauen
	@FXML
	public void info() {
	
		Dienstleister dienstleister = selectedDienstleister.getValue();
		dienstleisterId = dienstleister.getServiceId();
		InfoBlattWindow info = new InfoBlattWindow(dienstleister);
		info.showWindow(dienstleister);
		createTableView();
		
	}
	//setzt die Buttons, die nur ein Dienstleister benutzen kann, sichtbar
	public void setVisible(boolean isUser) {
		
		if (isUser) {
			btnNew.setVisible(true);
			btnEdit.setVisible(true);
			btnDelete.setVisible(true);
			btnLogout.setVisible(true);

			createTableView();
			
		} else {
			btnNew.setVisible(false);
			btnEdit.setVisible(false);
			btnDelete.setVisible(false);
			btnLogout.setVisible(false);
			
		}
		
	}
	
	//diese Methode ist zuständig, um die gebrauchte Dienstleister in dem ListView zu holen
	//und ermöglicht die Suchfunktion
	public void createTableView() {
		
		if (isUser) {
		oblist = FXCollections.observableArrayList(Dao.getAllByUserId(userId));		
		} else  {
		oblist = FXCollections.observableArrayList(Dao.getAll());	
		}
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		beruf.setCellValueFactory(new PropertyValueFactory<>("beruf"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		
		
		tblDienstleistern.getSelectionModel().selectedItemProperty()
		// den jetzt selektierten Eintrag in unserer eigenen Property setzen
		.addListener((o, oldVal, newVal) -> {
			System.out.println("selectedDienstleister: " + newVal);
			selectedDienstleister.set(newVal);
		});
		

		filteredData = new FilteredList<>(oblist, b -> true);
			
			txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(dienstleister -> {
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						String lowerCaseFilter = newValue.toLowerCase();
						
						if (dienstleister.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true;
						} else if (dienstleister.getBeruf().toLowerCase().indexOf(lowerCaseFilter) != -1)  
							return true;
							else 
							return false;
					});
			});
			
		sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tblDienstleistern.comparatorProperty());
		tblDienstleistern.setItems(sortedData);
		
		
		System.out.println(userId);
		System.out.println(isUser);
		} 
			
		
		
	}
	




