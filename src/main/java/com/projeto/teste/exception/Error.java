package com.projeto.teste.exception;

public class Error {

	private int codigo;
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public Error(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
