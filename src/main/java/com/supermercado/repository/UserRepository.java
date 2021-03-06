package com.supermercado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User getOne(Long id);
	User findByEmail(String email);
	List<User> findAllByActive(int i);
}
