package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UsuarioFoneDAO;
import model.UsuarioFone;

public class UsuarioFoneController {
	public UsuarioFoneController(UsuarioFone usuarioFone) throws SQLException {
		UsuarioFoneDAO usuFoneDAO = new UsuarioFoneDAO();
		usuFoneDAO.salvar(usuarioFone);
	}
	public void salvar(String telefone) throws SQLException {
        UsuarioFone usuarioFone = new UsuarioFone();
        usuarioFone.setTelefone(telefone);
        new UsuarioFoneDAO().salvar(usuarioFone);
    }

    public void alterar(String telefone)  throws SQLException {
    	UsuarioFone usuarioFone = new UsuarioFone();
        usuarioFone.setTelefone(telefone);
        new UsuarioFoneDAO().alterar(usuarioFone);
    }

    public List<UsuarioFone> listaUsuarioFones() {
    	UsuarioFoneDAO usuarioFoneDao = new UsuarioFoneDAO();
        try {
            return usuarioFoneDao.findUsuarioFone();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar UsuarioFones\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new UsuarioFoneDAO().excluir(id);
    }


}
