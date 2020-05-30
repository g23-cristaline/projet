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
import projet.data.Equipe;
import projet.data.Mission;
import projet.data.Participant;
import projet.data.Personne;

public class DaoParticipant {
	
	@Inject
	DataSource dataSource;
	@Inject
	DaoEquipe daoequipe;
	
	
	public int inserer(Participant participant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le participant
			sql = "INSERT INTO participant ( id, nom_complet, adresse, mail, telephone, date_naissance, id_equipe ) VALUES ( ?, ?, ?, ?, ?, ? ,?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setInt(	1, participant.getId() );
			stmt.setString(	2, participant.getNom_complet() );
			stmt.setString(	3, participant.getAdresse() );
			stmt.setString(	4, participant.getMail() );
			stmt.setString(	5, participant.getTelephone() );
			stmt.setObject(	6, participant.getDate_naissance() );
			stmt.setObject(	7, participant.getEquipe().getId() );
			
			
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			participant.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		return participant.getId();
	

}
	public List<Participant> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant ORDER BY nom_complet";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Participant> participants = new ArrayList<>();
			while (rs.next()) {
				participants.add( construireParticipant(rs) );
				
			}
			
			return participants;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public Participant construireParticipant( ResultSet rs ) throws SQLException {

		Participant participant = new Participant();
		participant.setId(rs.getObject( "id", Integer.class ));
		participant.setNom_complet(rs.getObject( "nom_complet", String.class ));
		participant.setAdresse(rs.getObject( "adresse", String.class ));
		participant.setMail(rs.getObject( "mail", String.class ));
		participant.setTelephone(rs.getObject( "telephone", String.class ));
		participant.setDate_naissance(rs.getObject( "date_naissance", LocalDate.class ));
		participant.setEquipe(daoequipe.retrouver(rs.getObject("id_equipe",Integer.class) ));
		

		
		
		return participant;
	}
	
	public Participant retrouver(int idParticipant)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant WHERE id = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idParticipant);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireParticipant(rs);
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public void supprimer(int IdParticipant) {
		
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql="DELETE from participant where id=?";
			
			stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, IdParticipant);
             stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		
	}
	
	public void modifier(Participant participant)  {
	Connection			cn		= null;
	PreparedStatement	stmt	= null;
	String 				sql;

	try {
		cn = dataSource.getConnection();

		// Modifie le participant
		sql = "UPDATE participant SET   nom_complet= ?, adresse= ?, mail= ?, telephone= ?, date_naissance= ?, id_equipe= ?  where id=?";
		stmt = cn.prepareStatement( sql );
		
		stmt.setObject( 1, participant.getNom_complet() );
		stmt.setObject( 2, participant.getAdresse() );
		stmt.setObject( 3, participant.getMail() );
		stmt.setObject( 4, participant.getTelephone() );
		stmt.setObject( 5, participant.getDate_naissance() );
		stmt.setObject( 6, participant.getEquipe().getId() );
		stmt.setObject( 7, participant.getId() );
		
		
		stmt.executeUpdate();
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	} finally {
		UtilJdbc.close( stmt, cn );
	}
	}
	
	
	

}
