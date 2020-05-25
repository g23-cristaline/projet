package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Responsable;
import projet.data.Utilisateur;


public class DaoRole {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject 
	private DaoCategorie daocategorie;
	
	// Actions

	public void insererPourUtilisateur( Utilisateur utilisateur )  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO role (id, role) VALUES (?,?)";
			stmt = cn.prepareStatement( sql );
			for( String role : utilisateur.getRoles() ) {
				stmt.setObject( 1, utilisateur.getId() );
				stmt.setObject( 2, role );
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimerPourUtilisateur( int idUtilisateur ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les roles
			sql = "DELETE FROM role  WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idUtilisateur );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public List<String> listerPourUtilisateur( Utilisateur utilisateur ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM role WHERE id = ? ORDER BY role";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, utilisateur.getId() );
			rs = stmt.executeQuery();

			List<String> roles = new ArrayList<>();
			while (rs.next()) {
				roles.add( rs.getObject("role", String.class) );
			}
			return roles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	public List<Responsable>listeResponsableParRole(String role){
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;
		try {
			cn=dataSource.getConnection();
			sql="SELECT * FROM role inner join utilisateur on role.id=utilisateur.id inner join responsable on utilisateur.id_responsable=responsable.id where role ilike ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, role);
			rs=stmt.executeQuery();
			List<Responsable>responsable = new ArrayList<>();
			while(rs.next()) {
				responsable.add(construireListe(rs));
			}
			return responsable;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	public Responsable construireListe(ResultSet rs) throws SQLException {
		Responsable responsable = new Responsable();
		responsable.setId(rs.getObject( "id", Integer.class ));
		responsable.setNom_complet(rs.getObject( "nom_complet", String.class ));
		responsable.setAdresse(rs.getObject( "adresse", String.class ));
		responsable.setPermis_conduire(rs.getObject("permis_conduire",Boolean.class));
		responsable.setBrevet_secourisme(rs.getObject("brevet_secourisme",Boolean.class));
		responsable.setCategorie( daocategorie.retrouver( rs.getObject("idcategorie", Integer.class) ) );
		responsable.setCode(rs.getObject( "code", String.class ));
		responsable.setTelephone(rs.getObject( "telephone", String.class ));
		responsable.setDate_naissance(rs.getObject("date_naissance", LocalDate.class));
		responsable.setDate_permis(rs.getObject("date_permis", LocalDate.class));
		responsable.setNumero_permis(rs.getObject("numero_permi",String.class));
		responsable.setLieu_permis(rs.getObject("Lieu_Permis",String.class));
		responsable.setInfo_supplementaires(rs.getObject( "info_supplementaires", String.class ));
		return responsable;
	}

}
