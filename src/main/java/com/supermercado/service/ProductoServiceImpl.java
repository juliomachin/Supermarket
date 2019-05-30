package com.supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermercado.model.Producto;
import com.supermercado.model.User;
import com.supermercado.repository.ProductoRepository;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<Producto> findAll(User user) {
		if(user.isAdmin())
			return productoRepository.findAll();
		return null;
	}

}
