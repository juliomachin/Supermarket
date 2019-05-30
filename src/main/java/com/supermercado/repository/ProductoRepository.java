package com.supermercado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	List<Producto> findAll();
}