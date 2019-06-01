package com.supermercado.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.supermercado.model.Pedido;
import com.supermercado.model.Producto;
import com.supermercado.model.User;
import com.supermercado.service.PedidoService;
import com.supermercado.service.ProductoService;
import com.supermercado.service.UserService;

@Controller
public class PeticionesController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value="/admin/settings", method = RequestMethod.GET)
	public ModelAndView admin(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if(user != null) {
			if(user.isAdmin()) {
				modelAndView.addObject("user", user);
				modelAndView.addObject("usuarios", userService.findAll());
				modelAndView.addObject("productos", productoService.findAll());
				modelAndView.setViewName("/admin/settings");
				return modelAndView;
			}
			modelAndView.setView(new RedirectView("/user/home"));
			return modelAndView;
		}
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}


	@RequestMapping(value="/add/producto", method = RequestMethod.GET)
	public String addProduct(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("precio") double precio){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if(user.isAdmin()) {
			Producto p = new Producto();
			p.setNombre(nombre);
			p.setDescripcion(descripcion);
			p.setPrecio(precio);
			productoService.save(p);
		}
		return "admin/settings :: productos";
	}

	@RequestMapping(value="/delete/producto/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") long id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Producto borrado = productoService.findbyId(id);
		if(user.isAdmin() && borrado != null) {
			productoService.remove(borrado);
		}

		return "admin/settings :: productos";
	}
	
	@RequestMapping(value="/pedido/{id}/{cant}")
	public String pedido(Model model, @PathVariable("id") long id, @PathVariable("cant") int cant){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Producto producto = productoService.findbyId(id);
		List<Pedido> pedidos = pedidoService.findByUser(user);
		
		Pedido pedido = null;
		for (Pedido p : pedidos) {
			if(p.getActive() == 1) {
				pedido = p;
				break;
			}
		}
		
		if(pedido != null) {
			for (int i = 0; i < cant; i++) {
				pedido.getProductos().add(producto);
			}
			pedidoService.save(pedido);
		} else {
			pedido = new Pedido();
			pedido.setActive(1);
			pedido.setUser(user);
			
			List<Producto> pr = new ArrayList<Producto>();
			for (int i = 0; i < cant; i++) {
				pr.add(producto);
			}
			pedido.setProductos(pr);
			
			pedidoService.save(pedido);
		}
		model.addAttribute("pedido", pedido);
		
		return "user/home :: cesta";
	}
	
	@RequestMapping(value="/pedido/procesar")
	public String procesarPedido(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Pedido> pedidos = pedidoService.findByUser(user);
		
		Pedido pedido = null;
		for (Pedido p : pedidos) {
			if(p.getActive() == 1) {
				pedido = p;
				break;
			}
		}
		
		if(pedido != null) {
			pedido.setActive(0);
			pedidoService.save(pedido);
			
			pedido = new Pedido();
			pedido.setActive(1);
			pedido.setUser(user);
			pedido.setProductos(new ArrayList<Producto>());
			pedidoService.save(pedido);
		} else {
			pedido = new Pedido();
			pedido.setActive(1);
			pedido.setUser(user);
			pedido.setProductos(new ArrayList<Producto>());
			pedidoService.save(pedido);
		}
		model.addAttribute("pedido", pedido);
		
		return "user/home :: cesta";
	}
	
	@RequestMapping(value="/pedido/quitar/{id}")
	public String quitarDePedido(Model model, @PathVariable("id") long id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Producto producto = productoService.findbyId(id);
		List<Pedido> pedidos = pedidoService.findByUser(user);
		
		Pedido pedido = null;
		for (Pedido p : pedidos) {
			if(p.getActive() == 1) {
				pedido = p;
				break;
			}
		}
		
		if(pedido != null) {
			pedido.getProductos().remove(producto);
			pedidoService.save(pedido);
		}
		model.addAttribute("pedido", pedido);
		
		return "user/home :: cesta";
	}
	
	@RequestMapping(value="/pedido/ver/{id}")
	public String verPedido(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<Pedido> pedidos = pedidoService.findByUser(user);
		Pedido pedido = null;
		for (Pedido p : pedidos) {
			if(p.getActive() == 1) {
				pedido = p;
				break;
			}
		}
		
		if(pedido == null) {
			pedido = new Pedido();
			pedido.setActive(1);
			pedido.setUser(user);
			pedido.setProductos(new ArrayList<Producto>());
			pedidoService.save(pedido);
		}
		model.addAttribute("pedido", pedido);
		
		return "user/home :: cesta";
	}
	
	@RequestMapping(value="/user/perfil/update", method = RequestMethod.GET)
	public String cambiarPerfil(Model model, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("password") String password){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		user.setName(nombre);
		user.setLastName(apellido);
		
		if(password.equals("")) {
			userService.update(user);
		}else {
			user.setPassword(password);
			userService.save(user);
		}

		model.addAttribute("user", user);
		
		return "user/perfil :: datos";
	}


}