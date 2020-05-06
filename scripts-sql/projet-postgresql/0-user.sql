

-- Supprime le schéma projet

DROP SCHEMA IF EXISTS cristaline CASCADE;


-- Crée l'utilisateur projet
-- (après l'avoir supprimé au préalable s'il existait déjà)

DO $code$
BEGIN
	IF EXISTS (SELECT  FROM pg_catalog.pg_roles WHERE rolname  = 'cristaline')
	THEN
		REVOKE CREATE ON DATABASE postgres FROM cristaline;
		DROP USER cristaline;
	END IF;
END
$code$;

CREATE USER cristaline WITH PASSWORD 'cristaline';
GRANT CREATE ON DATABASE postgres TO cristaline;

