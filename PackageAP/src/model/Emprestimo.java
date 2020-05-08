package model;

import java.sql.Date;

public class Emprestimo {
	protected int idEmprestimo;
	protected Date dataEmprestimo;
	protected Date dataPrevistaDevolucao;
	protected int fkUsuario;
	
	
	
	public Emprestimo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emprestimo(Date dataEmprestimo, Date dataPrevistaDevolucao, int fkUsuario) {
		super();
		
		this.dataEmprestimo = dataEmprestimo;
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
		this.fkUsuario = fkUsuario;
	}
	
	
	
	public int getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public Date getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}
	public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}
	public int getFkUsuario() {
		return fkUsuario;
	}
	public void setFkUsuario(int fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	
	
	
	
	
}
