package com.rolling.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idpurchase")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPurchase;
	
	@Column(name="purchase_date")
	private Date purchase_date;
	
	@Column(name="purchase_value")
	private Long purchase_value;
	
	//es un campo opcional para poder crear el curso sin el profesor
	@ManyToOne(optional = true, fetch = FetchType.EAGER) //el fecth forza a que se traigan los datos del objeto Client
	@JoinColumn(name = "client_idClient")
	private Client client;
	
	@OneToMany(mappedBy="purchase")//clase padre, se pone porque no hay instancia de (FK) en purchase.
	private Set<PurchaseItem>purchasesItems;
	
	
	public Purchase() {
		
	}

	public Purchase( Date purchase_date, Long purchase_value) {
		super();
		this.purchase_date = purchase_date;
		this.purchase_value = purchase_value;
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

	public Long getPurchase_value() {
		return purchase_value;
	}

	public void setPurchase_value(Long purchase_value) {
		this.purchase_value = purchase_value;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<PurchaseItem> getPurchasesItems() {
		return purchasesItems;
	}

	public void setPurchasesItems(Set<PurchaseItem> purchasesItems) {
		this.purchasesItems = purchasesItems;
	}
	
	
	
	

}
