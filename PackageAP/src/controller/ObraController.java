package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ObraDAO;
import model.Obra;

public class ObraController {
	
	public ObraController(Obra obra) throws SQLException {
		ObraDAO obraDAO = new ObraDAO();
		obraDAO.salvar(obra);
	}
	
	public void salvar(String titulo, int anoPublicacao, int tipoObra) throws SQLException {
        Obra obra = new Obra();
        obra.setTitulo(titulo);
        obra.setAnoPublicacao(anoPublicacao);
        obra.setTipoObra(tipoObra);
        new ObraDAO().salvar(obra);
    }

    public void alterar(String titulo, int anoPublicacao, int tipoObra) throws SQLException {
        Obra obra = new Obra();
        obra.setTitulo(titulo);
        obra.setAnoPublicacao(anoPublicacao);
        obra.setTipoObra(tipoObra);
        new ObraDAO().alterar(obra);
    }

    public List<Obra> listaObras() {
    	ObraDAO obraDao = new ObraDAO();
        try {
            return obraDao.findObraAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Obras\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new ObraDAO().excluir(id);
    }


}
