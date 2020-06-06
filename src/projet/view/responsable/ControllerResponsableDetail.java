package projet.view.responsable;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.view.IManagerGui;
import projet.data.Responsable;
import projet.view.EnumView;

public class ControllerResponsableDetail {
	@Inject
	ModelResponsable modelresponsable;
	@Inject
	IManagerGui managerGui;
	@FXML
	TextField id;
	@FXML
	Label nom;
	@FXML
	Label categorie;
	@FXML
	Label adresse;
	@FXML
	Label date_naissance;
	@FXML
	Label num_permi;
	@FXML
	Label date_permi;
	@FXML
	Label lieu_permi;
	@FXML
	Label telephone;
	@FXML
	CheckBox permi;
	@FXML
	CheckBox brevet;
	@FXML
	TextArea infos;
	Responsable courant;
	@FXML
	public void initialize() {
		courant=modelresponsable.getCourant();
		id.textProperty().bindBidirectional(courant.idProperty(),new ConverterStringInteger());
		nom.textProperty().bindBidirectional(courant.nom_completProperty());
		categorie.textProperty().bindBidirectional(courant.getCategorie().libelleProperty());
		adresse.textProperty().bindBidirectional(courant.adresseProperty());
		date_naissance.textProperty().bindBidirectional(courant.date_naissanceProperty(),new ConverterStringLocalDate());
		num_permi.textProperty().bindBidirectional(courant.numero_permisProperty());
		date_permi.textProperty().bindBidirectional(courant.date_permisProperty(),new ConverterStringLocalDate());
		lieu_permi.textProperty().bindBidirectional(courant.lieu_permisProperty());
		telephone.textProperty().bindBidirectional(courant.telephoneProperty());
		permi.selectedProperty().bindBidirectional(courant.permis_conduireProperty());
		brevet.selectedProperty().bindBidirectional(courant.brevet_secourismeProperty());
		infos.textProperty().bindBidirectional(courant.info_supplementairesProperty());
		
	}
	@FXML
	public void retour() {
		managerGui.showView( EnumView.ResponsableList);
		System.out.println(courant);
	}
	@FXML
	public void sendMail() {
		managerGui.showView(EnumView.Mail);
	}
}
