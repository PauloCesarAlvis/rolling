package com.rolling.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rolling.hibernate.dao.ClientDaoImp;
import com.rolling.hibernate.dao.WishDaoImp;
import com.rolling.hibernate.dao.ProductDaoImp;
import com.rolling.hibernate.model.Client;
import com.rolling.hibernate.model.Wish;
import com.rolling.hibernate.model.Product;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
   
        //probando la conexion... ingresando un objeto a la BD.
        
//        Product product = new Product("Torta", Long.parseLong("30000"), Long.parseLong("40000"),Long.parseLong("2"));
//        ProductDaoImp pdi = new ProductDaoImp();
//        pdi.saveProduct(product);
//        List<Product> pros = pdi.findProducts();
//        for (Product p: pros) {
//        	System.out.println("Name :" + p.getName());
//        }
//        Date date = new Date();
//		DateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
//		System.out.println("Date : "+ format.format(date));
//System.out.println("Solo date : "+ date);
//		try {
//			Date dat = format.parse(format.format(date));
//			System.out.println("Date : "+ dat);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//        ClientDaoImp cdi = new ClientDaoImp();
//        List<Client> clients = cdi.findClients();
//        for (Client c: clients) {
//			System.out.println("Name :" + c.getName());
//		}
        
//        Teacher upd = teacherDaoImp.findById(8L);
//        if (upd != null) {
//			upd.setName("el nuevo");
//			teacherDaoImp.updateTeacher(upd);
//		}
        WishDaoImp odi = new WishDaoImp();
//        Order o = new Order(8L, "Mesa 1");
//        odi.saveOrder(o);
//        List<Wish> orders = odi.findWishes();
//        for (Wish c: orders) {
//			System.out.println("price :" + c.getPrice());
//		}
        
        
    }
}
