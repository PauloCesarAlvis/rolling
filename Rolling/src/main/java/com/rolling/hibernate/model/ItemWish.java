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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="item_wish")
public class ItemWish implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="iditem_wish")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItemOrder;
	
	@Column(name="seller")
	private String seller;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="value")
	private Long value;
	
	//es un campo opcional para poder crear el item sin la orden
	@ManyToOne(optional = false, fetch = FetchType.EAGER) //el fecth forza a que se traigan los datos del objeto Order
	@JoinColumn(name = "wish_idwish")
	private Wish wish; //este campo debe ser llamado igual a la propiedad mappedBy en Order para itemorders.
	
	@OneToOne
	@JoinColumn(name="product_idProduct")
	private Product product;
	
	public ItemWish() {
	
	}


	public ItemWish(String seller, Long quantity, Long value, Product product) {
		super();
		this.seller = seller;
		this.quantity = quantity;
		this.value = value;
		this.product = product;
	}


	public Long getIdItemOrder() {
		return idItemOrder;
	}

	public void setIdItemOrder(Long idItemOrder) {
		this.idItemOrder = idItemOrder;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}


	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Long getValue() {
		return value;
	}


	public void setValue(Long value) {
		this.value = value;
	}

	
	
	
	

}
