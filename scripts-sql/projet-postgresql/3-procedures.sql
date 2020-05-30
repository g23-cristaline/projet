SET search_path TO cristaline;


-- Supprime toutes les fonctions du schéma

DO $ccode$
DECLARE 
	r RECORD;
BEGIN
	FOR r IN
		SELECT 'DROP PROCEDURE ' || ns.nspname || '.' || proname 
	       || '(' || oidvectortypes(proargtypes) || ')' AS sql
		FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid)
		WHERE ns.nspname = 'cristaline'  
	LOOP
		EXECUTE r.sql;
	END LOOP;
END;
$ccode$;


-- Fonctions

CREATE OR REPLACE PROCEDURE equipe_inserer(
	p_nom_equipe		VARCHAR,
	p_categorie			VARCHAR,
	p_repas				INT,
	p_nom_complet1 		VARCHAR,
	p_adresse1 	VARCHAR,
	p_email1			VARCHAR,
	p_telephone1			VARCHAR,
	p_naissance1		DATE,
	p_nom_complet2 		VARCHAR,
	p_adresse2		VARCHAR,
	p_email2		VARCHAR,
	p_telephone2		VARCHAR,
	p_naissance2		DATE
) 
AS $ccode$
DECLARE p_idequipe INT;
BEGIN
	INSERT INTO equipe_attente(nom,categorie,nombre_repas,valide,paye)
	VALUES (p_nom_equipe,p_categorie,p_repas,FALSE,FALSE)
	RETURNING id INTO p_idequipe;
	
	INSERT INTO participant_attente(nom_complet,adresse,mail,telephone,date_naissance,id_equipe_attente)
	VALUES(p_nom_complet1,p_adresse1,p_email1,p_telephone1,p_naissance1,p_idequipe),
	(p_nom_complet2,p_adresse2,p_email2,p_telephone2,p_naissance2,p_idequipe);
END;
$ccode$ LANGUAGE plpgsql;
CREATE PROCEDURE inserer_benevolePermis(
	p_nom VARCHAR,
	p_adresse VARCHAR,
	p_naissance DATE,
	p_secourisme VARCHAR,
	p_code	VARCHAR,
	p_telephone	VARCHAR,
	p_info  TEXT,
	p_num_permi VARCHAR,
	p_date_permi DATE,
	p_lieu_permi	VARCHAR
) AS $ccode$
DECLARE 
p_id_categorie 	INT:=1;
secour BOOL:=FALSE;
BEGIN
	IF p_secourisme<>'non' then
	secour:=TRUE;
	END IF;
	INSERT INTO responsable(nom_complet,adresse,
	date_naissance,permis_conduire,brevet_secourisme,
	code,telephone,info_supplementaires,numero_permi,
	date_permis,lieu_permis,idcategorie)
	VALUES(p_nom,p_adresse,p_naissance,TRUE,
	secour,p_code,p_telephone,p_info,
	p_num_permi,p_date_permi,p_lieu_permi,p_id_categorie);
END ;
$ccode$ LANGUAGE plpgsql;
CREATE PROCEDURE inserer_benevole(
	p_nom VARCHAR,
	p_adresse VARCHAR,
	p_naissance DATE,
	p_secourisme	VARCHAR,
	p_code	VARCHAR,
	p_telephone	VARCHAR,
	p_info  TEXT
) AS $ccode$
DECLARE
p_id_categorie 	INT:=1;
secour BOOL :=FALSE;
BEGIN
	IF p_secourisme<>'non' then
	secour:=TRUE;
	END IF;
	INSERT INTO responsable(nom_complet,adresse,
	date_naissance,permis_conduire,brevet_secourisme,
	code,telephone,info_supplementaires,idcategorie)
	VALUES(p_nom,p_adresse,p_naissance,FALSE,
	secour,p_code,p_telephone,p_info,
	p_id_categorie);
END ;
$ccode$ LANGUAGE plpgsql;

