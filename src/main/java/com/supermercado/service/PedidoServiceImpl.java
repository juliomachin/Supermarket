package com.supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.model.Pedido;
import com.supermercado.model.User;
import com.supermercado.repository.PedidoRepository;

@Service("carroService")
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> findByUser(User user) {
		return pedidoRepository.findByUser(user);
	}

}
