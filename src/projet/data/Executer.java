package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Executer {
	private final Property<Mission> mission= new SimpleObjectProperty();
	private final Property<Responsable> responsable= new SimpleObjectProperty();
	public final Property<Mission> missionProperty() {
		return this.mission;
	}
	
	public final Mission getMission() {
		return this.missionProperty().getValue();
	}
	
	public final void setMission(final Mission mission) {
		this.missionProperty().setValue(mission);
	}
	
	public final Property<Responsable> responsableProperty() {
		return this.responsable;
	}
	
	public final Responsable getResponsable() {
		return this.responsableProperty().getValue();
	}
	
	public final void setResponsable(final Responsable responsable) {
		this.responsableProperty().setValue(responsable);
	}
	
	
}
