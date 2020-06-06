package projet.view.equipe;

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
import projet.view.test.ModelParticipant;


public class ControllerDaoEquipeDetail {
	
	@Inject
	private ModelEquipe modelequipe;
	@Inject
	private IManagerGui managergui;
	@Inject 
	ModelParticipant modelparticipant;
	
	
	
	// Composants visuales
	
	
	@FXML
	private TextField nom;
	@FXML
	private TextField nom2;
	@FXML
	private TextField adresse;
	@FXML
	private TextField adresse2;
	@FXML
	private TextField telephone;
	@FXML
	private TextField telephone2;
	@FXML
	private TextField id;
	@FXML
	private TextField id2;
	@FXML
	private TextField id_equipe;
	@FXML
	private TextField id_equipe2;
	@FXML
	private TextField mail;
	@FXML
	private TextField mail2;
	@FXML
	private TextField naissance;
	@FXML
	private TextField naissance2;
	
	@FXML
	public void initialize() {
		modelequipe.actualiser();
		modelparticipant.actualiser();
		nom.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).nom_completProperty());
		adresse.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).adresseProperty());
		telephone.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).telephoneProperty());
		id.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).idProperty(),new ConverterStringInteger());
		id_equipe.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).getEquipe().nomProperty());
		mail.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).mailProperty());
		naissance.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).date_naissanceProperty(), new ConverterStringLocalDate());
	    
		nom2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).nom_completProperty());
		adresse2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).adresseProperty());
		telephone2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).telephoneProperty());
		id2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).idProperty(),new ConverterStringInteger());
		id_equipe2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).getEquipe().nomProperty());
		mail2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).mailProperty());
		naissance2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).date_naissanceProperty(), new ConverterStringLocalDate());
	}
	
	@FXML
	private void doModifier() {
		managergui.showView( EnumView.DetailParticipantModifiable );
		
	}
	
	@FXML
	private void doSupprimer() {
		modelequipe.supprimer();
		modelequipe.actualiser();
		managergui.showView( EnumView.ListeEquipeAttente );


		
	}
	@FXML
	private void doValider() {
		
		
	 modelequipe.Inserer();
	 modelequipe.supprimer();
	 managergui.showView( EnumView.ListeEquipeAttente );
	 
		
	}
	
	
	
	
	
	
}
