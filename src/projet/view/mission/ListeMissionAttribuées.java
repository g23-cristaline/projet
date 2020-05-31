package projet.view.mission;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import jfox.javafx.view.IManagerGui;

public class ListeMissionAttribuées {
	
	// Composants de la vue
	
	@FXML
	private TableView           tableview;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	
	
	//Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMission		modelMission;
	
	//Initialize
	
	@FXML
	public void initialize() {
		modelMission.actualiserListe();
		tableview.setItems(modelMission.getListe());
	}
	

}
