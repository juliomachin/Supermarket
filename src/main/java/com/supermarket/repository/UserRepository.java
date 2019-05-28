package com.supermarket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.models.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	Optional<User> findById(long id);
	boolean existsByEmail(String email);
}

