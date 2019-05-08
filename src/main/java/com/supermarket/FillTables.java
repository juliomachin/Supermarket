package com.supermarket;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.supermarket.models.Role;
import com.supermarket.repository.RoleRepository;

@SuppressWarnings("unused")
@Component
public class FillTables 
implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		insertRoles();
		return;
	}

	private void insertRoles() {
		if(roleRepository.findAll().isEmpty()) {
			Role role = new Role();
			role.setRole("ROLE_USER");
			roleRepository.save(role);
			role = new Role();
			role.setRole("ROLE_ADMIN");
			roleRepository.save(role);
		}
	}

}



