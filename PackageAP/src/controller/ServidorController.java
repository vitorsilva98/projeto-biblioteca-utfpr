package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ServidorDAO;
import model.Servidor;

public class ServidorController {
	public ServidorController(Servidor servidor) throws SQLException {
		ServidorDAO servidorDAO = new ServidorDAO();
		servidorDAO.salvar(servidor);
	}
	
	public void salvar(int matriculaSIAPE) throws SQLException {
        Servidor servidor = new Servidor();
        servidor.setMatriculaSIAPE(matriculaSIAPE);
        new ServidorDAO().salvar(servidor);
    }

    public void alterar(int matriculaSIAPE) throws SQLException {
        Servidor servidor = new Servidor();
        servidor.setMatriculaSIAPE(matriculaSIAPE);
        new ServidorDAO().alterar(servidor);
    }

    public List<Servidor> listaServidors() {
    	ServidorDAO servidorDao = new ServidorDAO();
        try {
            return servidorDao.findServidor();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Servidors\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new ServidorDAO().excluir(id);
    }


}
