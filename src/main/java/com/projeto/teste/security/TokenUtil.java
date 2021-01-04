package com.projeto.teste.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projeto.teste.entity.Pessoa;

@Component
public class TokenUtil {

	public String criarToken(Pessoa pessoa) {
		String token = SecurityConstants.TOKEN_PREFIX + JWT.create().withSubject(pessoa.getNome())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

		String usuarioToken = "Usuario " + pessoa.getNome() + " Token: " + token;

		return usuarioToken;
	}

}
