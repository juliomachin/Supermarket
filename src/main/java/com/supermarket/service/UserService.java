package com.supermarket.service;

import com.supermarket.models.User;

public interface  UserService {
	public User findUserByEmail(String email);
	public boolean existsByEmail(String email);
	public void signup(User user);
	public User findById(long id);
	public void save(User user);
}
