package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Localisation {
	
	private final Property<Integer> numero = new SimpleObjectProperty<>();
	private final StringProperty position = new SimpleStringProperty();
	
	
	
	public final Property<Integer> numeroProperty() {
		return this.numero;
	}
	
	public final Integer getNumero() {
		return this.numeroProperty().getValue();
	}
	
	public final void setNumero(final Integer numero) {
		this.numeroProperty().setValue(numero);
	}
	
	public final StringProperty positionProperty() {
		return this.position;
	}
	
	public final String getPosition() {
		return this.positionProperty().get();
	}
	
	public final void setPosition(final String position) {
		this.positionProperty().set(position);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(numero.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localisation other = (Localisation) obj;
		return Objects.equals(numero.getValue(), other.numero.getValue() );
	}

	@Override
	public String toString() {
		return   position.get() ;
	}
	
	
}
