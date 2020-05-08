package model;

public class ExemplarEmprestimo {
	
	private int idExemplar;
	private int idEmprestimo;
	
	public ExemplarEmprestimo () {
		super();
	}
	
	
	
	public ExemplarEmprestimo(int idExemplar, int idEmprestimo) {
		super();
		this.idExemplar = idExemplar;
		this.idEmprestimo = idEmprestimo;
	}



	public int getIdExemplar() {
		return idExemplar;
	}
	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}
	public int getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
	
	
	
}
