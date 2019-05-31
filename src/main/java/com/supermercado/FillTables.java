package com.supermercado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.supermercado.model.Producto;
import com.supermercado.model.Role;
import com.supermercado.repository.RoleRepository;
import com.supermercado.service.PedidoService;
import com.supermercado.service.ProductoService;

@Component
public class FillTables implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProductoService productoService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		insertRoles();
		insertProductos();
		return;
	}

	private void insertRoles() {
		if(roleRepository.findAll().isEmpty()) {
			Role role = new Role();
			role.setRole("USER");
			roleRepository.save(role);
			role = new Role();
			role.setRole("ADMIN");
			roleRepository.save(role);
		}
	}

	private void insertProductos() {
		if(productoService.findAll().isEmpty()) {
			Producto producto = new Producto();
			producto.setNombre("Producto 1");
			producto.setDescripcion("Descripcion de producto 1");
			producto.setPrecio(0.00);
			productoService.save(producto);
			
			producto = new Producto();
			producto.setNombre("Producto 2");
			producto.setDescripcion("Descripcion de producto 2");
			producto.setPrecio(9.99);
			productoService.save(producto);
		}
	}

}
