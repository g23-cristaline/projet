package projet.view.equipe;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoEquipe;
import projet.dao.DaoEquipeAttente;
import projet.dao.DaoParticipant;
import projet.dao.DaoParticipantAttente;
import projet.data.Equipe;
import projet.data.Mission;
import projet.data.Participant;
import projet.data.Personne;

public class ModelEquipe {
	
	@Inject 
	DaoEquipeAttente  daoequipeattente;
	@Inject
	DaoEquipe daoequipe;
	@Inject
	DaoParticipant daoparticipant;
	@Inject
	DaoParticipantAttente daoparticipantattente;
	@Inject
	IMapper mapper;
	
	private int i=0;
	private Equipe equipe;
	private ArrayList<Participant> participantcourant;
	
	@PostConstruct
	public void init() {
		equipe=new Equipe();
		participantcourant=new ArrayList<Participant>();
		participantcourant.add(new Participant());
		participantcourant.add(new Participant());
	}
	
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
	public void suppression(int ideq,int idp1, int idp2) {
		daoparticipantattente.supprimer(idp1);
		daoparticipantattente.supprimer(idp2);
		daoequipeattente.Supprimer(ideq);
	}
	public void modifier() {
	//	daoequipeattente.modifier(equipe);
	}
	public void retrouver() {
		
		participantcourant=(ArrayList<Participant>) daoequipeattente.retrouverParticipantAttente(equipe.getId());
		
	}
	

	public ArrayList<Participant> getParticipantcourant() {
		return participantcourant;
	}

	public void setParticipantcourant(ArrayList<Participant> participantcourant) {
		this.participantcourant = participantcourant;
	}
	public void Inserer() {
			
		i=daoequipe.inserer(equipe);
		int id1=participantcourant.get(0).getEquipe().getId();
		int id2=participantcourant.get(0).getId();
		int id3=participantcourant.get(1).getId();
		participantcourant.get(0).getEquipe().setId(i);
		daoparticipant.inserer(participantcourant.get(0));
		participantcourant.get(1).getEquipe().setId(i);
		daoparticipant.inserer(participantcourant.get(1));
		suppression(id1,id2,id3);
	
	}
	public void supprimer() {
		daoparticipantattente.supprimer(participantcourant.get(0).getId());
		daoparticipantattente.supprimer(participantcourant.get(1).getId());
		
		daoequipeattente.Supprimer(participantcourant.get(0).getEquipe().getId());
		
	}
	
	public void preparerModifier( Equipe item ) {
		mapper.update( equipe, daoequipeattente.retrouverEquipe(item.getId() ) );
		mapper.update(participantcourant.get(0), daoequipeattente.retrouverParticipantAttente(item.getId()).get(0));
		mapper.update(participantcourant.get(1), daoequipeattente.retrouverParticipantAttente(item.getId()).get(1));
	}
	
	
	
	

}
