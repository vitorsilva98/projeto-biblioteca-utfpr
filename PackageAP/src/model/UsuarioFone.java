package model;

public class UsuarioFone {

	protected String telefone;
	protected int fkUsuario;
		
	public UsuarioFone() {
		super();
	}
	
	public UsuarioFone(String telefone, int fkUsuario) {
		super();
		this.telefone = telefone;
		this.fkUsuario = fkUsuario;

	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(int fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	
	
}
