package com.projeto.teste.repository;

import org.springframework.data.repository.CrudRepository;

import com.projeto.teste.entity.Pessoa;

public interface RepositoryPessoa extends CrudRepository<Pessoa, Long> {

	Pessoa findByNome(String nome);
}