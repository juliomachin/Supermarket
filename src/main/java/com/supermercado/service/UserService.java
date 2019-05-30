package com.supermercado.service;

import java.util.List;

import com.supermercado.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void save(User user);
	public void update(User user);
	public User findOne(Long id);
	public List<User> findAll();
}
