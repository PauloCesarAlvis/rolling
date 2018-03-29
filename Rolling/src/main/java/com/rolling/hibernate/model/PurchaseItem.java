package com.rolling.hibernate.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="purchase_item")
public class PurchaseItem implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idpurchase_item")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPurchase;
	
	@Column(name="purchase_date")
	private Date purchase_date;

	@ManyToOne(optional = false, fetch = FetchType.EAGER) //el fecth forza a que se traigan los datos del objeto Client
	@JoinColumn(name = "purchase_idpurchase")
	private Purchase purchase;
		
	@OneToOne
	@JoinColumn(name="product_idProduct")
	private Product product;
	
//	@OneToMany(cascade = CascadeType.ALL)//en caso de borrar un teacher, que se lleve todos los socialMedia de él.
//	@JoinColumn(name ="teacher_idteacher")//esto se usa para cuando hay una table de muchos a muchos y se representa asi en ambos extremos
//	private Set<Order>teacherSocialMedia;
	
	public PurchaseItem() {
	
	}

public PurchaseItem(Date purchase_date, Purchase purchase, Product product) {
	super();
	this.purchase_date = purchase_date;
	this.purchase = purchase;
	this.product = product;
}

public Long getIdPurchase() {
	return idPurchase;
}

public void setIdPurchase(Long idPurchase) {
	this.idPurchase = idPurchase;
}

public Date getPurchase_date() {
	return purchase_date;
}

public void setPurchase_date(Date purchase_date) {
	this.purchase_date = purchase_date;
}

public Purchase getPurchase() {
	return purchase;
}

public void setPurchase(Purchase purchase) {
	this.purchase = purchase;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}
	
	
	
}
