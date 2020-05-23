package projet.view.mission;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;



public class ControllerMissionListe {
	

	// Composants de la vue

	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	@FXML
	private Button				buttonValider;
	@FXML
	private ListView			listvieww;
    

	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMission		modelMission;
	
	//Initialize
	
	@FXML
	public void initialize() {
		
		//courant = modelMission.getCourant();
		modelMission.actualiserListe();
		listvieww.setItems(modelMission.getListe());
		
	}
	
	// Actions

		@FXML
		private void doAjouter() {
			modelMission.preparerAjouter();
			managerGui.showView( EnumView.MissionForm );
		}

		@FXML
		private void doModifier() {
			modelMission.preparerModifier(null );
			managerGui.showView( EnumView.CompteForm );
		}

		@FXML
		private void doSupprimer() {
			if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
				modelMission.supprimer(null);
				
			}
		}
		

		// Gestion des évènements

		// Clic sur la liste
		@FXML
		private void gererClicSurListe( MouseEvent event ) {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				if (event.getClickCount() == 2) {
					 managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
					} else {
						doModifier();
					}
				}
			}
		
//		// Méthodes auxiliaires
//		
//		private void configurerBoutons() {
//	    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
//				buttonModifier.setDisable(true);
//				buttonSupprimer.setDisable(true);
//			} else {
//				buttonModifier.setDisable(false);
//				buttonSupprimer.setDisable(false);
//			}
//		}



}
