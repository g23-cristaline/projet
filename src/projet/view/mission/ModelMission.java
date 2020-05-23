package projet.view.mission;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import projet.commun.IMapper;
import projet.dao.DaoMission;
import projet.data.Compte;
import projet.data.Mission;

public class ModelMission {

	

	// Données observables 
	
	private final ObservableList<Mission> liste = FXCollections.observableArrayList(); 
	
	private final Mission	courant = new Mission();
	
	 
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoMission		daoMission;
    

	// Getters
	
	public ObservableList<Mission> getListe() {
		return liste;
	}

	public Mission getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoMission.listerTout() );
 	}
	
	
	
	// Actions
	
	public void preparerAjouter() {
//		mapper.update( courant, new Mission() );
	}

	
	public void preparerModifier( Mission item ) {
//		mapper.update( courant, daoMission.retrouver( item.getId() ) );
	}
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getNom_mission() == null || courant.getNom_mission().isEmpty() ) {
			message.append( "\nLe nom de la mission ne doit pas être vide." );
		} else 	if ( courant.getNom_mission().length() < 5 ) {
			message.append( "\nLe nom de la mission est trop court : 5 lettres minimun." );
		} else  if ( courant.getNom_mission().length()> 25 ) {
			message.append( "\nLe nom de la mission est trop long : 25 lettres maximum." );
		} 
		
		if( courant.getLocalisation() == null || courant.getLocalisation().isEmpty() ) {
			message.append( "\nLa localisation ne doit pas être vide." );
		} else  if ( courant.getLocalisation().length()< 3 ) {
			message.append( "\nLa localisation est trop courte : 3 lettres minimum." );
		} else  if ( courant.getLocalisation().length()> 25 ) {
			message.append( "\nLa localisation est trop longue : 25 lettres maximum." );
		}
		
		if( courant.getType() == null || courant.getType().isEmpty() ) {
			message.append( "\nLe type ne doit pas être vide." );
		} else  if ( courant.getType().length()> 100 ) {
			message.append( "\nL'adresse e-mail est trop longue : 100 maxi." );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoMission.inserer( courant ) );
		} else {
			// modficiation
			daoMission.modifier( courant ) ;
		}
	
	}
		public void supprimer( Mission item ) {
			daoMission.supprimer( item.getId() );
//			mapper.update( courant, UtilFX.findNext( liste, item ) );
		}

	

}
