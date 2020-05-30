package projet.view.participantattente;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.dao.DaoParticipant;
import projet.dao.DaoParticipantAttente;
import projet.data.Mission;
import projet.data.Participant;
import projet.data.Personne;

public class ModelParticipantAttente {
	
	@Inject 
	DaoParticipant  daoparticipant;
	@Inject
	DaoParticipantAttente daoparticipantattente;
	
	private Participant participant;
	
	
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	private final ObservableList<Participant> listeparticipant = FXCollections.observableArrayList();

	public DaoParticipant getDaoparticipant() {
		return daoparticipant;
	}

	public void setDaoparticipant(DaoParticipant daoparticipant) {
		this.daoparticipant = daoparticipant;
	}

	public ObservableList<Participant> getListeparticipant() {
		return listeparticipant;
	}
	
	
	public void actualiser() {
		listeparticipant.setAll(daoparticipantattente.listerTout());
	}
	public void preparerModifier( Participant item ) {
	//mapper.update( courant, daoparticipant.retrouver( item.getId() ) );
	}
	
	
	public void deplacer() {
		daoparticipant.inserer(daoparticipantattente.retrouver(participant.getId()));
		suppression();
	}
	public void suppression() {
		daoparticipantattente.supprimer(participant.getId());
	}
	

	
	

}
