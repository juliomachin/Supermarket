package com.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.Carro;

@Repository("carroRepository")
public interface CarroRepository extends JpaRepository<Carro, Long>{

}