package projet.view.test;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoCategorie;
import projet.data.Participant;
import projet.view.EnumView;


public class ControllerTestDaoParticipant {
	
	@Inject
	private ModelParticipant modelparticipant;
	@Inject
	private IManagerGui managergui;
	
	
	// Composants visuales
	
	@FXML
	private Button	modifier;
	@FXML
	private Button supprimer;
	@FXML
	private TextField nom;
	@FXML
	private TextField adresse;
	@FXML
	private TextField telephone;
	@FXML
	private TextField id;
	@FXML
	private TextField id_equipe;
	@FXML
	private TextField mail;
	@FXML
	private TextField naissance;
	
	@FXML
	public void initialize() {
		modelparticipant.actualiser();
		nom.textProperty().bindBidirectional(modelparticipant.getParticipant().nom_completProperty());
		adresse.textProperty().bindBidirectional(modelparticipant.getParticipant().adresseProperty());
		telephone.textProperty().bindBidirectional(modelparticipant.getParticipant().telephoneProperty());
		id.textProperty().bindBidirectional(modelparticipant.getParticipant().idProperty(),new ConverterStringInteger());
		id_equipe.textProperty().bindBidirectional(modelparticipant.getParticipant().getEquipe().nomProperty());
		mail.textProperty().bindBidirectional(modelparticipant.getParticipant().mailProperty());
		naissance.textProperty().bindBidirectional(modelparticipant.getParticipant().date_naissanceProperty(), new ConverterStringLocalDate());
	}
	
	@FXML
	private void doModifier() {
		managergui.showView( EnumView.DetailParticipantModifiable );
		
	}
	
	@FXML
	private void doSupprimer() {
		modelparticipant.suppression();
		modelparticipant.actualiser();
		managergui.showView( EnumView.ListeParticipant );
		
	}
	@FXML
	private void doValider() {
		
		modelparticipant.modifier();
		modelparticipant.actualiser();
		
		managergui.showView( EnumView.ListeParticipant );
	}
	
	
	
	
	
	
}
