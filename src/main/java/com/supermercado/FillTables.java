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
			role.setRole("ADMIN");
			roleRepository.save(role);
			role = new Role();
			role.setRole("USER");
			roleRepository.save(role);
		}
	}

	private void insertProductos() {
		if(productoService.findAll().isEmpty()) {
			Producto producto = new Producto();
			producto.setNombre("Alubia Blanca");
			producto.setDescripcion("Alubia cocida blanca, tarro 570 g escurrido 400 g");
			producto.setPrecio(0.80);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Lenteja Cocida");
			producto.setDescripcion("Lenteja cocida pardina, tarro 314 g escurrido 210 g");
			producto.setPrecio(0.67);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Garbanzo Cocido");
			producto.setDescripcion("Garbanzo cocido, tarro 314 g escurrido 210 g");
			producto.setPrecio(0.73);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Sal Fina");
			producto.setDescripcion("Sal fina seca yodada, paquete 1 kg");
			producto.setPrecio(0.24);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Sal Gruesa");
			producto.setDescripcion("Sal gruesa para cocinar, paquete 1 kg");
			producto.setPrecio(0.26);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Sal Fina Marina");
			producto.setDescripcion("Sal fina seca normal, paquete 1 kg");
			producto.setPrecio(0.29);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Canela");
			producto.setDescripcion("Canela molida (tapon rojo), tarro 52 g");
			producto.setPrecio(0.69);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Pimenton Dulce");
			producto.setDescripcion("Pimenton rojo dulce (tapon rojo), tarro 56 g");
			producto.setPrecio(0.75);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Oregano");
			producto.setDescripcion("Oregano (tapon verde), tarro 18 g");
			producto.setPrecio(0.76);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Colorante");
			producto.setDescripcion("Colorante alimentario (tapon naranja), tarro 85 g");
			producto.setPrecio(9.99);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Fideo");
			producto.setDescripcion("Fideo fino cabello angel pasta, paquete 750 g");
			producto.setPrecio(0.86);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Fideo Grueso");
			producto.setDescripcion("Fideo grueso pasta, paquete 500 g");
			producto.setPrecio(0.94);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Yatekomo Oriental");
			producto.setDescripcion("Yatekomo fideos orientales deshidratados, bote 61 g");
			producto.setPrecio(1.50);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Yatekomo Pollo");
			producto.setDescripcion("Yatekomo fideos orientales pollo deshidratados, bote 60 g");
			producto.setPrecio(1.50);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Yakisoba fideos");
			producto.setDescripcion("Yakisoba fideos deshidratados orientales al curri (2 raciones), paquete 120 g");
			producto.setPrecio(2.07);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Yakisoba Fideos con Pollo");
			producto.setDescripcion("Yakisoba fideos orientales deshidratados gourmet pollo (con pollo verduras y salsa oriental), bote 93 g");
			producto.setPrecio(2.16);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Yakisoba Fideos Clasico");
			producto.setDescripcion("Yakisoba fideos orientales deshidratados gourmet clasico (con verduras y salsa oriental), bote 93 g");
			producto.setPrecio(2.16);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Cous Cous");
			producto.setDescripcion("Cous cous mediano, paquete 1 kg");
			producto.setPrecio(2.11);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Quinoa");
			producto.setDescripcion("Quinoa -fuente de proteinas y fibra, paquete 500 g");
			producto.setPrecio(3.79);
			productoService.save(producto);



			producto = new Producto();
			producto.setNombre("Magret de Pato");
			producto.setDescripcion("Magret de pato fresco, pieza 380gr. aprox");
			producto.setPrecio(7.22);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Carcasa y Espinazo de Pollo");
			producto.setDescripcion("Carcasa y espinazo de pollo, bandeja 1000gr. aprox");
			producto.setPrecio(1.15);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Higado de Pollo");
			producto.setDescripcion("Higado y corazones de pollo - menudillos, tarrina 450gr. aprox");
			producto.setPrecio(1.44);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Mollejas de Pollo");
			producto.setDescripcion("Mollejas de pollo limpias, tarrina 400gr. aprox");
			producto.setPrecio(1.74);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Magret de Ganso");
			producto.setDescripcion("Magret de ganso fresco, pieza 380gr. aprox");
			producto.setPrecio(7.22);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Carcasa y Espinazo de Conejo");
			producto.setDescripcion("Carcasa y espinazo de conejo, bandeja 1000gr. aprox");
			producto.setPrecio(1.15);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Higado de Vacuno");
			producto.setDescripcion("Higado y corazones de vaca - menudillos, tarrina 450gr. aprox");
			producto.setPrecio(1.44);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Mollejas de Vacuno");
			producto.setDescripcion("Mollejas de vaca limpias, tarrina 400gr. aprox");
			producto.setPrecio(4.74);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Higado de Cerdo");
			producto.setDescripcion("Higado y corazones de cerdo - menudillos, tarrina 450gr. aprox");
			producto.setPrecio(1.44);
			productoService.save(producto);


			producto = new Producto();
			producto.setDescripcion("Mollejas de cerdo limpias, tarrina 400gr. aprox");
			producto.setPrecio(4.74);
			productoService.save(producto);



			producto = new Producto();
			producto.setNombre("Hueso de Vacuno");
			producto.setDescripcion("Hueso de vacuno, bandeja 250gr. aprox");
			producto.setPrecio(0.72);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Costilla de Vacuno");
			producto.setDescripcion("Costilla de vacuno añojo, bandeja 250gr. aprox");
			producto.setPrecio(1.35);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Arreglos de Vacuno");
			producto.setDescripcion("Trozo de vacuno añojo para cocido, bandeja 300gr. aprox");
			producto.setPrecio(2.37);
			productoService.save(producto);


			producto = new Producto();
			producto.setNombre("Carpaccio Vacuno");
			producto.setDescripcion("Carpaccio filetes finos de vacuno y queso, bandeja 110 g");
			producto.setPrecio(2.99);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Mini-Hamburguesas de Pollo");
			producto.setDescripcion("Mini hamburguesas pollo - burger meat, bandeja 8 uds - 200gr.");
			producto.setPrecio(1.50);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Mini-Hamburguesas de Vacuno");
			producto.setDescripcion("Mini hamburguesas vacuno - burger meat, bandeja 8 uds - 200gr.");
			producto.setPrecio(1.54);
			productoService.save(producto);

			producto = new Producto();
			producto.setNombre("Mini-Hamburguesas de Cerdo");
			producto.setDescripcion("Mini hamburguesas cerdo - burger meat, bandeja 8 uds - 200gr.");
			producto.setPrecio(1.54);
			productoService.save(producto);
		}
	}

}
