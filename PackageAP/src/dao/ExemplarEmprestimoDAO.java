package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.ExemplarEmprestimo;

public class ExemplarEmprestimoDAO extends GenericDAO{
	
	public void salvar(ExemplarEmprestimo exemplarEmprestimo) throws SQLException {
		try {
		String insert = "INSERT INTO exemplar_emprestimo(fkExemplar, fkEmprestimo) VALUES(?,?)";
		save(insert, exemplarEmprestimo.getIdExemplar(), exemplarEmprestimo.getIdEmprestimo());
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

	
	
	

    public ArrayList<ExemplarEmprestimo> findExemplarEmprestimo() throws SQLException {
    	ArrayList<ExemplarEmprestimo> exemplarEmprestimos = new ArrayList<ExemplarEmprestimo>();
        
        String select = "SELECT * FROM exemplar_emprestimo";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	ExemplarEmprestimo exemplarEmprestimo = new ExemplarEmprestimo();
        	exemplarEmprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
        	exemplarEmprestimo.setIdExemplar(rs.getInt("idExemplar"));
        	exemplarEmprestimos.add(exemplarEmprestimo);
        }

        rs.close();
        stmt.close();

        return exemplarEmprestimos;
    }

    public ExemplarEmprestimo findExemplarByIdEmprestimo(int fkEmprestimo) throws SQLException {
    	ExemplarEmprestimo exemplarEmprestimo = new ExemplarEmprestimo();
    	String select = "SELECT * FROM exemplar_emprestimo where fkEmprestimo = ?";
        PreparedStatement stmt = getCon().prepareStatement(select);
        stmt.setInt(1,fkEmprestimo);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
        	exemplarEmprestimo.setIdExemplar(rs.getInt("fkExemplar"));
        	exemplarEmprestimo.setIdEmprestimo(rs.getInt("fkEmprestimo"));
        }
                rs.close();
        stmt.close();

        return exemplarEmprestimo;
    }

    
}
