package projet.view.mission;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import jfox.javafx.view.IManagerGui;
import projet.data.Executer;
import projet.data.Mission;
import projet.data.Responsable;

public class ListeMissionAttribuees {
	
	// Composants de la vue
	
	@FXML
	private TableView<Executer>          tableview;
	@FXML
	private TableColumn<Executer,String>  missions;
	@FXML
	private TableColumn<Executer,String>  responsable;
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
		tableview.setItems(modelMission.getAtm());
		missions.setCellValueFactory(arg -> arg.getValue().getMission().nom_missionProperty());
		responsable.setCellValueFactory(arg -> arg.getValue().getResponsable().nom_completProperty());
		System.out.println(modelMission.getAtm().get(0).getResponsable());
	}
	
	

}
