package projet.view.mission;

import javax.inject.Inject;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoLocalisation;
import projet.dao.DaoMission;
import projet.dao.DaoResponsable;
import projet.data.Compte;
import projet.data.Localisation;
import projet.data.Memo;
import projet.data.Mission;
import projet.data.Responsable;

public class ModelMission {

	

	// Données observables 
	
	private final ObservableList<Mission> liste = FXCollections.observableArrayList(); 
	private final ObservableList<String> type = FXCollections.observableArrayList("m1","m2");
	private final ObservableList<Localisation> listlocal = FXCollections.observableArrayList();
	private final ObservableList<Responsable> listResponsable = FXCollections.observableArrayList();
	
	
	
	
	private  Mission	courant = new Mission();
	
	 
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoMission		daoMission;
    @Inject 
    private DaoLocalisation  daoLocalisation;
    @Inject
    private DaoResponsable   daoResponsable;

	// Getters
	
	public ObservableList<Mission> getListe() {
		return liste;
	}
	public Mission getCourant() {
		return courant;
	}
	public void setCourant(Mission courant) {
		this.courant=courant;
	}

	public ObservableList<String> getType() {
		return type;
	}

	public ObservableList<Localisation> getListlocal() {
		return listlocal;
	}
	
	public ObservableList<Responsable> getListResponsable(){
		return listResponsable;
	}
	
	// Actualisations
	
	

	public void actualiserListe() {
		liste.setAll( daoMission.listerTout() );
		listlocal.setAll(daoLocalisation.listerTout());
		listResponsable.setAll(daoResponsable.listerTout());
 	}
	
	
	
	// Actions
	
	public void preparerAjouter() {
		
		mapper.update( courant, new Mission() );
	}

	
	public void preparerModifier( Mission item ) {
		mapper.update( courant, daoMission.retrouver( item.getId() ) );
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
		
//		if( courant.getLocalisation() == null || courant.getLocalisation().isEmpty() ) {
//			message.append( "\nLa localisation ne doit pas être vide." );
//		} else  if ( courant.getLocalisation().length()< 3 ) {
//			message.append( "\nLa localisation est trop courte : 3 lettres minimum." );
//		} else  if ( courant.getLocalisation().length()> 25 ) {
//			message.append( "\nLa localisation est trop longue : 25 lettres maximum." );
//		}
		
//		if( courant.getType() == null || courant.getType().isEmpty() ) {
//			message.append( "\nLe type ne doit pas être vide." );
//		} else  if ( courant.getType().length()> 100 ) {
//			message.append( "\nL'adresse e-mail est trop longue : 100 maxi." );
//		}
//		
		
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
			mapper.update( courant, UtilFX.findNext( liste, item ) );
		}


		public void detail( Mission item ) {
			daoMission.supprimer( item.getId() );
			mapper.update( courant, UtilFX.findNext( liste, item ) );
		}
	
		public void Attribuer_Mission(Mission m ,Responsable p) {
			daoMission.AttribuerMission(m, p);
		}

}
