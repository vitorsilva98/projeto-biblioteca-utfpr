package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoController {
	public AlunoController(Aluno aluno) throws SQLException {
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.salvar(aluno);
	}
	
	public void salvar(int ra) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setRa(ra);
        new AlunoDAO().salvar(aluno);
    }

    public void alterar(int ra) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setRa(ra);
    	new AlunoDAO().alterar(aluno);
    }

    public List<Aluno> listaAlunos() {
    	AlunoDAO alunoDao = new AlunoDAO();
        try {
            return alunoDao.findAluno();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar Alunos\n" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(int id) throws SQLException {
        new AlunoDAO().excluir(id);
    }
    


}
