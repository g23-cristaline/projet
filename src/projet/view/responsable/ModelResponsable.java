package projet.view.responsable;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoCategorie;
import projet.dao.DaoResponsable;
import projet.dao.DaoRole;
import projet.dao.DaoUtilisateur;
import projet.data.Categorie;
import projet.data.Responsable;

public class ModelResponsable {
	@Inject
	DaoResponsable daoresponsable;
	@Inject
	DaoRole daorole;
	@Inject
	DaoUtilisateur daoutilisateur;
	@Inject 
	DaoCategorie daocategorie;
	@Inject
	IMapper mapper;
	private final ObservableList<Responsable> administrateurs= FXCollections.observableArrayList(); 
	private final ObservableList<Responsable> membres= FXCollections.observableArrayList();
	private final ObservableList<Responsable> externes= FXCollections.observableArrayList();
	private final ObservableList<Categorie> categorie= FXCollections.observableArrayList();
	private Responsable courant=new Responsable();
	public void inserer(Responsable responsable) {
		daoresponsable.inserer(responsable,"membre");
	}
	
	public void actualiser() {
		administrateurs.setAll(daorole.listeResponsableParRole("ADMINISTRATEUR"));
		membres.setAll(daorole.listeResponsableParRole("MEMBRE"));
		externes.setAll(daorole.listeResponsableParRole("EXTERNE"));
		categorie.setAll(daocategorie.listerTout());
	}
	public void preparerAjouter(Responsable courant) {
		mapper.update(courant, new Responsable());
	}
	

	public Responsable getCourant() {
		return courant;
	}
	public void preparerModifier(Responsable item) {
		mapper.update(courant, daoresponsable.retrouver( item.getId() ));
	}

	public void setCourant(Responsable courant) {
		this.courant = courant;
	}

	public ObservableList<Categorie> getCategorie() {
		return categorie;
	}

	public ObservableList<Responsable> getAdministrateurs() {
		return administrateurs;
	}


	public ObservableList<Responsable> getMembres() {
		return membres;
	}


	public ObservableList<Responsable> getExternes() {
		return externes;
	}
	public String getMail() {
	return	daoutilisateur.retrouver(daoresponsable.retrouver(courant.getId()).getId()).getMail();
	}
	public String getMail(Responsable resp) {
		return	daoutilisateur.retrouver(daoresponsable.retrouver(resp.getId()).getId()).getMail();
	}
	
	
}
