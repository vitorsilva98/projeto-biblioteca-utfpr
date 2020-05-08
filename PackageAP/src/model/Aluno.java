package model;

public class Aluno extends Usuario {

	public int ra;
	public int fkUsuario;
	
	public Aluno() {
		super();
	}
	
	public Aluno(int ra, int fkUsuario) {
		super();
		this.ra = ra;
		this.fkUsuario = fkUsuario;
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}
	public int getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(int fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	
	
}
