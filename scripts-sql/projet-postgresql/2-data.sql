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
INSERT INTO Mission (nom_Mission,horaire,localisation,typem) VALUES 
('Donec ','12:02:10','a','vitae erat vel pede blandit congue. In'),
('tristique ','17:15:20','imperdiet ornare. In faucibus.','pulvinar'),
('leo','06:09:50','semper egestas, urna justo faucibus lectus, a','ullamcorper magna. Sed eu eros. Nam'),
('imperdiet','23:19:32','tristique senectus et netus et malesuada fames','ullamcorper, nisl arcu iaculis'),
('Cras dictum ','15:40:46','et magnis dis parturient montes, nascetur','felis eget varius ultrices, mauris ipsum porta elit,'),
('magnis dis parturient','00:18:47','Ut semper pretium neque. Morbi quis urna.','mollis lectus pede et risus. Quisque libero'),
('at augue id','08:26:15','vitae, orci.','velit. Aliquam nisl. Nulla eu neque pellentesque massa'),
('dolor dolor','08:00:00','consectetuer adipiscing','libero lacus, varius et,');

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

--participant

--execute

  





