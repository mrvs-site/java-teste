package com.projeto.teste.entity;

public class UsuarioToken {

	private String usuario;

	private String token;

	public UsuarioToken(String usuario, String token) {
		super();
		this.usuario = usuario;
		this.token = token;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
