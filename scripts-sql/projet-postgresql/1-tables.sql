------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------
SET search_path TO cristaline;

-- Schéma

DROP SCHEMA IF EXISTS cristaline CASCADE;
CREATE SCHEMA cristaline AUTHORIZATION cristaline;
GRANT ALL PRIVILEGES ON SCHEMA cristaline TO cristaline;


------------------------------------------------------------
-- Table: responsable
------------------------------------------------------------
CREATE TABLE categorie (
	idcategorie		INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (idcategorie)
);
CREATE TABLE  responsable(
	id_responsable                  INT GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
	idcategorie 		INT NOT NULL,
	nom_complet         VARCHAR (100) NOT NULL ,
	adresse             VARCHAR (50) NOT NULL ,
	date_naissance         DATE  NOT NULL ,
	permis_conduire     BOOL  NOT NULL ,
	brevet_secourisme   BOOL  NOT NULL ,
	code                VARCHAR (50) NOT NULL  ,
	telephone 			VARCHAR (25) NOT NULL,
	info_supplementaires TEXT,
	numero_permi           VARCHAR (50) ,
	date_permis            DATE,
	Lieu_Permis            VARCHAR (50),
	CONSTRAINT responsable_PK PRIMARY KEY (id_responsable),
	FOREIGN KEY (idcategorie) REFERENCES categorie(idcategorie)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Utilisateur
------------------------------------------------------------
CREATE TABLE  Utilisateur(
	id               INT GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
	pseudo           VARCHAR (50) NOT NULL ,
	pass             VARCHAR (50) NOT NULL ,
	mail             VARCHAR (100) NOT NULL ,
	id_responsable   INT  NOT NULL  ,
	CONSTRAINT Utilisateur_PK PRIMARY KEY (id)

	,CONSTRAINT Utilisateur_responsable_FK FOREIGN KEY (id_responsable) REFERENCES  responsable(id_responsable)
	,CONSTRAINT Utilisateur_responsable_AK UNIQUE (id_responsable)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Equipe_attente
------------------------------------------------------------
CREATE TABLE  Equipe_attente(
	id             INT GENERATED BY DEFAULT AS IDENTITY NOT NULL  ,
	nom            VARCHAR (50) NOT NULL ,
	categorie      VARCHAR (50) NOT NULL ,
	nombre_repas   INT  NOT NULL ,
	valide         BOOL  NOT NULL ,
	paye           BOOL  NOT NULL  ,
	CONSTRAINT Equipe_attente_PK PRIMARY KEY (id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Participant_attente
------------------------------------------------------------
CREATE TABLE  Participant_attente(
	id                    INT GENERATED BY DEFAULT AS IDENTITY NOT NULL  ,
	nom_complet           VARCHAR (50) NOT NULL ,
	adresse               VARCHAR (50) NOT NULL ,
	mail                  VARCHAR (50) NOT NULL ,
	date_naissance      DATE  NOT NULL ,
	telephone             VARCHAR (25) NOT NULL ,
	id_Equipe_attente     INT  NOT NULL  ,
	CONSTRAINT Participant_attente_PK PRIMARY KEY (id)

	,CONSTRAINT Participant_attente_Equipe_attente_FK FOREIGN KEY (id_Equipe_attente) REFERENCES  Equipe_attente(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Equipe
------------------------------------------------------------
CREATE TABLE  Equipe(
	id             INT GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
	nom            VARCHAR (50) NOT NULL ,
	categorie      VARCHAR (50) NOT NULL ,
	nombre_repas   INT  NOT NULL ,
	valide         BOOL  NOT NULL ,
	paye           BOOL  NOT NULL  ,
	CONSTRAINT Equipe_PK PRIMARY KEY (id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Participant
------------------------------------------------------------
CREATE TABLE  Participant(
	id                    INT GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
	nom_complet           VARCHAR (50) NOT NULL ,
	adresse               VARCHAR (50) NOT NULL ,
	mail                  VARCHAR (50) NOT NULL ,
	date_naissance      DATE  NOT NULL ,
	telephone             VARCHAR (25) NOT NULL ,
	id_Equipe             INT  NOT NULL  ,
	
	CONSTRAINT Participant_PK PRIMARY KEY (id)

	,CONSTRAINT Participant_Equipe_FK FOREIGN KEY (id_Equipe) REFERENCES  Equipe(id)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table : Localisation
------------------------------------------------------------
CREATE TABLE Localisation(
numero     INT NOT NULL,
position   VARCHAR (100) NOT NULL,
CONSTRAINT Localisation_PK PRIMARY KEY (numero)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: Mission
------------------------------------------------------------
CREATE TABLE  Mission(
	id           INT GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
	nom_mission   VARCHAR (50) NOT NULL ,
	horaire        TIME  NOT NULL ,
	numerolocal     INT NOT NULL  ,
	typem          VARCHAR(100) NOT NULL,
	CONSTRAINT Mission_PK PRIMARY KEY (id),
	CONSTRAINT Localisation_FK FOREIGN KEY(numerolocal) REFERENCES Localisation(numero)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Role
------------------------------------------------------------
CREATE TABLE  Role(
	role   VARCHAR (50) NOT NULL , check(role in('ADMINISTRATEUR','MEMBRE','EXTERNE')),
	id     INT  NOT NULL  ,
	CONSTRAINT Role_PK PRIMARY KEY (role,id)

	,CONSTRAINT Role_Utilisateur_FK FOREIGN KEY (id) REFERENCES  Utilisateur(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Execute
------------------------------------------------------------
CREATE TABLE  Executer(
	id_responsable          INT  NOT NULL ,
	id_Mission   INT  NOT NULL  ,
	type 		VARCHAR (50),
	CONSTRAINT Execute_PK PRIMARY KEY (id_responsable,id_Mission)

	,CONSTRAINT Execute_responsable_FK FOREIGN KEY (id_responsable) REFERENCES  responsable(id_responsable)
	,CONSTRAINT Execute_Mission0_FK FOREIGN KEY (id_Mission) REFERENCES  mission(id)
)WITHOUT OIDS;



