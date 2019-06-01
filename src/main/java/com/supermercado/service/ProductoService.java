package com.supermercado.service;

import java.util.List;

import com.supermercado.model.Producto;

public interface ProductoService{
	public List<Producto> findAll();
	public void save(Producto producto);
	public Producto findbyId(long id);
	public void remove(Producto borrado);
}
