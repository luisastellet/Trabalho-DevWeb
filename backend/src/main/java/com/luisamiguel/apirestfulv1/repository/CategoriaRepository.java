package com.luisamiguel.apirestfulv1.repository;

import com.luisamiguel.apirestfulv1.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
