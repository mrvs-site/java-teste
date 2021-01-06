package com.projeto.teste.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.teste.entity.Login;
import com.projeto.teste.entity.Usuario;
import com.projeto.teste.repository.RepositoryUsuario;
import com.projeto.teste.security.SecurityConstants;
import com.projeto.teste.security.TokenUtil;

@RestController
@RequestMapping(path = "/")
public class ControllerPessoa {

	@Autowired
	private RepositoryUsuario repositorioUsuario;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private TokenUtil token;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody Usuario usuario, HttpServletResponse res) throws IOException {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		repositorioUsuario.save(usuario);

		return ResponseEntity.ok().body(token.criarToken(usuario));
	}

	@GetMapping
	public String lista() {
		return "<html><center><h1>Lista Pessoa</h1></center></html>";
	}

	@PostMapping
	public Usuario adicionar(@RequestBody Usuario usuario) {
		return repositorioUsuario.save(usuario);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) throws Exception {

//		throw new Exception();

		return repositorioUsuario.findById(id).map(p -> {
			repositorioUsuario.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") long id, @RequestBody Usuario usuario) {

		return repositorioUsuario.findById(id).map(p -> {
			p.setFirstName(usuario.getFirstName());
			Usuario atualiza = repositorioUsuario.save(p);
			return ResponseEntity.ok().body(atualiza);
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = "/lista")
	public Iterable<Usuario> findAll() {
		return repositorioUsuario.findAll();
	}

	@GetMapping(path = "/teste")
	public void teste() {
		throw new NullPointerException();
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Login login) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUser(), login.getPassword()));
		Usuario u = repositorioUsuario.findByEmail(login.getUser());

		return ResponseEntity.ok().body(token.criarToken(u));
	}

	@GetMapping("/me")
	public ResponseEntity<?> me(HttpServletRequest request) {

		String token = request.getHeader(SecurityConstants.HEADER_STRING);

		UsernamePasswordAuthenticationToken usuario = this.token.getAuthentication(token);

		return ResponseEntity.ok().body(repositorioUsuario.findByEmail(usuario.getPrincipal().toString()));

	}

}
