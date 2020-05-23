SET search_path TO cristaline;

-- Supprimer toutes les données
DELETE FROM Executer;
DELETE FROM role;
DELETE FROM Mission;
delete from Localisation;
DELETE FROM Participant;
DELETE FROM Equipe;
DELETE FROM Participant_attente;
delete from equipe_attente;
DELETE FROM utilisateur;
DELETE FROM responsable;
DELETE FROM categorie;


--mission

-- Categorie
  
INSERT INTO categorie (idcategorie, libelle ) VALUES 
  (1, 'Signaleurs' ),
  (2, 'Partenaires' ),
  (3, 'Clients' ),
  (4, 'Fournisseurs' ),
  (5, 'Dirigeants' );

ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 6;

--responsable
INSERT INTO "responsable" (id,idcategorie,nom_complet,adresse,permis_conduire,brevet_secourisme,code,telephone,info_supplementaires,date_naissance) 
VALUES (1,2,'Fredericka','UP',FALSE,FALSE,'RBC89YYT9IV','05 38 32 45 88','Lorem ipsum dolor','24/03/21'),
(2,4,'Cadman','LX',FALSE,FALSE,'UVK51RSQ7SM','09 93 73 35 85','Lorem ipsum dolor','24/03/21'),
(3,2,'Venus','ON',FALSE,FALSE,'DGD04JGU5HG','08 00 55 06 37','Lorem ipsum dolor sit amet,','24/03/21'),
(4,4,'Ria','Ontario',FALSE,FALSE,'FDY70VSR9OB','08 45 15 47 11','Lorem ipsum','24/03/21'),
(5,4,'Teegan','A',FALSE,FALSE,'HNB74HQW9GB','05 86 51 54 27','Lorem ipsum dolor sit amet, consectetuer adipiscing','17/04/20'),
(6,2,'Eden','Slaskie',TRUE,FALSE,'SJB12QGQ5SO','06 03 95 02 59','Lorem ipsum dolor sit amet, consectetuer','17/04/20'),
(7,3,'Mallory','Madhya Pradesh',TRUE,TRUE,'ONS66DTP0AK','08 52 38 40 10','Lorem','17/04/20'),
(8,5,'Wynne','Istanbul',TRUE,TRUE,'QLH09FFX2JC','08 87 53 06 55','Lorem ipsum dolor sit amet, consectetuer adipiscing','17/04/20'),
(9,1,'Barclay','Fr',FALSE,FALSE,'YZO53ZQV2SU','07 97 46 81 22','Lorem','17/04/20');

ALTER TABLE responsable ALTER COLUMN id RESTART WITH 10;

--utilisateur
INSERT INTO "utilisateur" (id,pseudo,pass,mail,id_responsable) VALUES (1,'Zachery','QAG84IGG4AY','scelerisque@In.edu',7),
(2,'Geoffrey','DYG69HRD6ZP','libero.est.congue@Maurismagna.ca',2),
(3,'Leila','XGA05BOK0WN','Donec.luctus@euaccumsan.com',6),
(4,'Brooke','MMQ24UMV4GY','neque.sed.sem@egetdictum.co.uk',9),
(5,'Lila','EAM68DXW5KI','commodo@turpisnon.org',1),
(6,'Kasimir','RPJ39JRF3MO','tincidunt.adipiscing.Mauris@Phasellusat.co.uk',4),
(7,'Basil','KEM67GDK6DC','purus.mauris.a@molestie.net',3),
(8,'Maggie','WAG60EGI5BH','vitae.aliquet@enim.org',5),
(9,'Lara','CYH36XJX3RW','amet.ante@metusurnaconvallis.ca',8);
ALTER TABLE utilisateur ALTER COLUMN id RESTART WITH 10;

-- Role

INSERT INTO role (id, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'MEMBRE' ),
  ( 2, 'MEMBRE' ),
  ( 3, 'EXTERNE' );
  
--Equipe_attente

--participant_attente

--equipe

--participant

--execute

  





