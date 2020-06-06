package projet.view.mail;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;
import projet.data.Responsable;
import projet.view.responsable.ModelResponsable;
import projet.view.test.ModelParticipant;

public class ControllerDiffusion {
	
	
	// Composants de la vue
	
	@FXML
	private ChoiceBox<String> groupe;
	@FXML
	private TextField	textFieldNom;
	@FXML
	private TextField	textFieldSujet;
	@FXML
	private TextArea	textAreaTexte;
	@FXML
	private TextField	textFieldFichier;
	
	
	// Autres champs
	
	@Inject
	private IManagerGui	managerGui;
	@Inject
	private ServiceMail	serviceMail;
	
	private File		fichier;
	@Inject 
	ModelResponsable modelresponsable;
	@Inject
	ModelParticipant modelparticipant;
	
	// Initialisations
	
	@FXML
	private void initialize() {
		groupe.setItems(serviceMail.getGroupe());
		textFieldNom.textProperty().bindBidirectional(groupe.valueProperty());
	}
	
	
	// Actions
	
	@FXML
	private void doEnvoyerMessage(String adresse) {
		
		managerGui.execTask( () -> {
			serviceMail.send( 
					adresse,
					textFieldNom.getText(),
					textFieldSujet.getText(),
					textAreaTexte.getText(),
					false,
					fichier
					);
		});
		
	}
	
	@FXML
	private void doOuvrirPageWeb() {
		
		try {
			Desktop.getDesktop().browse( new URI( "https://mail.google.com/") );
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException( e );
		}
		
		
	}
	@FXML
	private void Dodiffusion() {
		switch(groupe.getValue())
		{
		case "Administrateur":
			for(Responsable resp:modelresponsable.getAdministrateurs()) {
				doEnvoyerMessage(modelresponsable.getMail(resp));
			}
			break;
		case "Membre":
			for(Responsable resp:modelresponsable.getMembres()) {
				doEnvoyerMessage(modelresponsable.getMail(resp));
			}
			break;
		case "Externe":
			for(Responsable resp:modelresponsable.getExternes()) {
				doEnvoyerMessage(modelresponsable.getMail(resp));
			}
			break;
		case "Participant":
			for(Participant resp:modelparticipant.getListeparticipant()) {
				doEnvoyerMessage(resp.getMail());
			}
			break;
		default:
			for(Responsable resp:modelresponsable.getAdministrateurs()) {
				doEnvoyerMessage(modelresponsable.getMail(resp));
			}
			for(Responsable resp:modelresponsable.getMembres()) {
				doEnvoyerMessage(modelresponsable.getMail(resp));
			}
			for(Responsable resp:modelresponsable.getExternes()) {
				doEnvoyerMessage(modelresponsable.getMail(resp));
			}
			for(Participant resp:modelparticipant.getListeparticipant()) {
				doEnvoyerMessage(resp.getMail());
			}
			break;
		}
	}
	
	@FXML
	private void doParcourir() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier");
		fichier = fileChooser.showOpenDialog( managerGui.getStage() );
		if ( fichier == null ) {
			textFieldFichier.setText( null );
		} else {
			textFieldFichier.setText( fichier.getParent() );
		}
	}


}
