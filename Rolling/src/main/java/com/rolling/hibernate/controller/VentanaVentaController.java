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
import com.rolling.hibernate.gui.VentanaVenta;
import com.rolling.hibernate.dao.ProductDaoImp;
import com.rolling.hibernate.model.ItemWish;
import com.rolling.hibernate.model.Wish;
import com.rolling.hibernate.model.Product;

public class VentanaVentaController {

	private ProductDaoImp pdi;
	private ItemWishDaoImp iwdi;
	private WishDaoImp wdi;

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
		wdi = new WishDaoImp();

		wish.setItemOrders(itemsWish);
		wdi.saveWish(wish);
		wdi.getSession().getSessionFactory().close();
		for (ItemWish i : itemsWish) {
			iwdi = new ItemWishDaoImp();
			i.setWish(wish);
			iwdi.saveItemWish(i);
			iwdi.getSession().getSessionFactory().close();
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
		
		wdi = new WishDaoImp();
		Wish wish = wdi.findById(id);
		if (wish != null) {
			wish.setItemOrders(itemsWish);
			wish.setPrice(value);
			wdi.updateWish(wish);
			wdi.getSession().getSessionFactory().close();
			iwdi = new ItemWishDaoImp();
			for (ItemWish itemWish : itemsWish) {
				if (itemWish.getIdItemOrder() == null) {
					/**
					 * Se revisa la lista de items de la orden, el que no se
					 * encuentre registrado en la BD, se guarda antes de 
					 * asignar la lista de items a la orden (wish)
					 */
					itemWish.setWish(wish);
					iwdi.saveItemWish(itemWish);
					iwdi.getSession().getSessionFactory().close();
				}
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo que retira una orden del pedido.
	 * En caso de existir un solo producto en la lista
	 * y sea este el que se pretende retirar, se debe eliminar
	 * tambien la persistencia de la orden (Wish) pues no tiene
	 * asosiado ningun producto (ItemWish)
	 * @param idItem
	 * @param compra
	 * @param idWish
	 * @return
	 */
	public boolean deleteItemWish(Long idItem, Set<ItemWish> compra, Long idWish) {
		iwdi = new ItemWishDaoImp();
		boolean r = iwdi.deleteItemWish(idItem);
		if (compra.size() == 0) {
			wdi = new WishDaoImp();
			wdi.deleteWish(idWish);
		}
		return r;
	}
	/**
	 * Metodo que busca un Wish(orden) de pedido
	 * a partir del Id almacenado a partir de la seelección 
	 * del boton correspondiente a un puesto en especifico.
	 * @param id
	 * @return
	 */
	public Wish findWishById(Long id) {
		
		wdi = new WishDaoImp();
		Wish wish = wdi.findById(id);
		wdi.getSession().getSessionFactory().close();
		return wish;
	}
	/**
	 * Metodo que lista los productos disponibles en la BD.
	 * 
	 * @return
	 */
	public List<Product> loadProducts() {

		pdi = new ProductDaoImp();
		List<Product> list = pdi.findProducts();
		pdi.getSession().getSessionFactory().close();
		return list;
	}

	/**
	 * Metodo que busca un producto por su id.
	 * 
	 * @param idProduct
	 * @return
	 */
	public Product findById(Long idProduct) {

		pdi = new ProductDaoImp();
		Product p = pdi.findById(idProduct);
		pdi.getSession().getSessionFactory().close();
		return p;
	}
}
