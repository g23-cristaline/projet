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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.view.IManagerGui;
import projet.data.Localisation;
import projet.data.Mission;
import projet.view.EnumView;






public class ControllermissionForm {
	
	

	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldNomMission;
	@FXML
	private TextField			textFieldHoraire;
	@FXML
	private ChoiceBox<String>		listviewType;
	@FXML
	private ChoiceBox<Localisation> localisation;
	
	

	// Autres champs

	 private Mission 				courant;
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMission		modelMission;
	
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
		modelMission.actualiserListe();
		courant = modelMission.getCourant();
		

		// Data binding
		
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger());
		textFieldNomMission.textProperty().bindBidirectional( courant.nom_missionProperty() );
		//textFieldLocalisation.textProperty().bindBidirectional( courant.localisationProperty() );
		listviewType.setItems(modelMission.getType());
		localisation.setItems(modelMission.getListlocal());
		
		//courant.horaireProperty().bindBidirectional(textFieldHoraire.textProperty(),new ConverterStringLocalDate();
		//textFieldHoraire.setText(courant.getHoraire().toString());
		
		recuptype();
        recuplocal();
	}
	
	// Actions
	
		@FXML
		private void doAnnuler() {
			managerGui.showView( EnumView.MissionListe );
		}
		
		@FXML
		private void doValider() {
			try {
				courant.setHoraire(LocalTime.parse(textFieldHoraire.getText(), DateTimeFormatter.ISO_TIME));
				modelMission.validerMiseAJour();
				modelMission.actualiserListe();
				managerGui.showView( EnumView.MissionListe );
				
			}catch(Exception E) {
				
				Alert erreur = new Alert(AlertType.WARNING);
				erreur.setTitle("warning");
				erreur.setHeaderText(null);
				erreur.setContentText("L'horaire n'est pas dans le bon format");
				erreur.showAndWait();
			}
			
			
		}

		@FXML
		private void recuptype() {
			listviewType.getSelectionModel().selectedIndexProperty().addListener(observable->{courant.setType((String) listviewType.getSelectionModel().getSelectedItem());});
		}
		
		@FXML
		private void recuplocal() {
			localisation.getSelectionModel().selectedIndexProperty().addListener(observable->{courant.setLocalisation( (Localisation) localisation.getSelectionModel().getSelectedItem());});
		}
		
        
}
