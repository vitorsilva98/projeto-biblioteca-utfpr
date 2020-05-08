package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Exemplar;


public class ExemplarDAO extends GenericDAO{
	
	public void salvar(Exemplar exemplar) throws SQLException {
		try {
		String insert = "INSERT INTO exemplar(dataAquisicao, fkObra, fkSituacao) VALUES(?,?,?)";
        save(insert, exemplar.getDataAquisicao(),exemplar.getFkObra(), exemplar.getFkSituacao());
        JOptionPane.showMessageDialog(null,"Exemplar cadastrado com sucesso");
		
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
	
	public void alterar(Exemplar exemplar) throws SQLException {
        String update = "UPDATE exemplar " +
                		"SET fkSituacao = ? " +
                		"WHERE idExemplar = ? ";
        update(update, exemplar.getCodigo(), exemplar.getFkSituacao());
    }
	
	public void excluir(int codigo) throws SQLException {
        String delete = "DELETE FROM eexemplar WHERE Codigo = ?";
        delete(delete, codigo);
    }
	
	public ArrayList<Exemplar> findExemplarAll() throws SQLException {
    	ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
        
        String select = "SELECT * FROM exemplar";

        PreparedStatement stmt = getCon().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Exemplar exemplar = new Exemplar();
        	exemplar.setDataAquisicao(rs.getDate("DataAquisicao"));
        	exemplar.setFkSituacao(rs.getInt("situacao"));
        	exemplares.add(exemplar);
        }

        rs.close();
        stmt.close();

        return exemplares;
    }

	public ArrayList<Exemplar> findExemplarbySituacao(int fkObra, int fkSituacao) throws SQLException {
    	ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
        
        String select = "SELECT * FROM exemplar where fkObra = ? AND fkSituacao = ?";
        
        PreparedStatement stmt = getCon().prepareStatement(select);
        stmt.setInt(1, fkObra);
        stmt.setInt(2, fkSituacao);
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Exemplar exemplar = new Exemplar();
        	exemplar.setCodigo(rs.getInt("idExemplar"));
        	exemplar.setDataAquisicao(rs.getDate("dataAquisicao"));
        	exemplar.setFkSituacao(rs.getInt("fkSituacao"));
        	exemplares.add(exemplar);
        }

        rs.close();
        stmt.close();

        return exemplares;
    }

	public Exemplar findObraByIdExemplar(int idExemplar, int fkSituacao) throws SQLException {
    	String select = "SELECT * FROM exemplar where idExemplar = ? AND fkSituacao = ?";
    	Exemplar exemplar = new Exemplar();
    	PreparedStatement stmt = getCon().prepareStatement(select);
        stmt.setInt(1, idExemplar);
        stmt.setInt(2, fkSituacao);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
        	exemplar.setCodigo(rs.getInt("idExemplar"));
        	exemplar.setDataAquisicao(rs.getDate("dataAquisicao"));
        	exemplar.setFkObra(rs.getInt("fkObra"));
        	exemplar.setFkSituacao(rs.getInt("fkSituacao"));
        }

        rs.close();
        stmt.close();

        return exemplar;
    }


}
