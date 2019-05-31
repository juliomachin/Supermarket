package com.supermercado.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermercado.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
	Set<Role> findByRole(String role);
}
