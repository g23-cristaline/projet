package projet.view.mission;

import java.sql.SQLException;

import javax.inject.Inject;

import org.postgresql.util.PSQLException;

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
	//private final ObservableList<AttributionMission> Atm = FXCollections.observableArrayList();
	
	
	
	private  Mission	courant = new Mission();
	private Responsable respCourant = new Responsable();
	 
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
//	public ObservableList<AttributionMission> getAtm(){
//		return Atm;
//	}
	
	// Actualisations
	
	

	public void actualiserListe() {
		liste.setAll( daoMission.listerTout() );
		listlocal.setAll(daoLocalisation.listerTout());
		listResponsable.setAll(daoResponsable.listerTout());
		//Atm.setAll(daoMission.listerTout());
 	}
	
	
	
	// Actions
	
	public Responsable getRespCourant() {
		return respCourant;
	}
	public void setRespCourant(Responsable respCourant) {
		this.respCourant = respCourant;
	}
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
	
		public void Attribuer_Mission(Mission m ,Responsable p) throws SQLException{
			daoMission.AttribuerMission(m, p);
		}

}
