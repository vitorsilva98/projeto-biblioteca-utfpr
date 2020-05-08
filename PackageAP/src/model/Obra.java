package model;

public class Obra {
	protected int codigo;
	protected String titulo;
	protected int anoPublicacao;
	protected int tipoObra;
	
	
	
	public Obra() {
		super();
	}
	public Obra(int codigo, String titulo, int anoPublicacao, int tipoObra) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
		this.tipoObra = tipoObra;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public int getTipoObra() {
		return tipoObra;
	}
	public void setTipoObra(int tipoObra) {
		this.tipoObra = tipoObra;
	}
	
	

}
