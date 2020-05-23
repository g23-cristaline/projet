package projet.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mission {
	private final Property <Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom_mission = new SimpleStringProperty();
	private final Property<LocalTime>	horaire	= new SimpleObjectProperty<>();
	private final StringProperty localisation = new SimpleStringProperty();
	private final StringProperty type = new SimpleStringProperty();
	
	
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nom_missionProperty() {
		return this.nom_mission;
	}
	
	public final String getNom_mission() {
		return this.nom_missionProperty().get();
	}
	
	public final void setNom_mission(final String nom_mission) {
		this.nom_missionProperty().set(nom_mission);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mission other = (Mission) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	public final Property<LocalTime> horaireProperty() {
		return this.horaire;
	}
	

	public final LocalTime getHoraire() {
		return this.horaireProperty().getValue();
	}
	

	public final void setHoraire(final LocalTime horaire) {
		this.horaireProperty().setValue(horaire);
	}
	

	public final StringProperty localisationProperty() {
		return this.localisation;
	}
	

	public final String getLocalisation() {
		return this.localisationProperty().get();
	}
	

	public final void setLocalisation(final String localisation) {
		this.localisationProperty().set(localisation);
	}
	

	public final StringProperty typeProperty() {
		return this.type;
	}
	

	public final String getType() {
		return this.typeProperty().get();
	}
	

	public final void setType(final String type) {
		this.typeProperty().set(type);
	}

	@Override
	public String toString() {
		return   nom_mission.get() ;
	}

	
	

}
