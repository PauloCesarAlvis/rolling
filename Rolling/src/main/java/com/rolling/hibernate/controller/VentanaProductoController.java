package com.rolling.hibernate.controller;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rolling.hibernate.dao.ProductDaoImp;
import com.rolling.hibernate.model.Product;

public class VentanaProductoController {

	private ProductDaoImp pdi;
	private String exceNombre = "Verifique campo nombre";
	private String exceTipo = "Verifique campo tipo";
	private String excePrecioCompra = "Verifique campo precio compra";
	private String excePrecioVenta = "Verifique campo precio venta";
	private String exceContenido = "Verifique campo contenido";
	private String exceExpir = "Verifique campo fecha expiración";

	public VentanaProductoController() {
		
	}

	/**
	 * Metodo que permite persistir un producto en la BD.
	 * @param nombre
	 * @param precioCompra
	 * @param precioVenta
	 * @param cantidad
	 * @param fechaExp
	 * @return
	 * @throws Exception
	 */
	public boolean saveProduct(String nombre, String tipo, String precioCompra, String precioVenta, String contenido,
			Date fechaExp) throws Exception {

		//Validaciones
		validarCampos(nombre, tipo, precioCompra, precioVenta, contenido, fechaExp);
		
		Long buy_price = Long.parseLong(precioCompra);
		Long sale_price = Long.parseLong(precioVenta);
		Long content = Long.parseLong(contenido);
		
		Product product = new Product(nombre, tipo, buy_price, sale_price, content, fechaExp);
		pdi = new ProductDaoImp();
		pdi.saveProduct(product);
		
		return true;
	}
	
	/**
	 * Metodo que elimina un producto de la BD.
	 * @param idProduct
	 * @return
	 */
	public boolean deleteProduct(Long idProduct) {
		
		pdi = new ProductDaoImp();
		return pdi.deleteProduct(idProduct);
	}
	
	/**
	 * Metodo que valida los atributos del producto, verificando que no esten
	 * vacios o no haya una letra donde solo van numeros y viceversa.
	 * @param nombre
	 * @param tipo
	 * @param precioCompra
	 * @param precioVenta
	 * @param contenido
	 * @param fechaExp
	 * @throws Exception
	 */
	private void validarCampos(String nombre, String tipo, String precioCompra, String precioVenta, String contenido,
			Date fechaExp) throws Exception {
		
		if (nombre.isEmpty() || nombre.equals(null)) throw new Exception(exceNombre);
		if (tipo.isEmpty() || tipo.equals(null)) throw new Exception(exceTipo);
		if (precioCompra.isEmpty() || precioCompra.equals(null) || !isNumeric(precioCompra)) throw new Exception(excePrecioCompra);
		if (precioVenta.isEmpty() || precioVenta.equals(null) || !isNumeric(precioVenta)) throw new Exception(excePrecioVenta);
		if (contenido.isEmpty() || contenido.equals(null) || !isNumeric(contenido)) throw new Exception(exceContenido);
		if (fechaExp == null || fechaExp.equals(null)) throw new Exception(exceExpir);
	}
	/**
	 * Metodo que busca todos los productos en la BD.
	 * @return
	 */
	public List<Product> loadProducts(){
		
		pdi = new ProductDaoImp();
		return pdi.findProducts();
	}
	
	/**
	 * Metodo que busca un producto a partir del ID del mismo.
	 * @param idProduct
	 * @return
	 */
	public Product findById(Long idProduct) {
		pdi = new ProductDaoImp();
		return pdi.findById(idProduct);
	}
	
	/**
	 * Metodo que busca el producto en la BD y actualiza todos sus campos, tomando
	 * los que se encuentran en la interfaz de usuario, se hayan modificado o no.
	 * @param nombre
	 * @param tipo
	 * @param precioCompra
	 * @param precioVenta
	 * @param contenido
	 * @param fechaEx
	 * @param idProduct
	 * @return
	 * @throws Exception
	 */
	public boolean updateProduct(String nombre, String tipo, String precioCompra, String precioVenta, String contenido,
			Date fechaEx, Long idProduct) throws Exception {
		
		//validaciones
		validarCampos(nombre, tipo, precioCompra, precioVenta, contenido, fechaEx);
		
		Long buy_price = Long.parseLong(precioCompra);
		Long sale_price = Long.parseLong(precioVenta);
		Long content = Long.parseLong(contenido);
		
		Product p = findById(idProduct);
		if (p != null) {
			
			p.setBuy_price(buy_price);
			p.setContent(content);
			p.setExpiration(fechaEx);
			p.setName(nombre);
			p.setType(tipo);
			p.setSale_price(sale_price);
			p.setIngredients(p.getIngredients());
			pdi = new ProductDaoImp();
			pdi.updateProduct(p);
		}else
			throw new Exception("No se encontró el producto.");
		
		return true;
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

	public ProductDaoImp getPdi() {
		return pdi;
	}
	
	
}
