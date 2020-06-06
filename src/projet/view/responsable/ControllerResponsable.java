package projet.view.responsable;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.view.IManagerGui;
import projet.data.Mission;
import projet.data.Responsable;
import projet.view.EnumView;


public class ControllerResponsable {
	@Inject
	private IManagerGui			managerGui;
	@Inject 
	ModelResponsable modelresponsable;
	@FXML
	ListView administrateurs;
	@FXML
	ListView membres;
	@FXML
	ListView externes;
	@FXML
	public void initialize() {
		modelresponsable.actualiser();
		administrateurs.setItems(modelresponsable.getAdministrateurs());
		membres.setItems(modelresponsable.getMembres());
		externes.setItems(modelresponsable.getExternes());
		
		
	}
	@FXML
	public void ajouter() {
		
		managerGui.showView(EnumView.ResponsableForm);
	}
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			
			if (event.getClickCount() == 2) {

					switch(((ListView)event.getSource()).getId()) {
					case "administrateurs":
						if ( administrateurs.getSelectionModel().getSelectedIndex() == -1 ) {
							managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
						}else {
						doModifier(administrateurs);
						System.out.println("administrateur");
						}
						break;
					case "membres":
						if ( membres.getSelectionModel().getSelectedIndex() == -1 ) {
							managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
						}else {
							doModifier(membres);
						System.out.println("membres");
						}
						break;
					case "externes":
						if ( externes.getSelectionModel().getSelectedIndex() == -1 ) {
							managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
						}else {
							doModifier(externes);
						System.out.println("externes");
						}
						break;
					}
					
				}
			}
		
	}
	public void modifierCourant() {
		membres.getSelectionModel().selectedItemProperty().addListener(observer->{
			
		});
	}
	@FXML
	private void doModifier(ListView <Responsable>listView) {
		modelresponsable.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.ResponsableDetails );
	}
}
