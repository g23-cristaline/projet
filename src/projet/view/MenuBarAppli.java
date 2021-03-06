package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jfox.javafx.view.IManagerGui;
import projet.commun.Roles;
import projet.data.Utilisateur;
import projet.report.EnumReport;
import projet.report.ManagerReport;
import projet.view.systeme.ModelConnexion;


public class MenuBarAppli extends MenuBar {

	
	// Champs
	
	private Menu	menuDonnees;
	private Menu	menuEtats;
	private Menu	menuTests;
	
	private MenuItem itemDeconnecter;

	private MenuItem itemCategories;
	private MenuItem itemUtilisateurs;
	
	@Inject
	private IManagerGui 	managerGui;
	@Inject
	private ManagerReport 	managerReport;
	@Inject
	private ModelConnexion	modelConnexion;	
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {

		
		// Variables de travail
		Menu menu;
		MenuItem item;
		
		
		// Manu Système
		
		menu =  new Menu( "Système" );
		this.getMenus().add(menu);
		
		item = new MenuItem( "Quitter" );
		item.setOnAction(  (e) -> managerGui.exit()  );
		menu.getItems().add( item );

		
		// Manu Données
		
		menu =  new Menu( "Participant" );
		this.getMenus().add(menu);
		menuDonnees = menu;
		
		item = new MenuItem( "Liste des participants" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ListeParticipant )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "Participant en attente" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ListeEquipeAttente )  );
		menu.getItems().add( item );
		
		// Manu Etats
		
		menu =  new Menu( "Responsable" );;
		this.getMenus().add(menu);
		menuEtats = menu;
		item = new MenuItem( "Responsable" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ResponsableList )  );
		menu.getItems().add( item );
		
//		item = new MenuItem( "Personnes par catégorie v1" );
//		item.setOnAction(  (e) ->  
//				managerGui.showDialog( EnumView.EtatPersonnesParCateogire1 ) );
//		menu.getItems().add( item );
//		
//		item = new MenuItem( "Personnes par catégorie v2" );
//		item.setOnAction(  (e) ->  
//				managerGui.showDialog( EnumView.EtatPersonnesParCateogire2 ) );
//		menu.getItems().add( item );
//		
//		item = new MenuItem( "Liste des personnes (PDF)" );
//		item.setOnAction(  (e) ->  
//				managerReport.openFilePdf( EnumReport.PersonnesListeSimple, null ) );
//		menu.getItems().add( item );
//		
//		item = new MenuItem( "Liste des personnes (viewer)" );
//		item.setOnAction(  (e) ->  
//				managerReport.showViewer( EnumReport.PersonnesListeSimple, null ) );
//		menu.getItems().add( item );
//		
//		item = new MenuItem( "Annuaire téléphonique" );
//		item.setOnAction(  (e) ->  
////				managerReport.print( EnumReport.AnnuaireTelephone, null ) );
//				managerReport.showViewer( EnumReport.AnnuaireTelephone, null ) );
//		menu.getItems().add( item );

		
		// Manu Tests
		
		menu =  new Menu( "Tests" );;
		this.getMenus().add(menu);
		menuTests = menu;
		
		item = new MenuItem( "DaoCategorie" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoCategorie )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoMemo" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoMemo )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoService" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoService )  );
		menu.getItems().add( item );


		// Configuration initiale du menu
//		configurerMenu( modelConnexion.getUtilisateurActif() );

		// Le changement du utilisateur connecté modifie automatiquement le menu
		modelConnexion.utilisateurActifProperty().addListener( (obs) -> {
//					Platform.runLater( () -> configurerMenu( modelConnexion.getUtilisateurActif() ) );
				}
			); 
		
	}

	
	// Méthodes auxiliaires
	
	private void configurerMenu( Utilisateur utilisateurActif  ) {

//		itemDeconnecter.setDisable(true);
		
		menuDonnees.setVisible(false);
		itemCategories.setVisible(false);
		itemUtilisateurs.setVisible(false);
		menuEtats.setVisible(false);
		menuTests.setVisible(false);
		menuEtats.setVisible(false);
		
		if( utilisateurActif != null ) {
			itemDeconnecter.setDisable(false);
			if( utilisateurActif.isInRole( Roles.MEMBRE) ||
					utilisateurActif.isInRole(Roles.EXTERNE)) {
				menuDonnees.setVisible(true);
				menuEtats.setVisible(true);
			}
			if( utilisateurActif.isInRole( Roles.ADMINISTRATEUR ) ) {
				menuDonnees.setVisible(true);
				itemCategories.setVisible(true);
				itemUtilisateurs.setVisible(true);
				menuTests.setVisible(true);
			}
		}
	}
	
}