--CREATE FUNCTION compte_inserer(
--	p_pseudo 		VARCHAR,
--	p_motdepasse 	VARCHAR,
--	p_email			VARCHAR,
--	p_idcompte 	OUT	INT,
--	p_roles			VARCHAR[]
--) 
--AS $ccode$
--DECLARE v_role VARCHAR;
--BEGIN
--	INSERT INTO compte ( pseudo, motdepasse, email )
--	VALUES ( p_pseudo, p_motdepasse,p_email )
--	RETURNING idcompte INTO p_idcompte;
--	
--	FOREACH v_role IN ARRAY p_roles LOOP
--		INSERT INTO role ( idcompte, role )
--		VALUES ( p_idcompte, v_role );
--	END LOOP;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
--CREATE FUNCTION compte_modifier(
--	p_pseudo 		VARCHAR,
--	p_motdepasse 	VARCHAR,
--	p_email			VARCHAR,
--	p_idcompte 		INT,
--	p_roles			VARCHAR[]
--) 
--RETURNS VOID 
--AS $ccode$
--DECLARE v_role VARCHAR;
--BEGIN
--	UPDATE compte 
--	SET pseudo = p_pseudo, 
--		motdepasse = p_motdepasse, 
--		email = p_email 
--	WHERE idcompte =  p_idcompte;
--
--	DELETE FROM role WHERE idcompte = p_idcompte;
--	
--	FOREACH v_role IN ARRAY p_roles LOOP
--		INSERT INTO role ( idcompte, role )
--		VALUES ( p_idcompte, v_role );
--	END LOOP;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
--
--CREATE FUNCTION compte_supprimer( p_idCompte INT ) 
--RETURNS VOID 
--AS $ccode$
--BEGIN
--	DELETE FROM role WHERE idcompte = p_idcompte;
--	DELETE FROM compte WHERE idcompte = p_idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
----CREATE FUNCTION compte_retrouver( p_idCompte INT ) 
----RETURNS TABLE (
----    idcompte    INT,
----    pseudo      VARCHAR,
----    motdepasse  VARCHAR,
----    email       VARCHAR,
----    roles       VARCHAR[]
----)
----AS $ccode$
----BEGIN
----	RETURN QUERY
----	SELECT c.*, ARRAY_AGG( r.role ) AS roles
----	FROM compte c 
----	LEFT JOIN role r ON c.idcompte = r.idcompte
----	WHERE c.idcompte = p_idcompte
----	GROUP BY c.idcompte;
----END;
----$ccode$ LANGUAGE plpgsql;
--
--
--CREATE FUNCTION compte_retrouver( p_idCompte INT ) 
--RETURNS SETOF v_compte_avec_roles 
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT * 
--	FROM v_compte_avec_roles
--	WHERE idcompte = p_idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
--CREATE FUNCTION compte_lister_tout() 
--RETURNS SETOF v_compte_avec_roles 
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT * 
--	FROM v_compte_avec_roles 
--	ORDER BY pseudo;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
----CREATE FUNCTION compte_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
----RETURNS TABLE (
----    idcompte    INT,
----    pseudo      VARCHAR,
----    motdepasse  VARCHAR,
----    email       VARCHAR,
----    roles       VARCHAR[]
----)
----AS $ccode$
----BEGIN
----	RETURN QUERY
----	SELECT c.*,  ARRAY_AGG( r.role ) AS roles
----	FROM compte c 
----	LEFT JOIN role r ON c.idcompte = r.idcompte
----	WHERE c.pseudo = P_pseudo
----	  AND c.motdepasse = p_motdepasse
----	GROUP BY c.idcompte;
----END;
----$ccode$ LANGUAGE plpgsql;
--
--
--CREATE FUNCTION compte_valider_authentification( p_pseudo VARCHAR, p_motdepasse VARCHAR ) 
--RETURNS SETOF v_compte_avec_roles
--AS $ccode$
--BEGIN
--	RETURN QUERY
--	SELECT * FROM v_compte_avec_roles
--	WHERE pseudo = P_pseudo
--	  AND motdepasse = p_motdepasse;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
--CREATE FUNCTION compte_verifier_unicite_pseudo(
--	p_pseudo 		VARCHAR,
--	p_idcompte 		INT,
--	OUT p_unicite	BOOLEAN
--) 
--AS $ccode$
--BEGIN
--	SELECT COUNT(*) = 0 INTO p_unicite
--	FROM compte
--	WHERE pseudo = p_pseudo
--	  AND idcompte <> P_idcompte;
--END;
--$ccode$ LANGUAGE plpgsql;
--
--
