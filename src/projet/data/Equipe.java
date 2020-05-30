package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Equipe {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom = new SimpleStringProperty();
	private final StringProperty categorie = new SimpleStringProperty();
	private final Property<Integer> nombre_repas = new SimpleObjectProperty<>();
	private final Property<Boolean> valide = new SimpleObjectProperty<>();
	private final Property<Boolean> paye = new SimpleObjectProperty<>();
	
	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	public final StringProperty nomProperty() {
		return this.nom;
	}
	

	public final String getNom() {
		return this.nomProperty().get();
	}
	

	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	

	public final StringProperty categorieProperty() {
		return this.categorie;
	}
	

	public final String getCategorie() {
		return this.categorieProperty().get();
	}
	

	public final void setCategorie(final String categorie) {
		this.categorieProperty().set(categorie);
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
		Equipe other = (Equipe) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	public final Property<Integer> nombre_repasProperty() {
		return this.nombre_repas;
	}
	

	public final Integer getNombre_repas() {
		return this.nombre_repasProperty().getValue();
	}
	

	public final void setNombre_repas(final Integer nombre_repas) {
		this.nombre_repasProperty().setValue(nombre_repas);
	}
	

	public final Property<Boolean> valideProperty() {
		return this.valide;
	}
	

	public final Boolean getValide() {
		return this.valideProperty().getValue();
	}
	

	public final void setValide(final Boolean valide) {
		this.valideProperty().setValue(valide);
	}
	

	public final Property<Boolean> payeProperty() {
		return this.paye;
	}
	

	public final Boolean getPaye() {
		return this.payeProperty().getValue();
	}
	

	public final void setPaye(final Boolean paye) {
		this.payeProperty().setValue(paye);
	}

	@Override
	public String toString() {
		return  nom.get() ;
	}
	

}
	

	