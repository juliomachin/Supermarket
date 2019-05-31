package com.supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.supermercado.model.User;
import com.supermercado.repository.RoleRepository;
import com.supermercado.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByRole("USER"));
		userRepository.save(user);
	}
	
	@Override
	public void update(User user) {
		userRepository.save(user);
	}
	
	@Override
	public User findOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAllByActive(1);
	}
	
}
