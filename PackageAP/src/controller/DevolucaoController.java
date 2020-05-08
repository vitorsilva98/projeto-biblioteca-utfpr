package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.DevolucaoDAO;
import model.Devolucao;

public class DevolucaoController {
	
	public DevolucaoController(Devolucao devolucao) throws SQLException {
		DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
		devolucaoDAO.salvar(devolucao);
	}
	
	public void salvar(Date dataDevolucao) throws SQLException {
        Devolucao devolucao = new Devolucao();
        devolucao.setDataDevolucao(dataDevolucao);
        new DevolucaoDAO().salvar(devolucao);
    }

    public void alterar(Date dataDevolucao) throws SQLException {
        Devolucao devolucao = new Devolucao();
        devolucao.setDataDevolucao(dataDevolucao);
    	new DevolucaoDAO().alterar(devolucao);
    }

    public List<Devolucao> listaDevolucaos() {
    	DevolucaoDAO devolucaoDao = new DevolucaoDAO();
        try {
            return devolucaoDao.findDevolucaoAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Devolucaoes\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new DevolucaoDAO().excluir(id);
    }

}
