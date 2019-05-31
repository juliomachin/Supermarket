package com.supermercado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.Pedido;
import com.supermercado.model.User;

@Repository("carroRepository")
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	List<Pedido> findByUser(User user);
}