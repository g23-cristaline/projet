package projet.view.mail;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jfox.javafx.view.IManagerGui;
import projet.view.responsable.ModelResponsable;

public class ControllerMail {
	
	
	// Composants de la vue
	
	@FXML
	private TextField	textFieldAdresse;
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
	
	
	// Initialisations
	
	@FXML
	private void initialize() {
		textFieldAdresse.setText(modelresponsable.getMail());
		
	}
	
	
	// Actions
	
	@FXML
	private void doEnvoyerMessage() {
		
		managerGui.execTask( () -> {
			serviceMail.send( 
					textFieldAdresse.getText(),
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
