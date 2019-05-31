package com.supermercado.service;

import java.util.List;

import com.supermercado.model.Pedido;
import com.supermercado.model.User;

public interface PedidoService {
	List<Pedido> findByUser(User user);
}
