package projet.view.equipe;

import java.util.ArrayList;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.dao.DaoEquipeAttente;
import projet.dao.DaoParticipant;
import projet.data.Equipe;
import projet.data.Mission;
import projet.data.Participant;
import projet.data.Personne;

public class ModelEquipe {
	
	@Inject 
	DaoEquipeAttente  daoequipeattente;
	
	private Equipe equipe;
	private ArrayList<Participant> participantcourant=new ArrayList<Participant>();
	
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	private final ObservableList<Equipe> listeequipe = FXCollections.observableArrayList();

	public DaoEquipeAttente getDaoEquipeAttente() {
		return daoequipeattente;
	}

	public ObservableList<Equipe> getListeequipe() {
		return listeequipe;
	}
	
	
	public void actualiser() {
		listeequipe.setAll(daoequipeattente.listerTout());
	}
	public void preparerModifier( Participant item ) {
	//mapper.update( courant, daoparticipant.retrouver( item.getId() ) );
	}
	public void suppression() {
	//	daoequipeattente.supprimer(equipe.getId());
	}
	public void modifier() {
	//	daoequipeattente.modifier(equipe);
	}
	public void retrouver(Equipe equipe) {
		
		participantcourant=(ArrayList<Participant>) daoequipeattente.retrouverParticipantAttente(equipe.getId());
		
	}
	

	public ArrayList<Participant> getParticipantcourant() {
		return participantcourant;
	}

	public void setParticipantcourant(ArrayList<Participant> participantcourant) {
		this.participantcourant = participantcourant;
	}
	
	
	

}
