package projet.view.equipe;

import javax.inject.Inject;

import org.eclipse.jdt.internal.compiler.ast.MagicLiteral;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoMemo;
import projet.data.Equipe;
import projet.data.Participant;
import projet.view.EnumView;


public class ControllerDaoEquipeListe {
	@Inject
	ModelEquipe modelequipe;
	@Inject
	private IManagerGui			managerGui;
	
	
	// Composants visuales
	
	@FXML
	private ListView listeview;
	private Equipe courant;
	@FXML
	public void initialize() {
		modelequipe.actualiser();
	 listeview.setItems(modelequipe.getListeequipe());
	}
	
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(listeview.getSelectionModel().getSelectedIndex()==-1) {
					 managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				}
				
				
				 else {
					 try {
						 courant=(Equipe) listeview.getSelectionModel().getSelectedItem();
						 System.out.println(courant);
						 modelequipe.setEquipe(courant);
						 modelequipe.retrouver(courant);
						managerGui.showView( EnumView.DetailEquipe );
					 }catch(Exception e) {
						 managerGui.showDialogError("Aucun participant ou un seul;\n Une équipe doit avoir deux participants");
					 }

					
				}
				
			}
			
		}
	
	
	
	
	
	
	}

	public Equipe getCourant() {
		return courant;
	}

	public void setCourant(Equipe courant) {
		this.courant = courant;
	}
	
}
