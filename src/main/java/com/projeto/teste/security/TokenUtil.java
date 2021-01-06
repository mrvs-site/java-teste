package com.projeto.teste.security;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projeto.teste.entity.Usuario;
import com.projeto.teste.entity.UsuarioToken;
import com.projeto.teste.exception.JsonApiErrorMessage;

@Component
public class TokenUtil {

	private UsuarioToken userToken; 

	public UsuarioToken criarToken(Usuario usuario) {
		String token = SecurityConstants.TOKEN_PREFIX + JWT.create().withSubject(usuario.getFirstName())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
		
		return new UsuarioToken(usuario.getFirstName(), token);
	}
	
	
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			if (token != null) {
				// parse the token.
				String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
						.verify(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getSubject();

				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			}

		} catch (Exception e) {
			new JsonApiErrorMessage().setErro(e);
		}
		return null;
	}

}
