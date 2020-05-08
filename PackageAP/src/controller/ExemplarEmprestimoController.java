package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ExemplarEmprestimoDAO;
import dao.GenericDAO;
import model.ExemplarEmprestimo;

public class ExemplarEmprestimoController extends GenericDAO {
	
	public ExemplarEmprestimoController(ExemplarEmprestimo exemplarEmprestimo) throws SQLException {
		ExemplarEmprestimoDAO exemplarEmprestimoDAO = new ExemplarEmprestimoDAO();
		exemplarEmprestimoDAO.salvar(exemplarEmprestimo);
	}
	
	
	public void salvar(int idExemplar, int idEmprestimo) throws SQLException {
		ExemplarEmprestimo exemplarEmprestimo = new ExemplarEmprestimo();
        exemplarEmprestimo.setIdExemplar(idExemplar);
        exemplarEmprestimo.setIdEmprestimo(idEmprestimo);
        new ExemplarEmprestimoDAO().salvar(exemplarEmprestimo);
    }

   
    public List<ExemplarEmprestimo> listaExemplarEmprestimos() {
    	ExemplarEmprestimoDAO exemplarEmprestimoDao = new ExemplarEmprestimoDAO();
        try {
            return exemplarEmprestimoDao.findExemplarEmprestimo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar ExemplarEmprestimos\n" + e.getLocalizedMessage());
        }
        return null;
    }


}
