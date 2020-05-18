package projet.view.systeme;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import jfox.start.ManagerGui;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import projet.dao.DaoResponsable;
import projet.dao.DaoUtilisateur;
import projet.data.Utilisateur;
import projet.view.EnumView;

public class ControllerIdentifiant {
	@Inject
	DaoUtilisateur daoUtilisateur;
	@Inject
	ModelConnexion modelConnexion;
	@Inject IManagerGui managerGui;
	@Inject DaoResponsable daoResponsable;
	
	@FXML
	private TextField code;
	@FXML
	private TextField login;
	@FXML
	private TextField pass;
	@FXML
	private TextField mail;
	@FXML
	private TextField confirmation;
	Utilisateur user=new Utilisateur();
	private Alert bteDialog;
	
	@FXML
	public void initialize() {
		user.mailProperty().bindBidirectional(mail.textProperty());
		user.pseudoProperty().bindBidirectional(login.textProperty());
		user.passProperty().bindBidirectional(pass.textProperty());
		bteDialog = new Alert(AlertType.ERROR);
		bteDialog.setTitle("ERROR");
		bteDialog.setHeaderText(null);
	}
	@FXML
	private void creerId() {
		int id=daoUtilisateur.verifCodeIdentification(code.getText());
		if(id!=0) {
			user.setResponsable(daoResponsable.retrouver(id));
			if(!pass.getText().equals(confirmation.getText())) {
				bteDialog.setContentText("les mots de passes sont différents");
				bteDialog.showAndWait();
			}
			else if(pass.getText().length()<6) {
				bteDialog.setContentText("le mot de passe est trop court");
				bteDialog.showAndWait();
			}
			else  
				if(!daoUtilisateur.verifierUnicitePseudo(login.getText(), user.getId())||daoUtilisateur.isIdExistant(code.getText())) {
					bteDialog.setContentText("Cette personne possède déja un identifiant");
					bteDialog.showAndWait();
				}
				else {
				daoUtilisateur.inserer(user);
				Alert ok=new Alert(AlertType.INFORMATION);
				ok.setTitle("INFORMATION");
				ok.setHeaderText(null);
				ok.setContentText("Votre identifiant a bien été crée");
				ok.showAndWait();
				modelConnexion.getCourant().setPseudo(login.getText());
				modelConnexion.getCourant().setPass(pass.getText());
					managerGui.showView(EnumView.Connexion);
			}
				
		}
		else {
			
			bteDialog.setContentText("Votre code d'identification est incorrect");
			bteDialog.showAndWait();
		}
	}
}
