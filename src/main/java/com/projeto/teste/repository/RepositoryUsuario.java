package com.projeto.teste.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.teste.entity.Usuario;

@Repository
public interface RepositoryUsuario extends CrudRepository<Usuario, Long> {

	Usuario findByFirstName(String firstName);
	
	Usuario findByEmail(String email);
	
}