package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Aluno;

public class AlunoDAO extends GenericDAO{
	
	public void salvar(Aluno aluno) throws SQLException {
		try {
		String insert = "INSERT INTO aluno(ra,fkUsuario) VALUES(?,?)";
        save(insert, aluno.getRa(), aluno.getFkUsuario());
        JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso");
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
	public void alterar(Aluno aluno) throws SQLException {
        String update = "UPDATE aluno " +
                		"SET RA = ? " +
                		"WHERE Codigo = ?";
        update(update, aluno.getRa());
    }

    public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM aluno WHERE Codigo = ?";
        delete(delete, codigo);
    }

    public ArrayList<Aluno> findAluno() throws SQLException {
    	ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        
        String select = "SELECT * FROM aluno";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Aluno aluno = new Aluno();
        	aluno.setRa(rs.getInt("Ra"));
        	alunos.add(aluno);
        }

        rs.close();
        stmt.close();

        return alunos;
    }


}
