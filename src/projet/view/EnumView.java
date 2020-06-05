package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	Info				( "systeme/ViewInfo.fxml" ),
	Connexion			( "systeme/ViewConnexion.fxml" ),
	CompteListe			( "compte/ViewCompteListe.fxml" ),
	CompteForm			( "compte/ViewCompteForm.fxml" ),
	CategorieListe		( "personne/ViewCategorieListe.fxml" ),
	CategorieForm		( "personne/ViewCategorieForm.fxml" ),
	PersonneListe		( "personne/ViewPersonneListe.fxml" ),
	PersonneForm		( "personne/ViewPersonneForm.fxml" ),
	MemoListe			( "memo/ViewMemoListe.fxml" ),
	MemoForm			( "memo/ViewMemoForm.fxml" ),
	MemoAjoutPersonnes	( "memo/ViewMemoAjoutPersonnes.fxml" ),
	ServiceListe		( "service/ViewServiceListe.fxml" ),
	ServiceForm			( "service/ViewServiceForm.fxml" ),
	TestDaoCategorie	( "test/ViewTestDaoCategorie.fxml" ),
	TestDaoMemo			( "test/ViewTestDaoMemo.fxml" ),
	TestDaoService		( "test/ViewTestDaoService.fxml" ),
	EtatPersonnesParCateogire1	( "personne/ViewEtatPersonnesParCategorie1.fxml" ),
	EtatPersonnesParCateogire2	( "personne/ViewEtatPersonnesParCategorie2.fxml" ),
	NewIdentifiant		( "systeme/ViewCreatId.fxml" ),
	ListeParticipant ("test/ViewTestDaoParticipantListe.fxml"),
	MissionListe			( "mission/ViewMissionListe.fxml" ),
	MissionForm			( "mission/ViewmissionForm.fxml" ),
	ResponsableForm  	("responsable/ViewResponsableForm.fxml"),
	ResponsableList  ("responsable/ViewResponsableListe.fxml"),
<<<<<<< HEAD

	
	
	
	
	
	
	
	

=======
>>>>>>> branch 'master' of https://github.com/g23-cristaline/projet.git
	DétailMission    ("mission/ViewDetailMission.fxml"),
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/g23-cristaline/projet.git
	DetailParticipant ("test/ViewTestDaoParticipant.fxml"),
	DetailParticipantModifiable ("test/ViewTestDaoParticipantmodifiable.fxml"),
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/g23-cristaline/projet.git
	ListeEquipeAttente ("equipe/ViewDaoEquipeListe.fxml"),
	DetailEquipe ("equipe/ViewDaoEquipeDetail.fxml"),
<<<<<<< HEAD

	AttributionMission("mission/ViewAttributionMission.fxml"),

=======
>>>>>>> branch 'master' of https://github.com/g23-cristaline/projet.git

	AttributionMission("mission/ViewAttributionMission.fxml"),
	;

	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
