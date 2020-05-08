package model;

import java.sql.Date;

public class Devolucao {

	protected Date dataDevolucao;
	protected int fkEmprestimo;
	
	public Devolucao(Date dataDevolucao, int fkEmprestimo) {
		super();
		this.dataDevolucao = dataDevolucao;
		this.fkEmprestimo = fkEmprestimo;
	}
	public Devolucao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public int getFkEmprestimo() {
		return fkEmprestimo;
	}
	public void setFkEmprestimo(int fkEmprestimo) {
		this.fkEmprestimo = fkEmprestimo;
	}
	
	
	
}
