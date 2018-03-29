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
	}

	public boolean deleteProduct(Long idProduct) {

		Product t = findById(idProduct);
		if (t != null) {

			rollingSession.getSession().delete(t);
			rollingSession.getSession().getTransaction().commit();
			return true;
		}
		return false;
	}

	public void updateProduct(Product product) {

		rollingSession.getSession().update(product);
		rollingSession.getSession().getTransaction().commit();
	}

	public List<Product> findProducts() {

		return rollingSession.getSession().createQuery("from Product").getResultList();
	}

	public Product findById(Long idProduct) {

		Product p = rollingSession.getSession().get(Product.class, idProduct);
		rollingSession.getSession().clear();
		return p;
	}

}
