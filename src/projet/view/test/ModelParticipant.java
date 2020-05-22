package projet.view.test;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.dao.DaoParticipant;
import projet.data.Participant;
import projet.data.Personne;

public class ModelParticipant {
	
	@Inject 
	DaoParticipant  daoparticipant;
	
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

	
	

}
