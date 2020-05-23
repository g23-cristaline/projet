package projet.view.mission;

import java.awt.Checkbox;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Mission;
import projet.view.EnumView;






public class ControllermissionForm {
	
	

	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldNomMission;
	@FXML
	private TextField			textFieldLocalisation;
	@FXML
	private TextField			textFieldHoraire;
	@FXML
	private ListView		listviewType;
	
	

	// Autres champs

	 private Mission 				courant;
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMission		modelMission;


	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
		courant = modelMission.getCourant();
		

		// Data binding
		courant = modelMission.getCourant();
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger());
		textFieldNomMission.textProperty().bindBidirectional( courant.nom_missionProperty() );
		textFieldLocalisation.textProperty().bindBidirectional( courant.localisationProperty() );
		//textFieldHoraire.textProperty().bindBidirectional( courant.horaireProperty() );
		listviewType.setItems(modelMission.getType());
		recuptype();
        
	}
	
	// Actions
	
		@FXML
		private void doAnnuler() {
			managerGui.showView( EnumView.MissionListe );
		}
		
		@FXML
		private void doValider() {
			
			courant.setHoraire(LocalTime.parse(textFieldHoraire.getText(), DateTimeFormatter.ISO_TIME));
			modelMission.validerMiseAJour();
			modelMission.actualiserListe();
			managerGui.showView( EnumView.MissionListe );
		}

		@FXML
		private void recuptype() {
			listviewType.getSelectionModel().selectedIndexProperty().addListener(observable->{courant.setType((String) listviewType.getSelectionModel().getSelectedItem());});
		}
		
	

}
