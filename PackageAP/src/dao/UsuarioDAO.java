package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Usuario;



public class UsuarioDAO extends GenericDAO{

	public void salvar(Usuario usuario) throws SQLException {
		System.out.print("Salvar");
		try {
		String insert = "INSERT INTO usuario(idUsuario, nomeUsuario, endereco, dataNascimento) VALUES(?,?,?,?)";
        save(insert, usuario.getIdUsuario(),usuario.getNomeUsuario(), usuario.getEnderecoUsuario(), usuario.getDataNasc());
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
	
	public void alterar(Usuario usuario) throws SQLException {
        String update = "UPDATE usuario " +
                		"SET nomeUsuario = ? , endereco = ?, dataNascimento = ?" +
                		"WHERE Codigo = ?";
        update(update, usuario.getNomeUsuario(), usuario.getEnderecoUsuario(), usuario.getDataNasc());
    }

    public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM usuario WHERE Codigo = ?";
        delete(delete, codigo);
    }

    public ArrayList<Usuario> findUsuario() throws SQLException {
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        String select = "SELECT * FROM usuario";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Usuario usuario = new Usuario();
        	usuario.setNomeUsuario(rs.getString("nomeUsuario"));
        	usuario.setEnderecoUsuario(rs.getString("enderecoUsuario"));
        	usuario.setDataNasc(rs.getDate("dataNascimento"));
        	usuarios.add(usuario);
        }

        rs.close();
        stmt.close();

        return usuarios;
    }
    public String getUsuario(int idUsuario) throws SQLException {

        String select = "SELECT nomeUsuario FROM usuario where idUsuario=?";
        PreparedStatement stmt = getCon().prepareStatement(select);
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();
        String usuario = "";
        while (rs.next()) {
            
        usuario = rs.getString("nomeUsuario");
        }
        rs.close();
        stmt.close();

        return usuario;
                
                   
    }



}
