package projet.view.mission;

import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.view.IManagerGui;
import projet.data.Mission;
import projet.view.EnumView;

public class ControllerDetailMission {

	
	@Inject
	ModelMission  modelmission;
	@Inject
	private IManagerGui			managerGui;
	
	
	// Composants visuales
	
	@FXML
	private Label nom;
	@FXML
	private Label adresse;
	@FXML
	private Label horaire;
	@FXML
	private Label type;
	private Mission courant;
	@FXML
	public void initialize() {
		courant=modelmission.getCourant();
		nom.textProperty().bindBidirectional(courant.nom_missionProperty());
		adresse.textProperty().bindBidirectional(courant.getLocalisation().positionProperty());
		horaire.setText(courant.getHoraire().format(DateTimeFormatter.ISO_TIME));
		getHeure();
		type.textProperty().bindBidirectional(courant.typeProperty());
	}
	
	@FXML
	public void retour() {
		managerGui.showView(EnumView.MissionListe);
		modelmission.actualiserListe();
	}
	private void getHeure() {
		courant.horaireProperty().addListener((a,n,e)->{
			horaire.setText(n.format(DateTimeFormatter.ISO_TIME));
		});
	}
	
}
