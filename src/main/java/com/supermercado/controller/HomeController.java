package com.supermercado.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.supermercado.model.Pedido;
import com.supermercado.model.Producto;
import com.supermercado.model.User;
import com.supermercado.service.PedidoService;
import com.supermercado.service.ProductoService;
import com.supermercado.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductoService productoService;

	@RequestMapping(value="/user/home", method = RequestMethod.GET)
	public ModelAndView myFiles(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if(user != null) {
			
			modelAndView.addObject("user", user);
			modelAndView.addObject("productos", productoService.findAll());
			
			List<Pedido> pedidos = pedidoService.findByUser(user);
			Pedido pedido = null;
			for (Pedido p : pedidos) {
				if(p.getActive() == 1) {
					pedido = p;
					break;
				}
			}
			
			if(pedido != null) {
				modelAndView.addObject("pedido", pedido);
			} else {
				pedido = new Pedido();
				pedido.setActive(1);
				pedido.setUser(user);
				pedido.setProductos(new ArrayList<Producto>());
				modelAndView.addObject("pedido", pedido);
			}
			
			modelAndView.setViewName("/user/home");
			return modelAndView;
		}
		
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}
	
	
	@RequestMapping(value="/user/perfil", method = RequestMethod.GET)
	public ModelAndView myProfile(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if(user != null) {
			modelAndView.addObject("user", user);
			
			modelAndView.setViewName("/user/perfil");
			return modelAndView;
		}
		
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/user/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") long id){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		User borrado = userService.findOne(id);
		
		if(user.isAdmin() || user.equals(borrado)) {
			userService.remove(borrado);
			modelAndView.addObject("user", user);
			modelAndView.addObject("usuarios", userService.findAll());
			modelAndView.addObject("productos", productoService.findAll());
		}
		
		modelAndView.setViewName("/admin/settings");
		return modelAndView;
	}
	
	@RequestMapping(value="/user/pedidos", method = RequestMethod.GET)
	public ModelAndView pedidos(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if(user != null) {
			modelAndView.addObject("user", user);
			modelAndView.addObject("pedidos", pedidoService.findByUser(user));
			
			modelAndView.setViewName("/user/pedidos");
			return modelAndView;
		}
		
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}

}

