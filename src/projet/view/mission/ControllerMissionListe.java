package projet.view.mission;

import java.time.LocalTime;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Localisation;
import projet.data.Mission;
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
	private Mission courant;
	
	private détail		detail;

	//Initialize
	
	@FXML
	public void initialize() {
		
		courant = modelMission.getCourant();
		modelMission.actualiserListe();
		listvieww.setItems(modelMission.getListe());
		

		// Configuraiton des boutons
		listvieww.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();
	
		
	
	}
	
	private void refresh() {
		modelMission.actualiserListe();
		UtilFX.selectInListView( listvieww, modelMission.getCourant() );
		listvieww.requestFocus();
	}
	
	// Actions

		@FXML
		private void doAjouter() {
			modelMission.preparerAjouter();
			managerGui.showView( EnumView.MissionForm );
		}

		@FXML
		private void doModifier() {
			modelMission.preparerModifier((Mission) listvieww.getSelectionModel().getSelectedItem());
			System.out.println(modelMission.getCourant());
			managerGui.showView( EnumView.MissionForm );
			
		}

		@FXML
		private void doSupprimer() {
			if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
				modelMission.supprimer( (Mission) listvieww.getSelectionModel().getSelectedItem() );
				refresh();
			}
		}
		

		

		// Gestion des évènements
		// Clic sur la liste
		@FXML
		private void gererClicSurListe( MouseEvent event ) {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				if (event.getClickCount() == 2) {
					if ( listvieww.getSelectionModel().getSelectedIndex() == -1 ) {
						managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
					} else {
						courant=(Mission) listvieww.getSelectionModel().getSelectedItem();
						 modelMission.setCourant(courant);
						managerGui.showView( EnumView.DétailMission);
					}
				}
			}
		}
		
		// Méthodes auxiliaires
		
		private void configurerBoutons() {
	    	if( listvieww.getSelectionModel().getSelectedItems().isEmpty() ) {
				buttonModifier.setDisable(true);
				buttonSupprimer.setDisable(true);
			} else {
				buttonModifier.setDisable(false);
				buttonSupprimer.setDisable(false);
			}
		}

		@FXML
		private void recuphoraire() {
			listvieww.getSelectionModel().selectedIndexProperty().addListener(observable->{modelMission.setCourant((Mission) listvieww.getSelectionModel().getSelectedItem());
			System.out.println(modelMission.getCourant());});
		}
		


}
