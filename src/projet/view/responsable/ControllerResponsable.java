package projet.view.responsable;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;


public class ControllerResponsable {
	@Inject
	private IManagerGui			managerGui;
	@Inject 
	ModelResponsable modelresponsable;
	@FXML
	ListView administrateurs;
	@FXML
	ListView membres;
	@FXML
	ListView externes;
	
	@FXML
	public void initialize() {
		modelresponsable.actualiser();
		administrateurs.setItems(modelresponsable.getAdministrateurs());
		membres.setItems(modelresponsable.getMembres());
		externes.setItems(modelresponsable.getExternes());
		
		
	}
	@FXML
	public void ajouter() {
		
		managerGui.showView(EnumView.ResponsableForm);
	}
}
