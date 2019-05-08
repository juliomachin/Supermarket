package com.supermarket.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@Transient
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "active")
	private int active = 0;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastConnect;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getLastConnect() {
		return lastConnect;
	}

	public void setLastConnect(Date lastConnect) {
		this.lastConnect = lastConnect;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastName="
				+ lastName + ", active=" + active + ", creation=" + creation + ", lastConnect=" + lastConnect
				+ ", roles=" + roles + "]";
	}
	
	public boolean isAdmin() {
		for (Role role : roles) {
			if(role.isAdmin())
				return true;
		}
		return false;
	}
	
}
