package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.AutorObra;

public class AutorObraDAO extends GenericDAO{
	
	public void salvar(AutorObra autorObra) throws SQLException {
		try {
		String insert = "INSERT INTO autor_obra(fkAutor, fkObra) VALUES(?,?)";
        save(insert, autorObra.getIdAutor(), autorObra.getIdObra());
        JOptionPane.showMessageDialog(null,"Obra Cadastrada com Sucesso");
        
		} catch (SQLException ex) {
			System.out.println(ex.getErrorCode());
            switch (ex.getErrorCode()) {
                case 1062:
                    JOptionPane.showMessageDialog(null,"Usuario já está cadastrado");
                    break;
                case 1216:
                case 1217:
                case 1451:
                case 1064:
                    JOptionPane.showMessageDialog(null,"Não é permitido deixar campos em branco");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Oooops! Erro inesperado");
            }
		}
    }
	

    public ArrayList<AutorObra> findAutorObra() throws SQLException {
    	ArrayList<AutorObra> autorObras = new ArrayList<AutorObra>();
        
        String select = "SELECT * FROM autor_obra";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	AutorObra autorObra = new AutorObra();
        	autorObra.setIdObra(rs.getInt("idObra"));
        	autorObra.setIdAutor(rs.getInt("idAutor"));
        	autorObras.add(autorObra);
        }

        rs.close();
        stmt.close();

        return autorObras;
    }



}
