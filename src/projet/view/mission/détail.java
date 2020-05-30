package projet.view.mission;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.view.IManagerGui;
import projet.data.Mission;
import projet.view.EnumView;

public class détail {

	
	@Inject
	ModelMission  modelmission;
	@Inject
	private IManagerGui			managerGui;
	
	
	// Composants visuales
	
	@FXML
	private ListView listeview;
	private Mission courant;
	@FXML
	public void initialize() {
//		modelmission.actualiserListe();
//	 listeview.setItems(modelmission.getListe());
	}
	
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(listeview.getSelectionModel().getSelectedIndex()==-1) {
					 managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				}
				
				
				 else {
					 courant=(Mission) listeview.getSelectionModel().getSelectedItem();
					 modelmission.setCourant(courant);
					managerGui.showView( EnumView.DétailMission);
					
				}
				
			}
			
		}
	
	
	}

	public Mission getCourant() {
		return courant;
	}

	public void setCourant(Mission courant) {
		this.courant = courant;
	}
	
}
