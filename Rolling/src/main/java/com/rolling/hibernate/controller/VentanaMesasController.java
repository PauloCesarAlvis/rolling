package com.rolling.hibernate.controller;

import java.util.ArrayList;
import java.util.List;

public class VentanaMesasController {

	private List<Puesto> puestos;

	public VentanaMesasController() {

		crearPuestos();
	}

	/**
	 * Metodo que permite ocupar las mesas y la barra
	 * o informa que dicho puesto se encuentra
	 * ocupado.
	 * 
	 * @param place
	 * @return
	 */
	public boolean takePlace(String place) {

		boolean puedeOcupar = false;
		for (Puesto p : puestos) {
			if (p.getName().equals(place)) {
				if (!p.isOcupado()) {
					p.setOcupado(true);
					puedeOcupar = true;
				}
			}
		}

		return puedeOcupar;
	}

	/**
	 * Metodo que permite desocupar un puesto cuando se realiza el pago.
	 * Basta con cambiar su estado de ocupado a falso y retirar el id
	 * de la orden asiganada a ese puesto.
	 * @param place
	 * @return
	 */
	public boolean quitPlace(String place) {
		
		boolean desocupo = false;
		for (Puesto p : puestos) {
			if (p.getName().equals(place)) {
				if (p.isOcupado()) {
					p.setIdWish(0L); //se retira el Id para orden de ese puesto pues ya se pagó la cuenta
					p.setOcupado(false);
					desocupo = true; //se logró desocupar sin problemas.
					break;
				}
			}
		}
		
		return desocupo;
	}
	/**
	 * Metodo que busca un puesto este ocupado o no. 
	 * con el fin de asignarle el id de la orden que 
	 * ha realizado en caso que sea necesario modificarla.
	 * @param name
	 * @return
	 */
	public Puesto findPlace(String name) {
		
		Puesto p = null;
		for (Puesto puesto : puestos) {
			if (puesto.getName().equals(name)) {
				p = puesto;
			}
		}
		return p;
	}
	
	/**
	 * Metodo que inicia mesas y barra. Con su
	 * estado ocupado en false.
	 */
	private void crearPuestos() {
		
		puestos = new ArrayList<Puesto>();
		crearMesas();
		crearBarra();
	}
	/**
	 * Metodo que crea las mesas al iniciar el programa, solo se debe ejecutar una
	 * vez, nombrando las mesas y seteando por defecto el estado de desocupadas
	 * (false)
	 */
	private void crearMesas() {

//		puestos = new ArrayList<Puesto>();
		for (int i = 0; i < 8; i++) {
			Puesto mesa = new Puesto("Mesa " + (i + 1));
			puestos.add(mesa);
		}
	}

	/**
	 * Metodo que crea la barra al iniciar el programa, solo se debe ejecutar una
	 * vez, nombrando los puestos de la barra y seteando por defecto el estado de desocupadas
	 * (false)
	 */
	private void crearBarra() {

		for (int i = 0; i < 6; i++) {
			Puesto barra = new Puesto("Barra " + (i + 1));
			puestos.add(barra);
		}
	}
	/**
	 * Clase que permite crear una mesa o un puesto en barra para ser ocupado por
	 * clientes.
	 * 
	 * @author Paulo Cesar
	 *
	 */
	public class Puesto {

		private String name;
		private boolean ocupado;
		private Long idWish;

		public Puesto(String name) {
			this.name = name;
			ocupado = false;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isOcupado() {
			return ocupado;
		}

		public void setOcupado(boolean ocupado) {
			this.ocupado = ocupado;
		}

		public Long getIdWish() {
			return idWish;
		}

		public void setIdWish(Long idWish) {
			this.idWish = idWish;
		}
		
		

	}


}