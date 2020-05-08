package model;

import java.sql.Date;

public class Exemplar {

	protected Date dataAquisicao;
	protected int fkSituacao;
	protected int fkObra;
	protected int codigo;
	
	
	public Exemplar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exemplar(Date dataAquisicao, int fkSituacao, int fkObra) {
		super();
		this.dataAquisicao = dataAquisicao;
		this.fkSituacao = fkSituacao;
		this.fkObra = fkObra;
	}
	
	public Date getDataAquisicao() {
		return dataAquisicao;
	}
	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getFkSituacao() {
		return fkSituacao;
	}
	public void setFkSituacao(int fkSituacao) {
		this.fkSituacao = fkSituacao;
	}
	public int getFkObra() {
		return fkObra;
	}
	public void setFkObra(int fkObra) {
		this.fkObra = fkObra;
	}
	
	
	
}
