package com.rolling.hibernate.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import com.rolling.hibernate.dao.ClientDaoImp;
import com.rolling.hibernate.dao.PurchaseDaoImp;
import com.rolling.hibernate.dao.PurchaseItemDaoImp;
import com.rolling.hibernate.model.Client;
import com.rolling.hibernate.model.ItemWish;
import com.rolling.hibernate.model.Purchase;
import com.rolling.hibernate.model.PurchaseItem;

public class VentanaCuentaCobroController {

	private PurchaseDaoImp pdi;
	private PurchaseItemDaoImp pidi;
	private ClientDaoImp cdi;
	
	/**
	 * Metodo que persiste la compra junto con los productos que 
	 * se compraron, que son equivalentes a la lista Wish creada 
	 * al momento de hacer la orden.
	 * @param itemsWish
	 * @param value
	 * @param idClient
	 * @throws ParseException
	 */
	public void createPurchase(Set<ItemWish> itemsWish, Long value, String idClient) throws ParseException {

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date dat = (Date) format.parse(format.format(date));
		Purchase purchase = new Purchase(dat, value);
		pdi = new PurchaseDaoImp();
		cdi = new ClientDaoImp();
		Client client = cdi.findById(idClient);
		if (client != null) purchase.setClient(client);
		else purchase.setClient(null);
		pdi.savePurchase(purchase);
		Set<PurchaseItem> purchaseItems = new HashSet<PurchaseItem>();
		for (ItemWish i : itemsWish) {
			PurchaseItem pi = new PurchaseItem(dat, purchase, i.getProduct());
			pidi = new PurchaseItemDaoImp();
			pidi.savePurchaseItem(pi);
		}
		purchase.setPurchasesItems(purchaseItems);
	}
	
	/**
	 * Metodo que recibe el pago del cliente y entrega 
	 * la cantidad de cambio que se le debe dar al mismo.
	 * @param pago
	 * @param total
	 * @return
	 */
	public String calculateChange(String pago, String total) {
		
		long pay = (long) Integer.parseInt(pago);
		long value = (long) Integer.parseInt(total);
		if(value > pay) return "ERROR";
		long change = pay - value;
		
		return String.valueOf(change);		
	}
	/**
	 * Metodo que calcula el total de la compra realizada por 
	 * el cliente.
	 * @param productos
	 * @return
	 */
	public String calculatePayment(Set<ItemWish>productos) {
		
		long total = 0;
		for (ItemWish itemWish : productos) {
			total += itemWish.getValue();
		}
		
		return String.valueOf(total);
	}
	/**
	 * Metodo que busca un cliente por su numero
	 * de identificación.
	 * @param idClient
	 * @return
	 */
	public String findClient(String idClient) {
		cdi = new ClientDaoImp();
		Client c = cdi.findById(idClient);
		String client = "No Registra";
		return c == null ? client : c.getName()+" "+c.getLastname();
	}
	/**
	 * Metodo que entrega la hora exacta del sistema.
	 * @return
	 */
	public static String getTime() {
		
		Calendar calendar = new GregorianCalendar();
		Date horaActual = new Date();
		calendar.setTime(horaActual);
		String hour, minute, second;
		String ampm = calendar.get(Calendar.AM_PM) == Calendar.AM?"a.m.":"p.m.";
		if (ampm.equals("p.m.")) {
			int h = calendar.get(Calendar.HOUR_OF_DAY) == 12?calendar.get(Calendar.HOUR_OF_DAY):calendar.get(Calendar.HOUR_OF_DAY)-12;
			hour = (h>9?"":"0")+h;
		}else {
			hour = (calendar.get(Calendar.HOUR_OF_DAY)>9?"":"0")+calendar.get(Calendar.HOUR_OF_DAY);
		}
			
		minute = (calendar.get(Calendar.MINUTE)>9?"":"0")+calendar.get(Calendar.MINUTE);
		second = (calendar.get(Calendar.SECOND)>9?"":"0")+calendar.get(Calendar.SECOND);
		
		String time = hour+":"+minute+":"+second+" "+ampm;
		return time;
	}
	
	/**
	 * Metodo que entrega la fecha actual del sistema.
	 * @return
	 */
	public String getDate() {
		
		Calendar calendar = new GregorianCalendar();
		String	day = Integer.toString(calendar.get(Calendar.DATE));
		int m = calendar.get(Calendar.MONTH)+1;
		String month = (m>9?"":"0")+m;
		String year = Integer.toString(calendar.get(Calendar.YEAR));
		
		String date = day+" / "+month+" / "+year;
		return date;
	}
}
