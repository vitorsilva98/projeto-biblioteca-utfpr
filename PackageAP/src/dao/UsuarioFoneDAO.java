package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.UsuarioFone;

public class UsuarioFoneDAO extends GenericDAO{
	
	public void salvar(UsuarioFone usuarioFone) throws SQLException {
		try {
		String insert = "INSERT INTO usuariofone(telefone,fkUsuario) VALUES(?,?)";
        save(insert, usuarioFone.getTelefone(), usuarioFone.getFkUsuario());
		} catch (SQLException ex) {
			System.out.println(ex.getErrorCode());
            switch (ex.getErrorCode()) {
                case 1062:
                    JOptionPane.showMessageDialog(null,"Telefone já está cadastrado");
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
	public void alterar(UsuarioFone usuarioFone) throws SQLException {
        String update = "UPDATE usuarioFone " +
                		"SET telefone = ?" +
                		"WHERE Codigo = ?";
        update(update, usuarioFone.getTelefone());
    }

    public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM usuarioFone WHERE Codigo = ?";
        delete(delete, codigo);
    }

    public ArrayList<UsuarioFone> findUsuarioFone() throws SQLException {
    	ArrayList<UsuarioFone> usuarioFones = new ArrayList<UsuarioFone>();
        
        String select = "SELECT * FROM usuarioFone";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	UsuarioFone usuarioFone = new UsuarioFone();
        	usuarioFone.setTelefone(rs.getString("telefone"));
        	usuarioFones.add(usuarioFone);
        }

        rs.close();
        stmt.close();

        return usuarioFones;
    }


}
