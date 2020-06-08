package projet.view.equipe;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoCategorie;
import projet.data.Participant;
import projet.view.EnumView;
import projet.view.test.ModelParticipant;


public class ControllerDaoEquipeDetail {
	
	@Inject
	private ModelEquipe modelequipe;
	@Inject
	private IManagerGui managergui;
	@Inject 
	ModelParticipant modelparticipant;
	
	
	
	// Composants visuales
	
	
	@FXML
	private TextField nom;
	@FXML
	private TextField nom2;
	@FXML
	private TextField adresse;
	@FXML
	private TextField adresse2;
	@FXML
	private TextField telephone;
	@FXML
	private TextField telephone2;
	@FXML
	private TextField id;
	@FXML
	private TextField id2;
	@FXML
	private TextField id_equipe;
	@FXML
	private TextField id_equipe2;
	@FXML
	private TextField mail;
	@FXML
	private TextField mail2;
	@FXML
	private TextField naissance;
	@FXML
	private TextField naissance2;
	
	@FXML
	public void initialize() {
		modelequipe.actualiser();
		modelparticipant.actualiser();
		nom.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).nom_completProperty());
		adresse.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).adresseProperty());
		telephone.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).telephoneProperty());
		id.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).idProperty(),new ConverterStringInteger());
		id_equipe.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).getEquipe().nomProperty());
		mail.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).mailProperty());
		naissance.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(0).date_naissanceProperty(), new ConverterStringLocalDate());
	    
		nom2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).nom_completProperty());
		adresse2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).adresseProperty());
		telephone2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).telephoneProperty());
		id2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).idProperty(),new ConverterStringInteger());
		id_equipe2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).getEquipe().nomProperty());
		mail2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).mailProperty());
		naissance2.textProperty().bindBidirectional(modelequipe.getParticipantcourant().get(1).date_naissanceProperty(), new ConverterStringLocalDate());
	}
	
	@FXML
	private void doModifier() {
		managergui.showView( EnumView.DetailParticipantModifiable );
		
	}
	
	@FXML
	private void doSupprimer() {
		modelequipe.supprimer();
		modelequipe.actualiser();
		managergui.showView( EnumView.ListeEquipeAttente );


		
	}
	@FXML
	private void doValider() {
	 modelequipe.Inserer();
	 modelequipe.actualiser();
		managergui.showView( EnumView.ListeEquipeAttente );
	}
	@FXML
	private void retour() {
		managergui.showView(EnumView.ListeEquipeAttente);
	}
	@FXML
	public void piece_identite() {
		Path path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-piece_identite.jpg");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-piece_identite.pdf");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-piece_identite.png");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-piece_identite.gif");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-piece_identite.jpeg");
		File file= path.toFile();
		
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void certificat_medical() {
		Path path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-certificat_medical.pdf");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-certificat_medical.jpg");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-certificat_medical.png");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-certificat_medical.gif");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(0).getMail()+"-certificat_medical.jpeg");
		File file= path.toFile();
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void piece_identite1() {
		Path path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-piece_identite.jpg");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-piece_identite.pdf");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-piece_identite.png");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-piece_identite.gif");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-piece_identite.jpeg");
		File file= path.toFile();
		
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void certificat_medical1() {
		Path path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-certificat_medical.pdf");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-certificat_medical.jpg");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-certificat_medical.png");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-certificat_medical.gif");
		if(Files.notExists(path))
			path=Paths.get("C:\\web\\partie web\\uploads\\"+modelequipe.getParticipantcourant().get(1).getMail()+"-certificat_medical.jpeg");
		File file= path.toFile();
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
