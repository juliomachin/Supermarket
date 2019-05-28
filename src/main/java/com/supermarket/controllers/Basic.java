package com.supermarket.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.supermarket.models.User;
import com.supermarket.service.UserService;

@Controller
public class Basic {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			modelAndView.addObject("successMessage", "There is a user with the same email.");
			modelAndView.setViewName("register");
			return modelAndView;
		}else {
			userService.signup(user);
			modelAndView.addObject("successMessage", "User has been registered successfully.");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("register");
		}
		return modelAndView;
	}

	@RequestMapping(value="/access-denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}
	
	@RequestMapping(value="/error", method = RequestMethod.GET)
	public ModelAndView error() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
	}
	
	@RequestMapping(value="/user/home", method = RequestMethod.GET)
	public ModelAndView uhome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/home");
		return modelAndView;
	}

	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView ahome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/home");
		return modelAndView;
	}
	
}
