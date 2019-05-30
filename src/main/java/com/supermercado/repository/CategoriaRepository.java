package com.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.Categoria;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}