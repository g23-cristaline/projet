package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import jfox.javafx.util.UtilFX;
import projet.dao.DaoMemo;


public class ControllerTestDaoParticipantListe {
	@Inject
	ModelParticipant modelparticipant;
	
	// Composants visuales
	
	@FXML
	private ListView listeview;
	@FXML
	public void initialize() {
		modelparticipant.actualiser();
	 listeview.setItems(modelparticipant.getListeparticipant());
	}
	
	
	
	
	
}
