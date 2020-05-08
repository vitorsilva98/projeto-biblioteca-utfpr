package model;

public class Servidor extends Usuario{
	
	private int matriculaSIAPE;

	private int fkUsuario;
	
	public Servidor() {
		super();
	}
	
	public Servidor(int matriculaSIAPE, int fkUsuario) {
		super();
		this.matriculaSIAPE = matriculaSIAPE;
		this.fkUsuario = fkUsuario;
	}

	public int getMatriculaSIAPE() {
		return matriculaSIAPE;
	}

	public void setMatriculaSIAPE(int matriculaSIAPE) {
		this.matriculaSIAPE = matriculaSIAPE;
	}
	public int getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(int fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	
}
