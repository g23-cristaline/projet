package projet.view.compte;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoUtilisateur;
import projet.data.Utilisateur;


public class ModelUtilisateur {
	
	
	// Données observables 
	
	private final ObservableList<Utilisateur> liste = FXCollections.observableArrayList(); 
	
	private final Utilisateur	courant = new Utilisateur();
	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoUtilisateur		daoUtilisateur;
	
	
	// Getters
	
	public ObservableList<Utilisateur> getListe() {
		return liste;
	}

	public Utilisateur getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoUtilisateur.listerTout() );
 	}
	
	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Utilisateur() );
	}

	
	public void preparerModifier( Utilisateur item ) {
		mapper.update( courant, daoUtilisateur.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getPseudo() == null || courant.getPseudo().isEmpty() ) {
			message.append( "\nLe pseudo ne doit pas être vide." );
		} else 	if ( courant.getPseudo().length() < 3 ) {
			message.append( "\nLe pseudo est trop court : 3 mini." );
		} else  if ( courant.getPseudo().length()> 25 ) {
			message.append( "\nLe pseudo est trop long : 25 maxi." );
		} else 	if ( ! daoUtilisateur.verifierUnicitePseudo( courant.getPseudo(), courant.getId() ) ) {
			message.append( "\nLe pseudo " + courant.getPseudo() + " est déjà utilisé." );
		}
		
		if( courant.getPass() == null || courant.getPass().isEmpty() ) {
			message.append( "\nLe mot de passe ne doit pas être vide." );
		} else  if ( courant.getPass().length()< 3 ) {
			message.append( "\nLe mot de passe est trop court : 3 mini." );
		} else  if ( courant.getPass().length()> 25 ) {
			message.append( "\nLe mot de passe est trop long : 25 maxi." );
		}
		
		if( courant.getMail() == null || courant.getMail().isEmpty() ) {
			message.append( "\nL'adresse e-mail ne doit pas être vide." );
		} else  if ( courant.getMail().length()> 100 ) {
			message.append( "\nL'adresse e-mail est trop longue : 100 maxi." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoUtilisateur.inserer( courant ) );
		} else {
			// modficiation
			daoUtilisateur.modifier( courant );
		}
	}
	
	
	public void supprimer( Utilisateur item ) {
		daoUtilisateur.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}

}
