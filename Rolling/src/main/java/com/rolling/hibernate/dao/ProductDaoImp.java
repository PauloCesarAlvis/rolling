package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Product;

public class ProductDaoImp extends RollingSession implements ProductDao {

	RollingSession rollingSession;

	public ProductDaoImp() {

			
			rollingSession = new RollingSession();

	}

	public void saveProduct(Product product) {

		rollingSession.getSession().persist(product);
		rollingSession.getSession().getTransaction().commit();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
	}

	public boolean deleteProduct(Long idProduct) {

		Product t = findById(idProduct);
		if (t != null) {

			rollingSession.getSession().delete(t);
			rollingSession.getSession().getTransaction().commit();
//			rollingSession.getSession().clear();
			rollingSession.getSession().getSessionFactory().close();
			return true;
		}
		return false;
	}

	public void updateProduct(Product product) {

		rollingSession.getSession().update(product);
		rollingSession.getSession().getTransaction().commit();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
	}

	public List<Product> findProducts() {
		
		List<Product> products = rollingSession.getSession().createQuery("from Product").getResultList();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
		return products;
	}

	public Product findById(Long idProduct) {

		Product p = rollingSession.getSession().get(Product.class, idProduct);
//		rollingSession.getSession().clear();
//		rollingSession.getSession().getSessionFactory().close();
		return p;
	}

}
