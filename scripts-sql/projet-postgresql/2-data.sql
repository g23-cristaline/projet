SET search_path TO cristaline;

-- Supprimer toutes les données
DELETE FROM Executer;
DELETE FROM Participant;
DELETE FROM Equipe;
DELETE FROM Participant_attente;
delete from equipe_attente;
DELETE FROM role;
DELETE FROM utilisateur;
DELETE FROM responsable;
DELETE FROM categorie;
DELETE FROM Mission;

--mission

-- Categorie
  
INSERT INTO categorie (idcategorie, libelle ) VALUES 
  (1, 'Employés' ),
  (2, 'Partenaires' ),
  (3, 'Clients' ),
  (4, 'Fournisseurs' ),
  (5, 'Dirigeants' );

ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 6;

--responsable
INSERT INTO "responsable" (id,idcategorie,nom_complet,adresse,permis_conduire,brevet_secourisme,code,telephone,info_supplementaires) 
VALUES (1,2,'Fredericka','UP',FALSE,FALSE,'RBC89YYT9IV','05 38 32 45 88','Lorem ipsum dolor'),
(2,4,'Cadman','LX',FALSE,FALSE,'UVK51RSQ7SM','09 93 73 35 85','Lorem ipsum dolor'),
(3,2,'Venus','ON',FALSE,FALSE,'DGD04JGU5HG','08 00 55 06 37','Lorem ipsum dolor sit amet,'),
(4,4,'Ria','Ontario',FALSE,FALSE,'FDY70VSR9OB','08 45 15 47 11','Lorem ipsum'),
(5,4,'Teegan','A',FALSE,FALSE,'HNB74HQW9GB','05 86 51 54 27','Lorem ipsum dolor sit amet, consectetuer adipiscing'),
(6,2,'Eden','Slaskie',TRUE,FALSE,'SJB12QGQ5SO','06 03 95 02 59','Lorem ipsum dolor sit amet, consectetuer'),
(7,3,'Mallory','Madhya Pradesh',TRUE,TRUE,'ONS66DTP0AK','08 52 38 40 10','Lorem'),
(8,5,'Wynne','Istanbul',TRUE,TRUE,'QLH09FFX2JC','08 87 53 06 55','Lorem ipsum dolor sit amet, consectetuer adipiscing'),
(9,1,'Barclay','Fr',FALSE,FALSE,'YZO53ZQV2SU','07 97 46 81 22','Lorem');

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
INSERT INTO equipe (id,nom,categorie,nombre_repas,valide,paye) VALUES (1,'Branden','sem elit, pharetra ut, pharetra sed,',6,true,false),(2,'Iliana','neque. Nullam ut nisi a',6,false,true),(3,'Raphael','lobortis tellus',7,false,false),(4,'Eagan','nec ante blandit viverra. Donec tempus, lorem',1,true,true),(5,'Keith','eu elit. Nulla facilisi. Sed neque.',9,false,false),(6,'Alexandra','vitae diam. Proin dolor.',3,false,false),(7,'Chelsea','velit.',5,false,false),(8,'Jin','Donec',10,false,true);

--participant
INSERT INTO participant (id,nom_complet,adresse,mail,telephone,id_equipe,date_naissance) VALUES (1,'Ocean','P.O. Box 781, 8453 A, Ave','lobortis.augue.scelerisque@tempor.net','08 84 25 27 48',5,'05-12-2020'),(2,'Rylee','Ap #906-6080 Ipsum Avenue','laoreet.lectus@placeratCrasdictum.co.uk','04 72 45 76 42',6,'07-09-2020'),(3,'Rebekah','452-6453 Sagittis. St.','dolor@nec.org','09 77 93 81 03',7,'11-01-2020'),(4,'Wynter','P.O. Box 146, 5002 Sed Rd.','Donec.porttitor.tellus@vel.org','03 72 28 15 08',5,'15-12-2020'),(5,'Lara','P.O. Box 458, 3089 Mauris Street','sem.mollis@atrisus.com','01 46 84 66 60',7,'18-05-2021'),(6,'Maris','579-7182 Velit. Street','semper.tellus@atpretiumaliquet.com','06 28 41 79 55',7,'02-11-2020'),(7,'Kessie','Ap #585-8420 Odio St.','odio.Nam@lobortisClass.org','02 14 53 71 48',2,'09-07-2020'),(8,'Yeo','P.O. Box 694, 6377 Vestibulum, Street','orci.Ut.sagittis@sodalespurus.co.uk','01 27 25 31 75',7,'03-12-2020'),(9,'Raven','1795 Molestie St.','Cras@egestas.org','04 82 98 02 64',5,'09-08-2019'),(10,'Raja','Ap #608-7539 Iaculis Rd.','in.faucibus.orci@velsapien.com','07 70 27 23 78',8,'29-09-2019');

--execute

  





