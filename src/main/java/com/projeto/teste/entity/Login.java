package com.projeto.teste.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.internal.build.AllowPrintStacktrace;

public class Login {

	@NotEmpty
	private String user;
	@NotNull @AllowPrintStacktrace
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
