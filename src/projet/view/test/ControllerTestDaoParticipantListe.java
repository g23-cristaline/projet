package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoMemo;
import projet.data.Participant;
import projet.view.EnumView;


public class ControllerTestDaoParticipantListe {
	@Inject
	ModelParticipant modelparticipant;
	@Inject
	private IManagerGui			managerGui;
	
	
	// Composants visuales
	
	@FXML
	private ListView listeview;
	private Participant courant;
	@FXML
	public void initialize() {
		modelparticipant.actualiser();
	 listeview.setItems(modelparticipant.getListeparticipant());
	}
	
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(listeview.getSelectionModel().getSelectedIndex()==-1) {
					 managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				}
				
				
				 else {
					 courant=(Participant) listeview.getSelectionModel().getSelectedItem();
					 modelparticipant.setParticipant(courant);
					managerGui.showView( EnumView.DetailParticipant );
					
				}
				
			}
			
		}
	
	
	
	
	
	
	}

	public Participant getCourant() {
		return courant;
	}

	public void setCourant(Participant courant) {
		this.courant = courant;
	}
	
}
