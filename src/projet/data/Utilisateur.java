package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {
	
	//données observables
	
	private final Property<Integer>		id		= new SimpleObjectProperty<>();
	private final StringProperty	pseudo		= new SimpleStringProperty();
	private final StringProperty	motDePasse	= new SimpleStringProperty();
	
	
   // Constructeurs
	
	public Utilisateur() {
	}

	public Utilisateur( int id, String pseudo, String motDePasse) {
		setId(id);
		setPseudo(pseudo);
		setMotDePasse(motDePasse);
	}

   // Getters and Setters
	
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
		return this.pseudoProperty().getValue();
	}

	public final void setPseudo(final String pseudo) {
		this.pseudoProperty().setValue(pseudo);
	}

	public final StringProperty motDePasseProperty() {
		return this.motDePasse;
	}
	
	public final String getMotDePasse() {
		return this.motDePasseProperty().getValue();
	}

	public final void setMotDePasse(final String motDePasse) {
		this.motDePasseProperty().setValue(motDePasse);
	}
	

	// toString()
	
	@Override
	public String toString() {
		return getPseudo();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	
	
}
