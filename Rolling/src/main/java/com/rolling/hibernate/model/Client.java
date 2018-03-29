package com.rolling.hibernate.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idClient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="id")
	private String identification;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy="client")//clase padre, se pone porque no hay instancia de (FK) en client.
	private Set<Purchase>purchases;
	
	//es un campo opcional para poder crear el curso sin el profesor
//	@ManyToOne(optional = true, fetch = FetchType.EAGER) //el fecth forza a que se traigan los datos del objeto teacher
//	@JoinColumn(name = "teacher_idteacher")
//	private PurchaseItem teacher;

	public Client() {
	
	}

	public Client(String name, String lastname, String cedula, String telephone, String email) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.identification = cedula;
		this.telephone = telephone;
		this.email = email;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	
		
	
}
