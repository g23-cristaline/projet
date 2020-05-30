package projet.view.test;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.dao.DaoParticipant;
import projet.data.Mission;
import projet.data.Participant;
import projet.data.Personne;

public class ModelParticipant {
	
	@Inject 
	DaoParticipant  daoparticipant;
	
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
		listeparticipant.setAll(daoparticipant.listerTout());
	}
	public void preparerModifier( Participant item ) {
	//mapper.update( courant, daoparticipant.retrouver( item.getId() ) );
	}
	public void suppression() {
		daoparticipant.supprimer(participant.getId());
	}
	public void modifier() {
		daoparticipant.modifier(participant);
	}

	
	

}
