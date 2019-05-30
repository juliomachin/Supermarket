package com.supermercado.service;

import java.util.List;

import com.supermercado.model.Producto;
import com.supermercado.model.User;

public interface ProductoService{
	public List<Producto> findAll(User user);
}
