package com.rolling.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ingredient")
public class Ingredient implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idingredient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIngredient;
	
	@Column(name="name")
	private String name;
	
	@Column(name="quantity")
	private Long quantity;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER) //el fecth forza a que se traigan los datos del objeto Client
	@JoinColumn(name = "product_idProduct")
	private Product product;

	public Ingredient() {
	
	}

	public Ingredient(Long idIngredient, String name, Long quantity) {
		super();
		this.idIngredient = idIngredient;
		this.name = name;
		this.quantity = quantity;
	}

	public Long getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(Long idIngredient) {
		this.idIngredient = idIngredient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
