SET search_path TO cristaline;

-- Supprimer toutes les données
DELETE FROM Executer;
DELETE FROM role;
DELETE FROM Mission;
DELETE FROM Localisation;
DELETE FROM Participant;
DELETE FROM Equipe;
DELETE FROM Participant_attente;
delete from equipe_attente;
DELETE FROM utilisateur;
DELETE FROM responsable;
DELETE FROM categorie;



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


--Equipe_attente
INSERT INTO equipe_attente (id,nom,categorie,nombre_repas,valide,paye) VALUES (1,'Alika','euismod',1,true,true),(2,'TaShya','arcu',6,true,true),(3,'Adele','tellus',2,true,true),(4,'Christopher','leo. Morbi',6,true,true),(5,'David','a, magna.',8,true,true),(6,'Thomas','sit',10,true,true),(7,'Dante','rutru Curabitur',9,true,true),(8,'Charlotte',' dapibus i',9,true,true),(9,'Regan','tortor. Integer',10,true,true),(10,'Aaron','Duis sit',3,true,true);

--participant_attente
INSERT INTO participant_attente (id,nom_complet,adresse,mail,date_naissance,telephone,id_equipe_attente) VALUES (1,'Gage','986-7975 Non Avenue','libero.Proin@nibh.co.uk','14/05/20','02 26 85 33 31',1),(2,'Yardley','P.O. Box 644, 9206 Nisl. Av.','rutrum.lorem.ac@quamvelsapien.com','25/05/21','05 79 24 09 52',1),(3,'Noah','313-3798 Pede, Ave','pharetra@necante.com','03/09/20','03 17 43 75 19',2),(4,'Claire','943-1838 Diam. St.','sapien@aliquetvel.edu','15/04/21','07 29 45 08 07',2),(5,'Zelenia','Ap #535-7158 Facilisi. Av.','ipsum.primis.in@musDonecdignissim.com','14/01/20','05 63 33 07 10',3),(6,'Declan','P.O. Box 503, 6556 Nec Avenue','sem.vitae.aliquam@Sednulla.ca','22/05/21','05 85 01 92 23',3),(7,'Keiko','962-3195 Feugiat. St.','Donec@acrisus.edu','28/11/20','07 98 30 60 39',4),(8,'Amelia','P.O. Box 516, 484 Vestibulum. Rd.','ac@vitae.co.uk','28/02/20','01 35 59 97 69',4),(9,'Alexa','Ap #905-8480 Sodales Street','rhoncus.id@leoVivamus.co.uk','02/09/20','08 75 46 23 21',5),(10,'William','546-1743 Sed Rd.','fringilla.euismod@dictumeleifendnunc.edu','30/10/20','05 87 64 51 02',5);

--equipe
INSERT INTO equipe (id,nom,categorie,nombre_repas,valide,paye) VALUES (1,'Branden','sem elit',6,true,false),(2,'Iliana','neque. Nullam ',6,false,true),(3,'Raphael','loborti',7,false,false),(4,'Eagan','nec ante',1,true,true),(5,'Keith','eu elit',9,false,false),(6,'Alexandra','vitae diam',3,false,false),(7,'Chelsea','velit.',5,false,false),(8,'Jin','Donec',10,false,true);

--participant
INSERT INTO participant (id,nom_complet,adresse,mail,telephone,id_equipe,date_naissance) VALUES (1,'Ocean','P.O. Box 781, 8453 A, Ave','lobortis.augue.scelerisque@tempor.net','08 84 25 27 48',5,'05-12-2020'),(2,'Rylee','Ap #906-6080 Ipsum Avenue','laoreet.lectus@placeratCrasdictum.co.uk','04 72 45 76 42',6,'07-09-2020'),(3,'Rebekah','452-6453 Sagittis. St.','dolor@nec.org','09 77 93 81 03',7,'11-01-2020'),(4,'Wynter','P.O. Box 146, 5002 Sed Rd.','Donec.porttitor.tellus@vel.org','03 72 28 15 08',5,'15-12-2020'),(5,'Lara','P.O. Box 458, 3089 Mauris Street','sem.mollis@atrisus.com','01 46 84 66 60',7,'18-05-2021'),(6,'Maris','579-7182 Velit. Street','semper.tellus@atpretiumaliquet.com','06 28 41 79 55',7,'02-11-2020'),(7,'Kessie','Ap #585-8420 Odio St.','odio.Nam@lobortisClass.org','02 14 53 71 48',2,'09-07-2020'),(8,'Yeo','P.O. Box 694, 6377 Vestibulum, Street','orci.Ut.sagittis@sodalespurus.co.uk','01 27 25 31 75',7,'03-12-2020'),(9,'Raven','1795 Molestie St.','Cras@egestas.org','04 82 98 02 64',5,'09-08-2019'),(10,'Raja','Ap #608-7539 Iaculis Rd.','in.faucibus.orci@velsapien.com','07 70 27 23 78',8,'29-09-2019');

--localisation
INSERT INTO Localisation (numero, position) VALUES
(1,'Limoges'),
(2,'Paris'),
(3,'Lyon'),
(4,'Nantes');

--mission
INSERT INTO Mission (nom_Mission,horaire,numerolocal,typem) VALUES 
('Donec ','12:02:10',1,'vitae erat vel pede blandit congue. In'),
('tristique ','17:15:20',2,'pulvinar'),
('leo','06:09:50',3,'ullamcorper magna. Sed eu eros. Nam'),
('imperdiet','23:19:32',4,'ullamcorper, nisl arcu iaculis'),
('Cras dictum ','15:40:46',4,'felis eget varius ultrices, mauris ipsum porta elit,'),
('magnis dis parturient','00:18:47',3,'mollis lectus pede et risus. Quisque libero'),
('at augue id','08:26:15',2,'velit. Aliquam nisl. Nulla eu neque pellentesque massa'),
('dolor dolor','08:00:00',1,'libero lacus, varius et,'),
('dol dolar','08:01:00',2,'libero lacus, varius et,');

-- Role

INSERT INTO role (id, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'MEMBRE' ),
  ( 2, 'MEMBRE' ),
  ( 3, 'EXTERNE' );
  
--execute

  





