package projet.view.mission;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import org.postgresql.util.PSQLException;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.view.IManagerGui;
import projet.data.Mission;
import projet.data.Responsable;
import projet.view.EnumView;
import projet.view.responsable.ModelResponsable;

public class AttributionMission {

	@Inject
	private ModelResponsable Modelresponsable;
	@Inject
	private ModelMission Modelmission;
	private Responsable resp;
	private Mission miss;
	@Inject
	private IManagerGui			managerGui;
	
	//Composants de la vue 
	
	@FXML
	private ChoiceBox<Mission>		mission;
	@FXML
	private TextField              type;
	@FXML
	private TextField              localisation;
	@FXML
	private TextField              horaire;
	@FXML
	private CheckBox              permisconduire;
	@FXML
	private CheckBox                Brevet;
	@FXML
	private TextArea              preferences;
	@FXML
	private ChoiceBox<Responsable> responsable;
	
	 
	
	//initialisation
	
	@FXML
	private void initialize() {
	Modelmission.actualiserListe();
	miss = Modelmission.getCourant();
	resp = Modelmission.getRespCourant();
	mission.setItems( Modelmission.getListe());
	responsable.setItems(Modelmission.getListResponsable());
	responsable.valueProperty().addListener(observable->{
		resp = responsable.getSelectionModel().getSelectedItem();
		if(resp.getPermis_conduire()) {
			permisconduire.setSelected(true);
		}else {
			permisconduire.setSelected(false);
		}
		
		Brevet.setSelected(resp.getBrevet_secourisme()?true:false);
		 preferences.setText(resp.getInfo_supplementaires());
			});
	
	
	
	 mission.valueProperty().addListener(observable->{
		 	 miss= mission.getSelectionModel().getSelectedItem();
		 horaire.setText(miss.getHoraire().format(DateTimeFormatter.ISO_TIME));
		 System.out.println(miss.getType());
		 type.setText(miss.getType());
		 localisation.textProperty().bindBidirectional(miss.getLocalisation().positionProperty());}
	 );
	
	 
	}
	
	// Actions
	
			@FXML
			private void doAnnuler() {
				managerGui.showView( EnumView.AttributionMission );
			}
			
			@FXML
			private void doValider() {
				try {
					Modelmission.Attribuer_Mission(miss, resp);
					Alert reuissi = new Alert(AlertType.INFORMATION);
					reuissi.setTitle("Information");
					reuissi.setHeaderText(null);
					reuissi.setContentText("La mission a bien été attribué");
					reuissi.showAndWait();
					
				}catch(Exception e) {
					Alert erreur = new Alert(AlertType.WARNING);
					erreur.setTitle("warning");
					erreur.setHeaderText(null);
					erreur.setContentText("Ce responsable est déja en charge de cette mission");
					erreur.showAndWait();
				}
				
				
			}
}
