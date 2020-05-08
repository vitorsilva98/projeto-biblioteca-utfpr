package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.EmprestimoDAO;
import model.Emprestimo;

public class EmprestimoController {
	
	public EmprestimoController(Emprestimo emprestimo) throws SQLException {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		emprestimoDAO.salvar(emprestimo);
	}
	
	public void salvar(Date dataEmprestimo, Date dataPrevistaDevolucao) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataPrevistaDevolucao(dataPrevistaDevolucao);
        new EmprestimoDAO().salvar(emprestimo);
    }

    
	
	public void alterar(Date dataEmprestimo, Date dataPrevistaDevolucao) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataPrevistaDevolucao(dataPrevistaDevolucao);
       new EmprestimoDAO().alterar(emprestimo);
    }

    public List<Emprestimo> listaEmprestimos() {
    	EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        try {
            return emprestimoDao.findEmprestimoAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Emprestimos\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new EmprestimoDAO().excluir(id);
    }
    
    public int getLastId() {
    	EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        try {
            return emprestimoDao.getLastId();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Emprestimos\n" + e.getLocalizedMessage());
        }
        return -1;
    }

    
}
