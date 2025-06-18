package com.luisamiguel.apirestfulv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luisamiguel.apirestfulv1.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
