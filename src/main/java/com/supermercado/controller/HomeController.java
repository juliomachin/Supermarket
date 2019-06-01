package com.supermercado.controller;

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
		}
		
		modelAndView.setViewName("/home");
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

//	@RequestMapping(value = "/user/files/{id_folder}", method = RequestMethod.GET)
//	public ModelAndView getFolderFiles(@PathVariable("id_folder") String nid) {
//		ModelAndView modelAndView = new ModelAndView();
//		try {
//			Long id = Long.parseLong(Encryptor.decrypt(nid));
//			
//			Folder folder = folderService.findById(id);
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			User user = userService.findUserByEmail(auth.getName());
//			
//			if(user.getSharedFolders().contains(folder) || folder.getOwner().equals(user)) {
//				modelAndView.addObject("user", user);
//				modelAndView.addObject("root", folder);
//				modelAndView.addObject("notifications", userService.findConfirms(user));
//				modelAndView.addObject("notifies", notifyService.findByType("Advice"));
//				
//				List<File> files = new ArrayList<File>(folder.getFiles());
//				Collections.sort(files, new Comparator<File>(){
//		           	@Override
//					public int compare(File o1, File o2) {
//						 return o2.getLastUpdate().compareTo(o1.getLastUpdate());
//					}
//		        });
//				for (File file : new ArrayList<File>(files)) {
//					if(!file.getSharedUsers().contains(user) && !file.getOwner().equals(user))
//						files.remove(file);
//				}
//				
//				List<Folder> folders = new ArrayList<Folder>(folder.getFolders());
//				Collections.sort(folders, new Comparator<Folder>(){
//		           	@Override
//					public int compare(Folder o1, Folder o2) {
//						 return o2.getLastUpdate().compareTo(o1.getLastUpdate());
//					}
//		        });
//				for (Folder fold : new ArrayList<Folder>(folders)) {	
//					if(!fold.getSharedUsers().contains(user) && !fold.getOwner().equals(user))
//						folders.remove(fold);
//				}
//				modelAndView.addObject("files", files);
//				modelAndView.addObject("folders", folders);
//
//				Date last = new Date(0);
//				for (File file : user.getRoot().getFiles()) {
//					if(file.getLastUpdate().after(last))
//						last = file.getLastUpdate();
//				}
//
//				if(folder.getLastUpdate().after(last))
//					last = folder.getLastUpdate();
//
//				if( !last.equals(new Date(0)) )
//					modelAndView.addObject("lastDate", last);
//				else
//					modelAndView.addObject("lastDate", new Date());
//
//				modelAndView.setViewName("user/files");
//				return modelAndView;
//			}
//
//			modelAndView.setView(new RedirectView("/user/files"));
//			return modelAndView;
//		}catch(Exception e) {
//			log.error(e.getMessage());
//			e.printStackTrace();
//			modelAndView.setView(new RedirectView("/user/files"));
//			return modelAndView;
//		}
//	}

}

