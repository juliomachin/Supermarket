package com.supermercado.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	private int active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Producto> productos;
	
	@OneToOne
	private User user;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation = new Date();

	public Pedido() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}
	
}
