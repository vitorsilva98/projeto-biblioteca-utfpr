package model;

import java.sql.Date;
import java.util.ArrayList;

public class Usuario {
	protected int idUsuario;
	protected String nomeUsuario;
	protected String enderecoUsuario;
	protected Date dataNasc;
	protected ArrayList<UsuarioFone> usuarioFone = new ArrayList<UsuarioFone>();
	
	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String nomeUsuario, String enderecoUsuario, Date dataNasc) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.enderecoUsuario = enderecoUsuario;
		this.dataNasc = dataNasc;
	}
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public void addTelefone (UsuarioFone telefone) {
		usuarioFone.add(telefone);
	}
	
	public void removeTelefone (UsuarioFone telefone) {
		usuarioFone.remove(telefone);
	}
	
	
}
