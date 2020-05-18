package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Participant {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom_complet = new SimpleStringProperty();
	private final StringProperty mail = new SimpleStringProperty();
	private final StringProperty adresse = new SimpleStringProperty();
	private final Property <Equipe> equipe = new SimpleObjectProperty<>();
	private final StringProperty telephone = new SimpleStringProperty();
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nom_completProperty() {
		return this.nom_complet;
	}
	
	public final String getNom_complet() {
		return this.nom_completProperty().get();
	}
	
	public final void setNom_complet(final String nom_complet) {
		this.nom_completProperty().set(nom_complet);
	}
	
	public final StringProperty mailProperty() {
		return this.mail;
	}
	
	public final String getMail() {
		return this.mailProperty().get();
	}
	
	public final void setMail(final String mail) {
		this.mailProperty().set(mail);
	}
	
	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	
	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	
	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	public final Property<Equipe> equipeProperty() {
		return this.equipe;
	}
	

	public final Equipe getEquipe() {
		return this.equipeProperty().getValue();
	}
	

	public final void setEquipe(final Equipe equipe) {
		this.equipeProperty().setValue(equipe);
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
		Participant other = (Participant) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	public final StringProperty telephoneProperty() {
		return this.telephone;
	}
	

	public final String getTelephone() {
		return this.telephoneProperty().get();
	}
	

	public final void setTelephone(final String telephone) {
		this.telephoneProperty().set(telephone);
	}
	
	

}
