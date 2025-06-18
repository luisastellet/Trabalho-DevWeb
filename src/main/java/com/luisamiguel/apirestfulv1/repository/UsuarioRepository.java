package com.luisamiguel.apirestfulv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisamiguel.apirestfulv1.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByContaAndSenha(String conta, String senha);
}
