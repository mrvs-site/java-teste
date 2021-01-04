package com.projeto.teste.service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.teste.entity.Pessoa;
import com.projeto.teste.repository.RepositoryPessoa;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private RepositoryPessoa repositorioPessoa;

    public UserDetailsServiceImpl(RepositoryPessoa applicationUserRepository) {
        this.repositorioPessoa = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Pessoa pessoa = repositorioPessoa.findByNome(nome);
        if (pessoa == null) {
            throw new UsernameNotFoundException(nome);
        }
        return new User(pessoa.getNome(), pessoa.getPassword(), emptyList());
    }
}