package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Responsable;


public class DaoResponsable {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoCategorie	daoCategorie;

	
	// Actions

	public int inserer(Responsable responsable)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql,sql2;

		try {
			cn = dataSource.getConnection();

			// Insère le responsable
			sql = "INSERT INTO responsable ( idcategorie, nom_complet, adresse,permis_conduire,brevet_secourisme,code,telephone,info_supplementaires,date_naissance ) VALUES ( ?, ?, ?,?,?,?,?,?,? )";
			sql2= "INSERT INTO responsable ( idcategorie, nom_complet, adresse,permis_conduire,brevet_secourisme,code,telephone,info_supplementaires,date_naissance,numero_permis,date_permis,lieu_permis ) VALUES ( ?, ?, ?,?,?,?,?,?,?,?,?,? )";
			if(responsable.getCategorie().getId()==1)
				stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			else
				stmt = cn.prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(	1, responsable.getCategorie().getId() );
			stmt.setString(	2, responsable.getNom_complet() );
			stmt.setString(	3, responsable.getAdresse() );
			stmt.setBoolean(4, responsable.getPermis_conduire() );
			stmt.setBoolean(5, responsable.getBrevet_secourisme() );
			stmt.setString(	6, responsable.getCode() );
			stmt.setString(	7, responsable.getTelephone() );
			stmt.setString(	8, responsable.getInfo_supplementaires() );
			stmt.setObject(9, responsable.getDate_naissance());
			if(responsable.getCategorie().getId()==1)
			{
				stmt.setString(10,responsable.getNumero_permis());
				stmt.setObject(11,responsable.getDate_permis());
				stmt.setString(12, responsable.getLieu_permis());
			}
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			responsable.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}		
		// Retourne l'identifiant
		return responsable.getId();
	}

	
	public void modifier(Responsable responsable)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le responsable
			sql = "UPDATE responsable SET idcategorie = ?,"
					+ " nom_complet = ?,"
					+ " adresse= ?,"
					+ "permis_conduire=?,"
					+ "brevet_secourisme=?,"
					+ "code=?,"
					+ "telephone=?,"
					+ "info_supplementaires=?,"
					+ "date_naissance=?"
					+ " WHERE id =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, responsable.getCategorie().getId() );
			stmt.setObject( 2, responsable.getNom_complet() );
			stmt.setObject( 3, responsable.getAdresse() );
			stmt.setObject( 4, responsable.getPermis_conduire() );
			stmt.setObject( 5, responsable.getBrevet_secourisme() );
			stmt.setObject( 6, responsable.getCode() );
			stmt.setObject( 7, responsable.getTelephone() );
			stmt.setObject( 8, responsable.getInfo_supplementaires() );
			stmt.setObject (9,responsable.getDate_naissance());
			stmt.setObject( 10, responsable.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idResponsable)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime le responsable
			sql = "DELETE FROM responsable WHERE id = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idResponsable );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Responsable retrouver(int idResponsable)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM responsable WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idResponsable);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireResponsable(rs);
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	
	public List<Responsable> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM responsable ORDER BY nom_complet";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Responsable> responsables = new ArrayList<>();
			while (rs.next()) {
				responsables.add( construireResponsable(rs) );
			}
			return responsables;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	    
    public int compterPourCategorie(int idCategorie) {
    	
		Connection			cn		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;

		try {
			cn = dataSource.getConnection();
            String sql = "SELECT COUNT(*) FROM responsable WHERE idcategorie = ?";
            stmt = cn.prepareStatement( sql );
            stmt.setObject( 1, idCategorie );
            rs = stmt.executeQuery();

            rs.next();
            return rs.getInt( 1 );

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
    }
	
	
	// Méthodes auxiliaires
	
	private Responsable construireResponsable( ResultSet rs) throws SQLException {

		Responsable responsable = new Responsable();
		responsable.setId(rs.getObject( "id", Integer.class ));
		responsable.setNom_complet(rs.getObject( "nom_complet", String.class ));
		responsable.setAdresse(rs.getObject( "adresse", String.class ));
		responsable.setPermis_conduire(rs.getObject("permis_conduire",Boolean.class));
		responsable.setBrevet_secourisme(rs.getObject("brevet_secourisme",Boolean.class));
		responsable.setCategorie( daoCategorie.retrouver( rs.getObject("idcategorie", Integer.class) ) );
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
