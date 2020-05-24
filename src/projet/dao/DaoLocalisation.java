package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Localisation;
import projet.data.Mission;

public class DaoLocalisation {
	

	@Inject
	DataSource dataSource;


	// Actions

		public int inserer(Localisation localisation)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				// Insère la localisation
				sql = "INSERT INTO localisation ( numero, position) VALUES (?,?)";
				stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
				stmt.setInt(	1, localisation.getNumero());
				stmt.setObject(	2, localisation.getPosition());
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				localisation.setNumero( rs.getObject( 1, Integer.class ) );
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}		
			// Retourne l'identifiant
			return localisation.getNumero();
		}

		
		public void modifier(Localisation localisation)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie la localisation
	
				sql = "UPDATE localisation SET  position = ? WHERE numero =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setObject(1, localisation.getPosition());
				stmt.setInt(2, localisation.getNumero());
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}

		public void supprimer(int numero)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Supprime la localisation
				sql = "DELETE FROM localisation WHERE numero = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, numero );
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}

		public Localisation retrouver(int numero)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM localisation WHERE numero = ?";
	            stmt = cn.prepareStatement(sql);
	            stmt.setObject( 1, numero);
	            rs = stmt.executeQuery();

	            if ( rs.next() ) {
	                return construireLocalisation(rs);
	            } else {
	            	return null;
	            }
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}
		
		public Localisation retrouverLocal(String position)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM localisation WHERE position = ?";
	            stmt = cn.prepareStatement(sql);
	            stmt.setObject( 1,position);
	            rs = stmt.executeQuery();

	            if ( rs.next() ) {
	                return construireLocalisation(rs);
	            } else {
	            	return null;
	            }
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

   
		public List<Localisation> listerTout()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM localisation ORDER BY position";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				List<Localisation> localisations = new ArrayList<>();
				while (rs.next()) {
					localisations.add(construireLocalisation(rs) );
				}
				return localisations;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

		// Méthodes auxiliaires
		
		private Localisation construireLocalisation( ResultSet rs) throws SQLException {

			Localisation localisation = new Localisation();
			localisation.setNumero(rs.getObject( "numero", Integer.class ));
			localisation.setPosition(rs.getObject( "Position", String.class ));
			
			return localisation;
		}


	
}
