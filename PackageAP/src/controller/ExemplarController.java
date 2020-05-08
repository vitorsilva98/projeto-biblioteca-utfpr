package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ExemplarDAO;
import model.Exemplar;

public class ExemplarController {
	
	public ExemplarController(Exemplar exemplar) throws SQLException {
		ExemplarDAO exemplarDAO = new ExemplarDAO();
		exemplarDAO.salvar(exemplar);
	}
	
    public void alterar(Date dataAquisicao, int situacao) throws SQLException {
        Exemplar exemplar = new Exemplar();
        exemplar.setDataAquisicao(dataAquisicao);
        exemplar.setFkSituacao(situacao);
       new ExemplarDAO().alterar(exemplar);
    }

    public List<Exemplar> listaExemplars() {
    	ExemplarDAO exemplarDao = new ExemplarDAO();
        try {
            return exemplarDao.findExemplarAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Exemplares\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new ExemplarDAO().excluir(id);
    
    }
}
