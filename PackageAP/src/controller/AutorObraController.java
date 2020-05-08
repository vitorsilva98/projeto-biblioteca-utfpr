package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.AutorObraDAO;
import model.AutorObra;

public class AutorObraController {
	public AutorObraController(AutorObra autorObra) throws SQLException {
		AutorObraDAO autorObraDAO = new AutorObraDAO();
		autorObraDAO.salvar(autorObra);
	}
	
	
	public void salvar(int idAutor, int idObra) throws SQLException {
        AutorObra autorObra = new AutorObra();
        autorObra.setIdAutor(idAutor);
        autorObra.setIdObra(idObra);
        new AutorObraDAO().salvar(autorObra);
    }

   
    public List<AutorObra> listaAutorObras() {
    	AutorObraDAO autorObraDao = new AutorObraDAO();
        try {
            return autorObraDao.findAutorObra();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar AutorObras\n" + e.getLocalizedMessage());
        }
        return null;
    }


}
