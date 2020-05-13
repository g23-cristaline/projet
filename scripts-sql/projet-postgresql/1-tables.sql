------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------


SET search_path TO cristaline;

-- Schéma

DROP SCHEMA IF EXISTS cristaline CASCADE;
CREATE SCHEMA cristaline AUTHORIZATION cristaline;
GRANT ALL PRIVILEGES ON SCHEMA cristaline TO cristaline;
------------------------------------------------------------
-- Table: Utilisateur
------------------------------------------------------------
CREATE TABLE  Utilisateur(
	id       SERIAL NOT NULL ,
	pseudo   VARCHAR (50) NOT NULL UNIQUE,
	pass     VARCHAR (50) NOT NULL  ,
	CONSTRAINT Utilisateur_PK PRIMARY KEY (id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Administrateur
------------------------------------------------------------
CREATE TABLE  Administrateur(
	id_Utilisateur   INT  NOT NULL ,
	id               INT  NOT NULL ,
	nom_complet      VARCHAR (100) NOT NULL ,
	mail             VARCHAR (50) NOT NULL ,
	adresse          VARCHAR (50) NOT NULL ,
	pseudo           VARCHAR (50) NOT NULL ,
	pass             VARCHAR (50) NOT NULL  ,
	CONSTRAINT Administrateur_PK PRIMARY KEY (id_Utilisateur,id)

	,CONSTRAINT Administrateur_Utilisateur_FK FOREIGN KEY (id_Utilisateur) REFERENCES  Utilisateur(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Benevoles
------------------------------------------------------------
CREATE TABLE  Benevoles(
	id_Utilisateur      INT  NOT NULL ,
	id                  INT  NOT NULL ,
	nom_complet         VARCHAR (100) NOT NULL ,
	mail                VARCHAR (50) NOT NULL ,
	adresse             VARCHAR (50) NOT NULL ,
	permis_conduire     BOOL  NOT NULL ,
	brevet_secourisme   BOOL  NOT NULL ,
	pseudo              VARCHAR (50) NOT NULL UNIQUE,
	pass                VARCHAR (50) NOT NULL  ,
	CONSTRAINT Benevoles_PK PRIMARY KEY (id_Utilisateur,id)

	,CONSTRAINT Benevoles_Utilisateur_FK FOREIGN KEY (id_Utilisateur) REFERENCES  Utilisateur(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Equipe
------------------------------------------------------------
CREATE TABLE  Equipe(
	id    SERIAL NOT NULL ,
	nom   VARCHAR (50) NOT NULL  ,
	CONSTRAINT Equipe_PK PRIMARY KEY (id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Participants
------------------------------------------------------------
CREATE TABLE  Participants(
	id            SERIAL NOT NULL ,
	nom_complet   VARCHAR (50) NOT NULL ,
	adresse       VARCHAR (50) NOT NULL ,
	mail          VARCHAR (50) NOT NULL ,
	id_Equipe     INT  NOT NULL  ,
	CONSTRAINT Participants_PK PRIMARY KEY (id)

	,CONSTRAINT Participants_Equipe_FK FOREIGN KEY (id_Equipe) REFERENCES  Equipe(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Missions
------------------------------------------------------------
CREATE TABLE  Missions(
	id             SERIAL NOT NULL ,
	nom_missions   VARCHAR (50) NOT NULL  ,
	CONSTRAINT Missions_PK PRIMARY KEY (id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Benevoles internes
------------------------------------------------------------
CREATE TABLE  Benevoles_internes(
	id_Utilisateur      INT  NOT NULL ,
	id_Benevoles        INT  NOT NULL ,
	pseudo              VARCHAR (50) NOT NULL UNIQUE,
	pass                VARCHAR (50) NOT NULL ,
	nom_complet         VARCHAR (100) NOT NULL ,
	mail                VARCHAR (50) NOT NULL ,
	adresse             VARCHAR (50) NOT NULL ,
	permis_conduire     BOOL  NOT NULL ,
	brevet_secourisme   BOOL  NOT NULL ,
	CONSTRAINT Benevoles_internes_PK PRIMARY KEY (id_Utilisateur,id_Benevoles)

	,CONSTRAINT Benevoles_internes_Benevoles_FK FOREIGN KEY (id_Utilisateur,id_Benevoles) REFERENCES  Benevoles(id_Utilisateur,id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Benevoles externes
------------------------------------------------------------
CREATE TABLE  Benevoles_externes(
	id_Utilisateur      INT  NOT NULL ,
	id_Benevoles        INT  NOT NULL ,
	code                VARCHAR (50) NOT NULL UNIQUE,
	pseudo              VARCHAR (50) NOT NULL ,
	pass                VARCHAR (50) NOT NULL ,
	nom_complet         VARCHAR (100) NOT NULL ,
	mail                VARCHAR (50) NOT NULL ,
	adresse             VARCHAR (50) NOT NULL ,
	permis_conduire     BOOL  NOT NULL ,
	brevet_secourisme   BOOL  NOT NULL ,
	infos_pratiques     VARCHAR (2000)  NOT NULL  ,
	CONSTRAINT Benevoles_externes_PK PRIMARY KEY (id_Utilisateur,id_Benevoles)

	,CONSTRAINT Benevoles_externes_Benevoles_FK FOREIGN KEY (id_Utilisateur,id_Benevoles) REFERENCES  Benevoles(id_Utilisateur,id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Localisation
------------------------------------------------------------
CREATE TABLE  Localisation(
	id         SERIAL NOT NULL ,
	Numero     INT  NOT NULL ,
	position   VARCHAR (10) NOT NULL  ,
	CONSTRAINT Localisation_PK PRIMARY KEY (id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: gerer
------------------------------------------------------------
CREATE TABLE  gerer(
	id_Equipe                  INT  NOT NULL ,
	id_Utilisateur_Benevoles   INT  NOT NULL ,
	id_Benevoles               INT  NOT NULL  ,
	CONSTRAINT gerer_PK PRIMARY KEY (id_Equipe,id_Utilisateur_Benevoles,id_Benevoles)

	,CONSTRAINT gerer_Equipe_FK FOREIGN KEY (id_Equipe) REFERENCES  Equipe(id)
	,CONSTRAINT gerer_Benevoles0_FK FOREIGN KEY (id_Utilisateur_Benevoles,id_Benevoles) REFERENCES  Benevoles(id_Utilisateur,id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Attribue
------------------------------------------------------------
CREATE TABLE  Attribue(
	id_Utilisateur   INT  NOT NULL ,
	id_Benevole      INT  NOT NULL ,
	id_Missions      INT  NOT NULL  ,
	CONSTRAINT Attribue_PK PRIMARY KEY (id_Utilisateur,id_Benevole,id_Missions)

	,CONSTRAINT Attribue_Benevoles_FK FOREIGN KEY (id_Utilisateur,id_Benevole) REFERENCES  Benevoles(id_Utilisateur,id)
	,CONSTRAINT Attribue_Missions0_FK FOREIGN KEY (id_Missions) REFERENCES  Missions(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: positionner
------------------------------------------------------------
CREATE TABLE  positionner(
	id_Utilisateur    INT  NOT NULL ,
	id_Benevole       INT  NOT NULL ,
	id_Localisation   INT  NOT NULL  ,
	CONSTRAINT positionner_PK PRIMARY KEY (id_Utilisateur,id_Benevole,id_Localisation)

	,CONSTRAINT positionner_Benevoles_FK FOREIGN KEY (id_Utilisateur,id_Benevole) REFERENCES  Benevoles(id_Utilisateur,id)
	,CONSTRAINT positionner_Localisation0_FK FOREIGN KEY (id_Localisation) REFERENCES  Localisation(id)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: diriger
------------------------------------------------------------
CREATE TABLE  diriger(
	id_Administrateur               INT  NOT NULL ,
	id_Benevole	                    INT  NOT NULL ,
	id_Utilisateur_Administrateur   INT  NOT NULL ,
	id_Utilisateur_Benevole              INT  NOT NULL  ,
	CONSTRAINT diriger_PK PRIMARY KEY (id_Administrateur,id_Benevole,id_Utilisateur_Administrateur,id_Utilisateur_Benevole)
	
	,CONSTRAINT diriger_Administeur_FK FOREIGN KEY (id_Administrateur,id_Utilisateur_Administrateur) REFERENCES  Administrateur(id,id_Utilisateur)
	,CONSTRAINT diriger_Benevoles0_FK FOREIGN KEY (id_Benevole,id_Utilisateur_Benevole) REFERENCES  Benevoles(id,id_Utilisateur)
)WITHOUT OIDS;


