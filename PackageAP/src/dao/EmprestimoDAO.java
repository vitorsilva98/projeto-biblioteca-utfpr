package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Emprestimo;
import dao.GenericDAO;

public class EmprestimoDAO  extends GenericDAO{
	
	public void salvar(Emprestimo emprestimo) throws SQLException {
		try {
		String insert = "INSERT INTO emprestimo(dataEmprestimo, dataPrevistaDevolucao,fkUsuario) VALUES(?,?,?)";
        save(insert, emprestimo.getDataEmprestimo(), emprestimo.getDataPrevistaDevolucao(), emprestimo.getFkUsuario());
        JOptionPane.showMessageDialog(null,"Emprestimo registrado com sucesso");
		
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
	
	public void alterar(Emprestimo emprestimo) throws SQLException {
        String update = "UPDATE emprestimo " +
                		"SET dataEmprestimo = ?, dataDevolucao = ? " +
                		"WHERE codigo = ?";
        update(update, emprestimo.getDataEmprestimo(), emprestimo.getDataPrevistaDevolucao());
    }
	
	public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM emprestimo WHERE Codigo = ?";
        delete(delete, codigo);
    }
	
	public ArrayList<Emprestimo> findEmprestimoAll() throws SQLException {
    	ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
        String select = "SELECT * FROM emprestimo";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Emprestimo emprestimo = new Emprestimo();
        	emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo"));
        	emprestimo.setDataPrevistaDevolucao(rs.getDate("dataDevolucao"));
        	emprestimos.add(emprestimo);
        }

        rs.close();
        stmt.close();

        return emprestimos;
    }
	public ArrayList<Emprestimo> findEmprestimoAllByUsuario(int idUsuario) throws SQLException {
    	ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        String select = "SELECT * FROM emprestimo WHERE fkUsuario = ?";
        
        PreparedStatement stmt = getCon().prepareStatement(select);
        stmt.setInt(1,idUsuario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Emprestimo emprestimo = new Emprestimo();
        	emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
        	emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo"));
        	emprestimo.setDataPrevistaDevolucao(rs.getDate("dataPrevistaDevolucao"));
        	emprestimo.setFkUsuario(rs.getInt("fkUsuario"));
        	emprestimos.add(emprestimo);
        }

        rs.close();
        stmt.close();
        
        return emprestimos;
    }

	
	
	
	public int getLastId() throws SQLException {
		String select = "select max(idEmprestimo) from emprestimo";
		PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int lastid = -1;
		rs = stmt.executeQuery();
		while(rs.next()) {
			lastid = rs.getInt("max(idEmprestimo)");
		}
		return lastid;
	}
	
	
}
