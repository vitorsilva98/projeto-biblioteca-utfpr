package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.AutorDAO;
import model.Autor;


public class AutorController {
	
	public AutorController(Autor autor) throws SQLException {
		AutorDAO autorDAO = new AutorDAO();
		autorDAO.salvar(autor);
	}

	public void salvar(String nomeAutor) throws SQLException {
        Autor autor = new Autor();
        autor.setNomeAutor(nomeAutor);
        new AutorDAO().salvar(autor);
    }

    public void alterar(String nomeAutor) throws SQLException {
        Autor autor = new Autor();
        autor.setNomeAutor(nomeAutor);
    	new AutorDAO().alterar(autor);
    }

    public List<Autor> listaAutors() {
    	AutorDAO autorDao = new AutorDAO();
        try {
            return autorDao.findAutor();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Autores\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new AutorDAO().excluir(id);
    }


}
