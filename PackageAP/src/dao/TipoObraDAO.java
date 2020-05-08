package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.TipoObra;

public class TipoObraDAO extends GenericDAO{
	
	  public ArrayList<TipoObra> findTipoObra() throws SQLException {
	    	ArrayList<TipoObra> tipoObras = new ArrayList<TipoObra>();
	        
	        String select = "SELECT * FROM tipoObra";

	        PreparedStatement stmt = getCon().prepareStatement(select);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	TipoObra tipoObra = new TipoObra();
	        	tipoObra.setDescricaoTipoObra(rs.getString("descricaoTipoObra"));
	        	tipoObras.add(tipoObra);
	        }

	        rs.close();
	        stmt.close();

	        return tipoObras;
	    }


}
