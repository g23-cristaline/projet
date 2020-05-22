package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Utilisateur;


public class DaoUtilisateur {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoRole			daoRole;
	@Inject
	private DaoResponsable daoResponsable;

	
	// Actions

	public int inserer( Utilisateur utilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le utilisateur
			sql = "INSERT INTO utilisateur ( pseudo, pass, mail,id_responsable ) VALUES ( ?, ?, ? ,?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS ); 
			stmt.setObject( 1, utilisateur.getPseudo() );
			stmt.setObject( 2, utilisateur.getPass() );
			stmt.setObject( 3, utilisateur.getMail() );
			stmt.setObject( 4, utilisateur.getResponsable().getId() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			utilisateur.setId( rs.getObject( 1, Integer.class) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Insère les rôles
		daoRole.insererPourUtilisateur( utilisateur );
		
		// Retourne l'identifiant
		return utilisateur.getId();
	}
	public int verifCodeIdentification(String code) {
		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;
		ResultSet rs;
		try {
			cn=dataSource.getConnection();
			sql="SELECT * from responsable where code ilike ?";
			stmt= cn.prepareStatement(sql);
			stmt.setObject(1, code);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getObject("id", Integer.class);
			}
			else {
				return 0;
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		
	}
	

	public void modifier( Utilisateur utilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le utilisateur
			sql = "UPDATE utilisateur SET pseudo = ?, pass = ?, mail = ?, id_responsable = ? WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, utilisateur.getPseudo() );
			stmt.setObject( 2, utilisateur.getPass() );
			stmt.setObject( 3, utilisateur.getMail() );
			stmt.setObject( 4, utilisateur.getResponsable().getId() );
			stmt.setObject( 5, utilisateur.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}

		// Modifie les rôles
		daoRole.supprimerPourUtilisateur( utilisateur.getId() );
		daoRole.insererPourUtilisateur( utilisateur );

	}
	

	public void supprimer( int idUtilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		// Supprime les rôles
		daoRole.supprimerPourUtilisateur( idUtilisateur );

		try {
			cn = dataSource.getConnection();

			// Supprime le utilisateur
			sql = "DELETE FROM utilisateur WHERE id = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, idUtilisateur );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	

	public Utilisateur retrouver( int idUtilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur WHERE id = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idUtilisateur );
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireUtilisateur( rs );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	

	public List<Utilisateur> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur ORDER BY pseudo";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Utilisateur> utilisateurs = new ArrayList<>();
			while ( rs.next() ) {
				utilisateurs.add( construireUtilisateur(rs) );
			}
			return utilisateurs;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public Utilisateur validerAuthentification( String pseudo, String motDePasse )  {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM utilisateur WHERE pseudo = ? AND pass = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, pseudo );
			stmt.setObject( 2, motDePasse );
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireUtilisateur( rs );			
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public boolean verifierUnicitePseudo( String pseudo, Integer idUtilisateur )   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		if ( idUtilisateur == null ) idUtilisateur = 0;
		
		try {
			cn = dataSource.getConnection();

			sql = "SELECT COUNT(*) = 0 AS unicite"
					+ " FROM utilisateur WHERE pseudo = ? AND id <> ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(	1, pseudo );
			stmt.setObject(	2, idUtilisateur );
			rs = stmt.executeQuery();
			
			rs.next();
			return rs.getBoolean( "unicite" );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	public boolean isIdExistant(String code) {
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;
		try {
			cn=dataSource.getConnection();
			sql = "select * from utilisateur inner join\r\n" + 
					"responsable on responsable.id=utilisateur.id_responsable where code ilike ?";
			stmt=cn.prepareStatement(sql);
			stmt.setObject(1, code);
			rs=stmt.executeQuery();
			return rs.next();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
		
	}
	
	
	// Méthodes auxiliaires
	
	private Utilisateur construireUtilisateur( ResultSet rs ) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId( rs.getObject( "id", Integer.class ) );
		utilisateur.setPseudo( rs.getObject( "pseudo", String.class ) );
		utilisateur.setPass( rs.getObject( "pass", String.class ) );
		utilisateur.setMail( rs.getObject( "mail", String.class ) );
		utilisateur.setResponsable(daoResponsable.retrouver(rs.getObject("id_responsable",Integer.class)));
		utilisateur.getRoles().setAll( daoRole.listerPourUtilisateur( utilisateur ) );
		return utilisateur;
	}
	
}
