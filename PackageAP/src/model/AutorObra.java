package model;

public class AutorObra {
	private int idAutor;
	private int idObra;
	
	public AutorObra() {
		
		super();
	}

	public AutorObra(int fkAutor, int fkObra) {
		super();
		this.idAutor = fkAutor;
		this.idObra = fkObra;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	
	
}
