package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Devolucao;

public class DevolucaoDAO extends GenericDAO{
	
	public void salvar(Devolucao devolucao) throws SQLException {
		try {
		String insert = "INSERT INTO devolucao(dataDevolucao,fkEmprestimo) VALUES(?,?)";
        save(insert, devolucao.getDataDevolucao(), devolucao.getFkEmprestimo());
        JOptionPane.showMessageDialog(null,"Devolucao registrada com sucesso");
		
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
	
	public void alterar(Devolucao devolucao) throws SQLException {
        String update = "UPDATE devolucao " +
                		"SET dataDevolucao = ? " +
                		"WHERE codigo = ?";
        update(update, devolucao.getDataDevolucao());
    }
	
	public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM emprestimo WHERE Codigo = ?";
        delete(delete, codigo);
    }
	
	public ArrayList<Devolucao> findDevolucaoAll() throws SQLException {
    	ArrayList<Devolucao> devolucoes = new ArrayList<Devolucao>();
        String select = "SELECT * FROM devolucao";
        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	Devolucao devolucao = new Devolucao();
        	devolucao.setDataDevolucao(rs.getDate("dataDevolucao"));
        	devolucoes.add(devolucao);
        }
        rs.close();
        stmt.close();
        return devolucoes;
    }

}
