package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnection {
	 

	private static final String URL_MYSQL = "jdbc:mysql://127.0.0.1:3306/mydb?useTimezone=true&serverTimezone=UTC";
	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static final String user = "root";
	private static final String pass = "root";
	
	public static Connection getConnection(){
		try{
	  
            Class.forName(DRIVER_CLASS);
    
            return DriverManager.getConnection(URL_MYSQL, user, pass);
        }catch (SQLException e){
        	throw new RuntimeException(e);
        }catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erro ao conectar ao Banco de Dados");
			JOptionPane.showMessageDialog(null,"Aplicativo não conectado ao banco de dados.");
		}
		return null;
	}
}
