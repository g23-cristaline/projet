package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Utilisateur {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty pseudo = new SimpleStringProperty();
	private final StringProperty pass = new SimpleStringProperty();
	private final StringProperty mail = new SimpleStringProperty();
	private final Property<Responsable> responsable = new SimpleObjectProperty<>();
	private final ObservableList<String> roles = FXCollections.observableArrayList();
	//getters et setters
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty pseudoProperty() {
		return this.pseudo;
	}
	
	public final String getPseudo() {
		return this.pseudoProperty().get();
	}
	
	public final void setPseudo(final String pseudo) {
		this.pseudoProperty().set(pseudo);
	}
	
	public final StringProperty passProperty() {
		return this.pass;
	}
	
	public final String getPass() {
		return this.passProperty().get();
	}
	
	public final void setPass(final String pass) {
		this.passProperty().set(pass);
	}
	public boolean isInRole( String role ) {
		
		if ( role != null ) {
			for ( String r : roles ) {
				if ( role.equals( r ) ) {
					return true;
				}
			}
		}
		return false;
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
	

	public final Property<Responsable> responsableProperty() {
		return this.responsable;
	}
	

	public final Responsable getResponsable() {
		return this.responsableProperty().getValue();
	}
	

	public final void setResponsable(final Responsable responsable) {
		this.responsableProperty().setValue(responsable);
	}
	
	
	public ObservableList<String> getRoles() {
		return roles;
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
		Utilisateur other = (Utilisateur) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}
	
		

}
