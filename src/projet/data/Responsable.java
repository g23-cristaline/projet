package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Responsable {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom_complet = new SimpleStringProperty();
	private final StringProperty adresse = new SimpleStringProperty();
	private final Property <Boolean> permis_conduire = new SimpleObjectProperty<>();
	private final Property <Boolean> brevet_secourisme = new SimpleObjectProperty<>();
	private final Property <Categorie> categorie = new SimpleObjectProperty<>();
	private final StringProperty code = new SimpleStringProperty();
	private final StringProperty telephone = new SimpleStringProperty();
	private final StringProperty info_supplementaires = new SimpleStringProperty();
	
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
	
	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	
	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	
	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	
	public final Property<Boolean> permis_conduireProperty() {
		return this.permis_conduire;
	}
	

	public final Boolean getPermis_conduire() {
		return this.permis_conduireProperty().getValue();
	}
	

	public final void setPermis_conduire(final Boolean permis_conduire) {
		this.permis_conduireProperty().setValue(permis_conduire);
	}
	

	public final Property<Boolean> brevet_secourismeProperty() {
		return this.brevet_secourisme;
	}
	

	public final Boolean getBrevet_secourisme() {
		return this.brevet_secourismeProperty().getValue();
	}
	

	public final void setBrevet_secourisme(final Boolean brevet_secourisme) {
		this.brevet_secourismeProperty().setValue(brevet_secourisme);
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
		Responsable other = (Responsable) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}


	public final Property<Categorie> categorieProperty() {
		return this.categorie;
	}
	


	public final Categorie getCategorie() {
		return this.categorieProperty().getValue();
	}
	


	public final void setCategorie(final Categorie categorie) {
		this.categorieProperty().setValue(categorie);
	}


	public final StringProperty codeProperty() {
		return this.code;
	}
	


	public final String getCode() {
		return this.codeProperty().get();
	}
	


	public final void setCode(final String code) {
		this.codeProperty().set(code);
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
	


	public final StringProperty info_supplementairesProperty() {
		return this.info_supplementaires;
	}
	


	public final String getInfo_supplementaires() {
		return this.info_supplementairesProperty().get();
	}
	


	public final void setInfo_supplementaires(final String info_supplementaires) {
		this.info_supplementairesProperty().set(info_supplementaires);
	}
	
	
	
	
}
