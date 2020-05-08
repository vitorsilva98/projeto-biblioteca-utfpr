package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DBConnection;

public class GenericDAO {
private Connection con;
	
	protected GenericDAO() {
		this.con = DBConnection.getConnection();
	}
	
	protected Connection getCon(){
		return con;
	}
	
	protected void save(String insertSQL, Object... parametros) throws SQLException{
		 PreparedStatement pstmt = getCon().prepareStatement(insertSQL);
		 for (int i = 0; i < parametros.length; i++)	
			 pstmt.setObject(i+1, parametros[i]);
		 
		 pstmt.execute();
		 pstmt.close();
		 
	}
	
	protected void update(String updateSQL, Object id, Object... parametros) throws SQLException {
		PreparedStatement pstmt = getCon().prepareStatement(updateSQL);
		for (int i = 0; i < parametros.length; i++)	
			 pstmt.setObject(i+1, parametros[i]);
		
		pstmt.setObject(parametros.length + 1, id);
		pstmt.execute();
		pstmt.close();
	}
	
	protected void delete(String deleteSQL, Object id, Object... parametros) throws SQLException {
		PreparedStatement pstmt = getCon().prepareStatement(deleteSQL);
		 
		for (int i = 0; i < parametros.length; i++)	
			 pstmt.setObject(i+1, parametros[i]);
	
		pstmt.execute();
		pstmt.close();
	}
}
