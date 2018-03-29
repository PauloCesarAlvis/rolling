package com.rolling.hibernate.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rolling.hibernate.dao.ItemWishDaoImp;
import com.rolling.hibernate.dao.WishDaoImp;
import com.rolling.hibernate.dao.ProductDaoImp;
import com.rolling.hibernate.model.ItemWish;
import com.rolling.hibernate.model.Wish;
import com.rolling.hibernate.model.Product;

public class VentanaVentaController {

	private ProductDaoImp pdi;
	private ItemWishDaoImp iodi;
	private WishDaoImp odi;

	public VentanaVentaController() {

	}

	/**
	 * Metodo encargado de crear la orden y sus items (productos ordenados) para
	 * poder persisitir el registro en la BD. teniendo en cuenta que esta no es la
	 * compra final del cliente.
	 * 
	 * @param products
	 * @param place
	 * @param valor
	 * @throws ParseException 
	 */
	public Long createWish(Set<ItemWish> itemsWish, String place, Long valor) throws ParseException {

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date dat = (Date) format.parse(format.format(date));
		Wish wish = new Wish(dat, valor, place);
		odi = new WishDaoImp();

		wish.setItemOrders(itemsWish);
		odi.saveWish(wish);
		for (ItemWish i : itemsWish) {
			iodi = new ItemWishDaoImp();
			i.setWish(wish);
			iodi.saveItemWish(i);
		}
		return wish.getIdWish();
	}

	/**
	 * Metodo que busca una orden (wish) almacenada y realiza 
	 * la actualización de los productos ordenados y el 
	 * valor de la misma.
	 * @param itemsWish
	 * @param id
	 * @return
	 */
	public boolean updateWish(Set<ItemWish> itemsWish, Long id, Long value) {
		
		odi = new WishDaoImp();
		Wish wish = odi.findById(id);
		if (wish != null) {
			wish.setItemOrders(itemsWish);
			wish.setPrice(value);
			odi.updateWish(wish);
			iodi = new ItemWishDaoImp();
			for (ItemWish itemWish : itemsWish) {
				if (itemWish.getIdItemOrder() == null) {
					/**
					 * Se revisa la lista de items de la orden, el que no se
					 * encuentre registrado en la BD, se guarda antes de 
					 * asignar la lista de items a la orden (wish)
					 */
					itemWish.setWish(wish);
					iodi.saveItemWish(itemWish);
				}
			}
			return true;
		}
		
		return false;
	}
	/**
	 * Metodo que busca un Wish(orden) de pedido
	 * a partir del Id almacenado a partir de la seelección 
	 * del boton correspondiente a un puesto en especifico.
	 * @param id
	 * @return
	 */
	public Wish findWishById(Long id) {
		
		odi = new WishDaoImp();
		return odi.findById(id);
	}
	/**
	 * Metodo que lista los productos disponibles en la BD.
	 * 
	 * @return
	 */
	public List<Product> loadProducts() {

		pdi = new ProductDaoImp();
		return pdi.findProducts();
	}

	/**
	 * Metodo que busca un producto por su id.
	 * 
	 * @param idProduct
	 * @return
	 */
	public Product findById(Long idProduct) {

		pdi = new ProductDaoImp();
		return pdi.findById(idProduct);
	}
}
