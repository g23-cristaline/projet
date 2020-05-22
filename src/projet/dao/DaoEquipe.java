package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Equipe;

public class DaoEquipe {
	@Inject
	DataSource dataSource;
	
	
	
	public Equipe retrouver(int id) {
		
		Equipe eq=null;
		
		
		

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql= "SELECT * FROM equipe WHERE id=(?)";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(	1, id );
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				
				eq=construire(rs);
				
			}
			return eq;
			

		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		
	}
	
	public Equipe construire(ResultSet rs) throws SQLException {
		
		Equipe equipe=new Equipe();
		
		equipe.setId(rs.getInt("id"));
		equipe.setCategorie(rs.getObject("categorie", String.class));
		equipe.setNom(rs.getObject("nom",String.class));
		equipe.setNombre_repas(rs.getInt("nombre_repas"));
		equipe.setPaye(rs.getBoolean("paye"));
		equipe.setValide(rs.getBoolean("valide"));
		
		return equipe;
		
		
	}
	
}
