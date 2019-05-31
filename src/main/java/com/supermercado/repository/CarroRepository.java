package com.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.Pedido;

@Repository("carroRepository")
public interface CarroRepository extends JpaRepository<Pedido, Long>{

}