SET search_path TO cristaline;


-- Schéma

DROP SCHEMA IF EXISTS cristaline CASCADE;
CREATE SCHEMA cristaline AUTHORIZATION cristaline;
GRANT ALL PRIVILEGES ON SCHEMA cristaline TO cristaline;


-- Tables

--SET search_path TO cristaline;

CREATE TABLE Administrateur(

             id            SERIAL NOT NULL ,

             nom_complet   VARCHAR (100) NOT NULL ,

             mail          VARCHAR (50) NOT NULL ,

             adresse       VARCHAR (50) NOT NULL  ,

             CONSTRAINT Administrateur_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Utilisateur

------------------------------------------------------------

CREATE TABLE Utilisateur(

             id       SERIAL NOT NULL ,

             pseudo   VARCHAR (50) NOT NULL ,

             pass     VARCHAR (50) NOT NULL  ,

             CONSTRAINT Utilisateur_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Benevoles

------------------------------------------------------------

CREATE TABLE Benevoles(

             id            SERIAL NOT NULL ,

             nom_complet   VARCHAR (100) NOT NULL ,

             mail          VARCHAR (50) NOT NULL ,

             adresse       VARCHAR (50) NOT NULL  ,
             
            permis_conduire     BOOL  NOT NULL ,
	brevet_secourisme   BOOL  NOT NULL ,
	infos_pratiques     VARCHAR (2000)  NOT NULL ,
	code                VARCHAR (10) NOT NULL  ,

             CONSTRAINT Benevoles_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Equipe

------------------------------------------------------------

CREATE TABLE Equipe(

             id    SERIAL NOT NULL ,

             nom   VARCHAR (50) NOT NULL  ,

             CONSTRAINT Equipe_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Participants

------------------------------------------------------------

CREATE TABLE Participants(

             id            SERIAL NOT NULL ,

             nom_complet   VARCHAR (50) NOT NULL ,

             adresse       VARCHAR (50) NOT NULL ,

             mail          VARCHAR (50) NOT NULL  ,

             CONSTRAINT Participants_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Missions

------------------------------------------------------------

CREATE TABLE Missions(

             id             SERIAL NOT NULL ,

             nom_missions   VARCHAR (50) NOT NULL  ,

             CONSTRAINT Missions_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Benevoles internes

------------------------------------------------------------

CREATE TABLE Benevoles_internes(

             id            INT  NOT NULL ,

             nom_complet   VARCHAR (100) NOT NULL ,

             mail          VARCHAR (50) NOT NULL ,

             adresse       VARCHAR (50) NOT NULL  ,

             CONSTRAINT Benevoles_internes_PK PRIMARY KEY (id)

 

             ,CONSTRAINT Benevoles_internes_Benevoles_FK FOREIGN KEY (id) REFERENCES  Benevoles(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Benevoles externes

------------------------------------------------------------

CREATE TABLE Benevoles_externes(

             id            INT  NOT NULL ,

             nom_complet   VARCHAR (100) NOT NULL ,

             mail          VARCHAR (50) NOT NULL ,

             adresse       VARCHAR (50) NOT NULL  ,

             CONSTRAINT Benevoles_externes_PK PRIMARY KEY (id)

 

             ,CONSTRAINT Benevoles_externes_Benevoles_FK FOREIGN KEY (id) REFERENCES  Benevoles(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Localisation

------------------------------------------------------------

CREATE TABLE Localisation(

             id         SERIAL NOT NULL ,

             Numero     INT  NOT NULL ,

             position   VARCHAR (10) NOT NULL  ,

             CONSTRAINT Localisation_PK PRIMARY KEY (id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: est

------------------------------------------------------------

CREATE TABLE  est(

             id                  INT  NOT NULL ,

             id_Administrateur   INT  NOT NULL  ,

             CONSTRAINT est_PK PRIMARY KEY (id,id_Administrateur)

 

             ,CONSTRAINT est_Utilisateur_FK FOREIGN KEY (id) REFERENCES  Utilisateur(id)

             ,CONSTRAINT est_Administrateur0_FK FOREIGN KEY (id_Administrateur) REFERENCES  Administrateur(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: peut etre

------------------------------------------------------------

CREATE TABLE  peut_etre(

             id             INT  NOT NULL ,

             id_Benevoles   INT  NOT NULL  ,

             CONSTRAINT peut_etre_PK PRIMARY KEY (id,id_Benevoles)

 

             ,CONSTRAINT peut_etre_Utilisateur_FK FOREIGN KEY (id) REFERENCES  Utilisateur(id)

             ,CONSTRAINT peut_etre_Benevoles0_FK FOREIGN KEY (id_Benevoles) REFERENCES  Benevoles(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: gerer

------------------------------------------------------------

CREATE TABLE  gerer(

             id             INT  NOT NULL ,

             id_Benevoles   INT  NOT NULL  ,

             CONSTRAINT gerer_PK PRIMARY KEY (id,id_Benevoles)

 

             ,CONSTRAINT gerer_Equipe_FK FOREIGN KEY (id) REFERENCES  Equipe(id)

             ,CONSTRAINT gerer_Benevoles0_FK FOREIGN KEY (id_Benevoles) REFERENCES  Benevoles(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: appartient

------------------------------------------------------------

CREATE TABLE  appartient(

             id          INT  NOT NULL ,

             id_Equipe   INT  NOT NULL  ,

             CONSTRAINT appartient_PK PRIMARY KEY (id,id_Equipe)

 

             ,CONSTRAINT appartient_Participants_FK FOREIGN KEY (id) REFERENCES  Participants(id)

             ,CONSTRAINT appartient_Equipe0_FK FOREIGN KEY (id_Equipe) REFERENCES  Equipe(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: Attribue

------------------------------------------------------------

CREATE TABLE  Attribue(

             id            INT  NOT NULL ,

             id_Missions   INT  NOT NULL  ,

             CONSTRAINT Attribue_PK PRIMARY KEY (id,id_Missions)

 

             ,CONSTRAINT Attribue_Benevoles_FK FOREIGN KEY (id) REFERENCES  Benevoles(id)

             ,CONSTRAINT Attribue_Missions0_FK FOREIGN KEY (id_Missions) REFERENCES  Missions(id)

)WITHOUT OIDS;

 

 

------------------------------------------------------------

-- Table: donne

------------------------------------------------------------

CREATE TABLE  donne(

             id                INT  NOT NULL ,

             id_Localisation   INT  NOT NULL  ,

             CONSTRAINT donne_PK PRIMARY KEY (id,id_Localisation)

 

             ,CONSTRAINT donne_Benevoles_FK FOREIGN KEY (id) REFERENCES  Benevoles(id)

             ,CONSTRAINT donne_Localisation0_FK FOREIGN KEY (id_Localisation) REFERENCES  Localisation(id)

)WITHOUT OIDS;

