package com.projeto.teste.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.teste.entity.Pessoa;
import com.projeto.teste.repository.RepositoryPessoa;
import com.projeto.teste.security.TokenUtil;

@RestController
@RequestMapping(path = "/")
public class ControllerPessoa {

	@Autowired
	private RepositoryPessoa repositorioPessoa;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private TokenUtil token;

	@PostMapping("/users/sign-up")
	public void signUp(@RequestBody Pessoa pessoa, HttpServletResponse res) throws IOException {
		pessoa.setPassword(bCryptPasswordEncoder.encode(pessoa.getPassword()));
		repositorioPessoa.save(pessoa);

		res.getWriter().write(token.criarToken(pessoa));
		res.getWriter().flush();
	}

	@GetMapping
	public String lista() {
		return "<html><center><h1>Lista Pessoa</h1></center></html>";
	}

	@PostMapping
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return repositorioPessoa.save(pessoa);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) throws Exception {

//		throw new Exception();
		
		return repositorioPessoa.findById(id).map(p -> {
			repositorioPessoa.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {

		return repositorioPessoa.findById(id).map(p -> {
			p.setNome(pessoa.getNome());
			Pessoa atualiza = repositorioPessoa.save(p);
			return ResponseEntity.ok().body(atualiza);
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = "/lista")
	public Iterable<Pessoa> findAll() {
		return repositorioPessoa.findAll();
	}
	
	@GetMapping(path = "/teste")
	public void teste() {
		throw new NullPointerException();
	}
	

}
