package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Servidor;

public class ServidorDAO extends GenericDAO{
	
	public void salvar(Servidor servidor) throws SQLException {
		try {
		String insert = "INSERT INTO servidor(matriculaSIAPE,fkUsuario) VALUES(?,?)";
        save(insert, servidor.getMatriculaSIAPE(), servidor.getFkUsuario());
        JOptionPane.showMessageDialog(null,"Servidor cadastrado com sucesso");
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
	public void alterar(Servidor servidor) throws SQLException {
        String update = "UPDATE servidor " +
                		"SET SIAPE = ? " +
                		"WHERE Codigo = ?";
        update(update, servidor.getMatriculaSIAPE());
    }

    public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM servidor WHERE Codigo = ?";
        delete(delete, codigo);
    }

    public ArrayList<Servidor> findServidor() throws SQLException {
    	ArrayList<Servidor> servidores = new ArrayList<Servidor>();
        
        String select = "SELECT * FROM servidor";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Servidor servidor = new Servidor();
        	servidor.setMatriculaSIAPE(rs.getInt("SIAPE"));
        	servidores.add(servidor);
        }

        rs.close();
        stmt.close();

        return servidores;
    }


}
