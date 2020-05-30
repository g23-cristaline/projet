package projet.view.systeme;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Utilisateur;
import projet.view.EnumView;


public class ControllerConnexion {
	

	// Composants de la vue
	
	@FXML
	private TextField		fieldPseudo;
	@FXML
	private PasswordField	fieldMotDePasse;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;
	@Inject
	private ModelInfo		modelInfo;
	
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
		// Data binding
		Utilisateur courant = modelConnexion.getCourant();
		fieldPseudo.textProperty().bindBidirectional( courant.pseudoProperty() );
		fieldMotDePasse.textProperty().bindBidirectional( courant.passProperty() );

	}
	
	
	public void refresh() {
		// Ferem la session si elle est ouverte
		if ( modelConnexion.getUtilisateurActif() != null ) {
			modelConnexion.fermerSessionUtilisateur();
		}
	}
	

	// Actions
	
	@FXML
	private void doConnexion() {
		managerGui.execTask( () -> {
			modelConnexion.ouvrirSessionUtilisateur();
			Platform.runLater( () -> {
         			modelInfo.titreProperty().setValue( "Bienvenue" );
        			modelInfo.messageProperty().setValue( "Connexion réussie" );
        			managerGui.showView(EnumView.Info);
            }) ;
		} );
	}
	@FXML
	private void creer_identifiant() {
		Platform.runLater(() ->{

			
			
			
			
			
			
			
//			managerGui.showView(EnumView.NewIdentifiant);
<<<<<<< HEAD
//			managerGui.showView(EnumView.ListeEquipeAttente);
=======
			managerGui.showView(EnumView.AttributionMission);
>>>>>>> branch 'master' of https://github.com/g23-cristaline/projet.git

	//	managerGui.showView(EnumView.MissionListe);
		});
	}
	

}
