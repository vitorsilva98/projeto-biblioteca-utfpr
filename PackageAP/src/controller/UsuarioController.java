package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {
	public UsuarioController () {
		super();
	}
	
	public UsuarioController(Usuario usuario) throws SQLException {
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
	}
	
	public void salvar(String nomeUsuario, String enderecoUsuario, Date dataNasc) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setEnderecoUsuario(enderecoUsuario);
        usuario.setDataNasc(dataNasc);
        new UsuarioDAO().salvar(usuario);
    }

    public void alterar(String nomeUsuario, String enderecoUsuario, Date dataNasc)  throws SQLException {
    	Usuario usuario = new Usuario();
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setEnderecoUsuario(enderecoUsuario);
        usuario.setDataNasc(dataNasc);
        new UsuarioDAO().alterar(usuario);
    }

    public List<Usuario> listaUsuarios() {
    	UsuarioDAO usuarioDao = new UsuarioDAO();
        try {
            return usuarioDao.findUsuario();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Usuarios\n" + e.getLocalizedMessage());
        }
        return null;
    }
    public String getUsuario(int idUsuario) throws SQLException {
    	UsuarioDAO usuarioDao = new UsuarioDAO();
    	System.out.println(usuarioDao.getUsuario(idUsuario));
        try {
            return usuarioDao.getUsuario(idUsuario);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Usuarios\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new UsuarioDAO().excluir(id);
    }


	
}
