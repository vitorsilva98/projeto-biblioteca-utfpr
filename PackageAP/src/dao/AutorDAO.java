package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Autor;

public class AutorDAO  extends GenericDAO{
	
	public void salvar(Autor autor) throws SQLException {
		try {
		String insert = "INSERT INTO autor(nomeAutor) VALUES(?)";
        save(insert, autor.getNomeAutor());
        JOptionPane.showMessageDialog(null,"Autor Cadastrado com Sucesso!");
        
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
	public void alterar(Autor autor) throws SQLException {
        String update = "UPDATE autor " +
                		"SET nomeAutor = ? " +
                		"WHERE Codigo = ?";
        update(update, autor.getNomeAutor());
    }

    public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM autor WHERE Codigo = ?";
        delete(delete, codigo);
    }

    public ArrayList<Autor> findAutor() throws SQLException {
    	ArrayList<Autor> autores = new ArrayList<Autor>();
        
        String select = "SELECT * FROM autor";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Autor autor = new Autor();
        	autor.setNomeAutor(rs.getString("nomeAutor"));
        	autores.add(autor);
        }

        rs.close();
        stmt.close();

        return autores;
    }


}
