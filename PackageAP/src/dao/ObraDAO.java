package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Obra;

public class ObraDAO extends GenericDAO{
	
	public void salvar(Obra obra) throws SQLException {
		try {
		String insert = "INSERT INTO obra(idObra, titulo, anoPublicacao, fkTipoObra) VALUES(?,?,?,?)";
        save(insert, obra.getCodigo(), obra.getTitulo(), obra.getAnoPublicacao(), obra.getTipoObra());
        
		} catch (SQLException ex) {
			System.out.println(ex.getErrorCode());
            switch (ex.getErrorCode()) {
                case 1062:
                    JOptionPane.showMessageDialog(null,"O livro já está cadastrado");
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
	
	public void alterar(Obra obra) throws SQLException {
        String update = "UPDATE obra " +
                		"SET titulo = ?, anoPublicacao = ?, tipoObbra = ? " +
                		"WHERE codigo = ?";
        update(update, obra.getTitulo(), obra.getAnoPublicacao(), obra.getTipoObra());
    }
	
	public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM obra WHERE Codigo = ?";
        delete(delete, codigo);
    }
	
	public ArrayList<Obra> findObraAll() throws SQLException {
    	ArrayList<Obra> obras = new ArrayList<Obra>();
        
        String select = "SELECT * FROM obra";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Obra obra = new Obra();
        	obra.setCodigo(rs.getInt("idObra"));
        	obra.setTitulo(rs.getString("titulo"));
        	obra.setAnoPublicacao(rs.getInt("anoPublicacao"));
        	obra.setTipoObra(rs.getInt("fkTipoObra"));
        	obras.add(obra);
        }

        rs.close();
        stmt.close();

        return obras;
    }

	public Obra findObraById(int idObra) throws SQLException {
		Obra obra = new Obra();
		String select = "SELECT * FROM obra where idObra = ?";
        PreparedStatement stmt = getCon().prepareStatement(select);
        stmt.setInt(1,idObra);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	obra.setCodigo(rs.getInt("idObra"));
        	obra.setTitulo(rs.getString("titulo"));
        	obra.setAnoPublicacao(rs.getInt("anoPublicacao"));
        	obra.setTipoObra(rs.getInt("fkTipoObra"));
        }

        rs.close();
        stmt.close();

        return obra;
    }

}

