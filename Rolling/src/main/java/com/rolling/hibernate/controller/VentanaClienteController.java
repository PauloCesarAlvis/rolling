package com.rolling.hibernate.controller;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;

import com.rolling.hibernate.dao.ClientDaoImp;
import com.rolling.hibernate.dao.ProductDaoImp;
import com.rolling.hibernate.model.Client;
import com.rolling.hibernate.model.Product;

public class VentanaClienteController {

	private ClientDaoImp cdi;
	private String exceNombre = "Verifique campo nombre";
	private String exceApellidos = "Verifique campo apellidos";
	private String exceCedula = "Verifique campo cedula";
	private String exceTelefono = "Verifique campo telefono";
	private String exceCorreo = "Verifique campo correo";
	private String existeCliente = "Ya se encuentra registrado un cliente con esa cédula.";
	
	
	public VentanaClienteController() {

	}
	
	/**
	 * Metodo encargado de recibir parametros del cliente y validar que esten correctos, 
	 * si es asi procede a invocar el metodo para almacenarlo en la BD.
	 * @param nombre
	 * @param apellidos
	 * @param cedula
	 * @param telefono
	 * @param correo
	 * @return
	 * @throws Exception
	 */
	public boolean saveClient(String nombre, String apellidos, String cedula, String telefono, String correo) throws Exception {
		
		valirCampos(nombre, apellidos, cedula, telefono, correo);
		if (findById(cedula) != null) throw new Exception(existeCliente);
		
		Client client = new Client(nombre, apellidos, cedula, telefono, correo);
		cdi = new ClientDaoImp();
		cdi.saveClient(client);;
		return true;
	}
	
	/**
	 * Metodo que permite actualizar los datos de un cliente almacenado previamente
	 * en la BD.
	 * @param nombre
	 * @param apellidos
	 * @param cedula
	 * @param telefono
	 * @param correo
	 * @return
	 * @throws Exception
	 */
	public boolean updateClient(String nombre, String apellidos, String cedula, String telefono, String correo) throws Exception {
		
		valirCampos(nombre, apellidos, cedula, telefono, correo);
		Client c = findById(cedula);
		if (c != null) {
			
			c.setEmail(correo);
			c.setLastname(apellidos);
			c.setName(nombre);
			c.setTelephone(telefono);
			
			cdi = new ClientDaoImp();
			cdi.updateClient(c);
		}else
			throw new Exception("No se encontró el cliente.");
		
		return true;
		
	}
	
	/**
	 * Metodo que elimina un cliente de la BD.
	 * @param idClient
	 * @return
	 */
	public boolean deleteClient(String idClient) {
		
		cdi = new ClientDaoImp();
		return cdi.deleteClient(idClient);
	}
	/**
	 * Metodo que busca un cliente por su numero de cedula.
	 * @param id : cedula del cliente. No es el id interno de la BD.
	 * @return
	 */
	public Client findById(String id) {
		cdi = new ClientDaoImp();
		return cdi.findById(id);
	}

	/**
	 * Metodo que busca todos los clientes en la BD.
	 * @return
	 */
	public List<Client> loadClients(){
		
		cdi = new ClientDaoImp();
		return cdi.findClients();
	}
	
	/**
	 * Metodo que verifica los atributos del cliente, para que 
	 * pasen vacios o con datos erroneos.
	 * @param nombre
	 * @param apellidos
	 * @param cedula
	 * @param telefono
	 * @param correo
	 * @throws Exception
	 */
	private void valirCampos (String nombre, String apellidos, String cedula, String telefono, String correo)throws Exception {
		
			
			//Validaciones
			if (nombre.isEmpty() || nombre.equals(null)) throw new Exception(exceNombre);
			if (apellidos.isEmpty() || apellidos.equals(null)) throw new Exception(exceApellidos);
			if (cedula.isEmpty() || cedula.equals(null) || !isNumeric(cedula)) throw new Exception(exceCedula);
			if (telefono.isEmpty() || telefono.equals(null) || !isNumeric(telefono)) throw new Exception(exceTelefono);
			if (correo.isEmpty() || correo.equals(null)) throw new Exception(exceCorreo);
	}
	/**
	 * Metodo que verifica si un dato es numerico.
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str)
	{
	    NumberFormat formatter = NumberFormat.getInstance();
	    
	    ParsePosition pos = new ParsePosition(0);
	    formatter.parse(str, pos);
	    return str.length() == pos.getIndex();
	}
	
	public ClientDaoImp getCdi() {
		return cdi;
	}


	
	
}
