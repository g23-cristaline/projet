package projet.view.systeme;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import jfox.commun.exception.ExceptionValidation;
import projet.dao.DaoUtilisateur;
import projet.data.Utilisateur;


public class ModelConnexion {
	
	// Logger
	public static final Logger logger = Logger.getLogger( ModelConnexion.class.getName() );
	
	
	// Données observables 
	
	// Vue connexion
	private final Utilisateur			courant = new Utilisateur();

	// Utilisateur connecté
	private final Property<Utilisateur>	utilisateurActif = new SimpleObjectProperty<>();
	// Autres champs
	@Inject
	private DaoUtilisateur	daoUtilisateur;
	

	// Getters 
	
	public Utilisateur getCourant() {
		return courant;
	}
	
	public Property<Utilisateur> utilisateurActifProperty() {
		return utilisateurActif;
	}
	
	public Utilisateur getUtilisateurActif() {
		return utilisateurActif.getValue();
	}
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {
		courant.setPseudo( "Zachery" );
		courant.setPass( "QAG84IGG4AY" );
	}
	
	
	// Actions


	public void ouvrirSessionUtilisateur() {

		Utilisateur utilisateur = daoUtilisateur.validerAuthentification(
					courant.pseudoProperty().getValue(), courant.passProperty().getValue() );
		
		if( utilisateur == null ) {
			throw new ExceptionValidation( "Pseudo ou mot de passe invalide." );
		} else {
			Platform.runLater( () -> utilisateurActif.setValue( utilisateur ) );
		}
	}
	

	public void fermerSessionUtilisateur() {
		utilisateurActif.setValue( null );
	}

}
