package projet.view.participantattente;

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


public class ControllerDaoDetailParticipantAttente {
	
	@Inject
	private ModelParticipantAttente modelparticipantattente;
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
		modelparticipantattente.actualiser();
		nom.textProperty().bindBidirectional(modelparticipantattente.getParticipant().nom_completProperty());
		adresse.textProperty().bindBidirectional(modelparticipantattente.getParticipant().adresseProperty());
		telephone.textProperty().bindBidirectional(modelparticipantattente.getParticipant().telephoneProperty());
		id.textProperty().bindBidirectional(modelparticipantattente.getParticipant().idProperty(),new ConverterStringInteger());
		id_equipe.textProperty().bindBidirectional(modelparticipantattente.getParticipant().getEquipe().nomProperty());
		mail.textProperty().bindBidirectional(modelparticipantattente.getParticipant().mailProperty());
		naissance.textProperty().bindBidirectional(modelparticipantattente.getParticipant().date_naissanceProperty(), new ConverterStringLocalDate());
	}
	
	@FXML
	private void doModifier() {
		managergui.showView( EnumView.DetailParticipantModifiable );
		
	}
	
	@FXML
	private void doSupprimer() {
		modelparticipantattente.suppression();
		modelparticipantattente.actualiser();
//		managergui.showView( EnumView.ListeAttente );
		
	}
	@FXML
	private void doValider() {
		
		modelparticipantattente.deplacer();
		modelparticipantattente.actualiser();
		
		managergui.showView( EnumView.ListeParticipant );
	}
	
	
	
	
	
	
}
