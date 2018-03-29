package com.rolling.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product implements Serializable{ 
	

	@Id
	@Column(name="idProduct")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Column(name="buy_price")
	private Long buy_price;

	@Column(name="sale_price")
	private Long sale_price;
	
	@Column(name="content")
	private Long content;
	
	@Column(name="expiration")
	private Date expiration;
	
	@OneToMany(mappedBy="product")//clase padre, se pone porque no hay instancia de (FK) en product.
	private Set<Ingredient>ingredients;
	
	
	public Product() {
	
	}


	public Product(String name, String type, Long buy_price, Long sale_price, Long content, Date expiration) {
		super();
		this.name = name;
		this.type = type;
		this.buy_price = buy_price;
		this.sale_price = sale_price;
		this.content = content;
		this.expiration = expiration;
	}


	public Long getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getBuy_price() {
		return buy_price;
	}


	public void setBuy_price(Long buy_price) {
		this.buy_price = buy_price;
	}


	public Long getSale_price() {
		return sale_price;
	}


	public void setSale_price(Long sale_price) {
		this.sale_price = sale_price;
	}



	public Set<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	public Long getContent() {
		return content;
	}


	public void setContent(Long content) {
		this.content = content;
	}


	public Date getExpiration() {
		return expiration;
	}


	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
