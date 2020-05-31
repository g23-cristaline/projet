package projet.view.responsable;


import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import projet.data.Categorie;
import projet.data.Responsable;

public class ControllerResponsableForm {
	@Inject
	private ModelResponsable modelresponsable;
	@FXML
	private TextField id;
	@FXML
	private TextField nom;
	@FXML
	private ComboBox<Categorie> categorie;
	@FXML
	private TextField adresse;
	@FXML
	private DatePicker date_naissance;
	@FXML
	private ToggleGroup permis_conduire;
	@FXML
	private ToggleGroup brevet_secourisme;
	@FXML
	private TextField telephone;
	@FXML
	private TextArea infos;
	@FXML
	private TextField num_permis;
	@FXML
	private DatePicker date_permis;
	@FXML
	private TextField lieu_permis;
	
	private Responsable courant = new Responsable();
	@FXML
	public void initialize() {
		modelresponsable.actualiser();
		id.textProperty().bindBidirectional(courant.idProperty(),new ConverterStringInteger());
		nom.textProperty().bindBidirectional(courant.nom_completProperty());
		adresse.textProperty().bindBidirectional(courant.adresseProperty());
		date_naissance.getEditor().textProperty().bindBidirectional(courant.date_naissanceProperty(),new ConverterStringLocalDate());
		telephone.textProperty().bindBidirectional(courant.telephoneProperty());
		infos.textProperty().bindBidirectional(courant.info_supplementairesProperty());
		num_permis.textProperty().bindBidirectional(courant.numero_permisProperty());
		date_permis.getEditor().textProperty().bindBidirectional(courant.date_permisProperty(), new ConverterStringLocalDate());
		lieu_permis.textProperty().bindBidirectional(courant.lieu_permisProperty());
		categorie.setItems(modelresponsable.getCategorie());
		permis_conduire.selectedToggleProperty().addListener(observable ->{actualiserResponsable();});
		brevet_secourisme.selectedToggleProperty().addListener(observable ->{actualiserResponsable();});
		categorie.getSelectionModel().selectedIndexProperty().addListener(observable -> 
		{courant.setCategorie(categorie.getSelectionModel().getSelectedItem());});
		actualiserResponsable();
		
	}
	private void actualiserResponsable() {
	    // Modifie le statut en fonction du bouton radio sélectionné 
	    Toggle bouton = permis_conduire.getSelectedToggle();
	    int statut = permis_conduire.getToggles().indexOf( bouton  );
	    courant.setPermis_conduire(statut==0);
	    Toggle bouton1 = brevet_secourisme.getSelectedToggle();
	   int statut1 = brevet_secourisme.getToggles().indexOf(bouton1);
	    courant.setBrevet_secourisme(statut1==0);
	    if(statut==1) {
	    	
	    	num_permis.setDisable(true);
	    	date_permis.setDisable(true);
	    	lieu_permis.setDisable(true);
	    }
	    if(statut==0) {
	    	num_permis.setDisable(false);
	    	date_permis.setDisable(false);
	    	lieu_permis.setDisable(false);
	    }
	    
	    
	}
	public Responsable getCourant() {
		return courant;
	}
	public void setCourant(Responsable courant) {
		this.courant = courant;
	}
	@FXML
	public void valider() {
		modelresponsable.inserer(courant);
	}
}
