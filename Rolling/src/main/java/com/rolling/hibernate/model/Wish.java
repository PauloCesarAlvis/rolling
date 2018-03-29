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
@Table(name="wish")
public class Wish implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idwish")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWish;
	
	@Column(name ="date")
	private Date date;

	@Column(name ="price")
	private Long price;
	
	@Column(name ="place")
	private String place;
	
	@OneToMany(mappedBy="wish")//clase padre, se pone porque no hay instancia de (FK) en order. y el wish debe ir como nombre del atributo al otro lado.
	private Set<ItemWish>ItemWishs;
	
	
	public Wish() {
	}


	public Wish( Date date, Long wish_price, String place) {
		super();
		this.date = date;
		this.price = wish_price;
		this.place = place;
	}




	public Long getIdWish() {
		return idWish;
	}


	public void setIdWish(Long idWish) {
		this.idWish = idWish;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Long getPrice() {
		return price;
	}


	public void setPrice(Long price) {
		this.price = price;
	}

	public Set<ItemWish> getItemOrders() {
		return ItemWishs;
	}


	public void setItemOrders(Set<ItemWish> itemWishs) {
		ItemWishs = itemWishs;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}

	
	
}
