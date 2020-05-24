package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Mission;
import projet.data.Responsable;

public class DaoMission {
	
	@Inject
	DataSource dataSource;
	@Inject 
	
	DaoLocalisation daolocalisation;

	
	// Actions

		public int inserer(Mission mission)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				// Insère la mission
				sql = "INSERT INTO mission ( nom_mission, horaire,numerolocal,typem) VALUES (?,?,?,?)";
				stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
				//stmt.setInt(1, mission.getId() );
				stmt.setString(	1, mission.getNom_mission());
				stmt.setObject(	2, mission.getHoraire());
				stmt.setObject(3, mission.getLocalisation().getNumero());
				stmt.setString(4, mission.getType());
				stmt.executeUpdate();

				// Récupère l'identifiant généré par le SGBD
				rs = stmt.getGeneratedKeys();
				rs.next();
				mission.setId( rs.getObject( 1, Integer.class ) );
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}		
			// Retourne l'identifiant
			return mission.getId();
		}

		
		public void modifier(Mission mission)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Modifie la mission
	
				sql = "UPDATE mission SET nom_mission = ?, horaire = ?,numerolocal = ?,typem = ? WHERE id =  ?";
				stmt = cn.prepareStatement( sql );
				stmt.setString(1, mission.getNom_mission());
				stmt.setObject(2, mission.getHoraire());
				stmt.setObject(3, mission.getLocalisation().getNumero());
				stmt.setString(4, mission.getType());
				stmt.setObject(5,mission.getId() );
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}

		public void supprimer(int idMission)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			String 				sql;

			try {
				cn = dataSource.getConnection();

				// Supprime la mission
				sql = "DELETE FROM mission WHERE id = ? ";
				stmt = cn.prepareStatement(sql);
				stmt.setObject( 1, idMission );
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( stmt, cn );
			}
		}

		public Mission retrouver(int idMission)  {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM mission WHERE id = ?";
	            stmt = cn.prepareStatement(sql);
	            stmt.setObject( 1, idMission);
	            rs = stmt.executeQuery();

	            if ( rs.next() ) {
	                return construireMission(rs);
	            } else {
	            	return null;
	            }
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

   
		public List<Mission> listerTout()   {

			Connection			cn		= null;
			PreparedStatement	stmt	= null;
			ResultSet 			rs 		= null;
			String				sql;

			try {
				cn = dataSource.getConnection();

				sql = "SELECT * FROM mission ORDER BY nom_mission";
				stmt = cn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				List<Mission> missions = new ArrayList<>();
				while (rs.next()) {
					missions.add(construireMission(rs) );
				}
				return missions;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				UtilJdbc.close( rs, stmt, cn );
			}
		}

		// Méthodes auxiliaires
		
		private Mission construireMission( ResultSet rs) throws SQLException {

			Mission mission = new Mission();
			mission.setId(rs.getObject( "id", Integer.class ));
			mission.setNom_mission(rs.getObject( "nom_mission", String.class ));
			mission.setHoraire((LocalTime) rs.getObject( "Horaire", LocalTime.class ));
			mission.setLocalisation(daolocalisation.retrouver(rs.getObject("numerolocal",Integer.class)));
			mission.setType(rs.getObject("typem",String.class));
			return mission;
		}


	
}
