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
import projet.data.Equipe;
import projet.data.Participant;

public class DaoEquipeAttente {
	@Inject
	DataSource dataSource;
	@Inject
	DaoParticipantAttente daoparticipantattente;
	
	
	
	public List<Equipe> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM equipe_attente ORDER BY nom";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Equipe> equipe = new ArrayList<>();
			while (rs.next()) {
				equipe.add( construireEquipe(rs) );
				
			}
			
			return equipe;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	private Equipe construireEquipe( ResultSet rs ) throws SQLException {

		Equipe equipe = new Equipe();
		equipe.setId(rs.getObject( "id", Integer.class ));
		equipe.setNom(rs.getObject( "nom", String.class ));
		equipe.setCategorie(rs.getObject( "categorie", String.class ));
		equipe.setNombre_repas(rs.getObject( "nombre_repas", Integer.class ));
		equipe.setValide(rs.getObject( "valide", Boolean.class ));
		equipe.setPaye(rs.getObject( "paye", Boolean.class ));
		
		

		
		
		return equipe;
	}
	public List<Participant> retrouverParticipantAttente(int idEquipe)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM participant_attente where id_equipe_attente=?"; 
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idEquipe);
			rs = stmt.executeQuery();
			
			List<Participant> participant = new ArrayList<>();
			while (rs.next()) {
				participant.add( daoparticipantattente.retrouver(rs.getInt("id")) );
				
			}
			
			return participant;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

}
