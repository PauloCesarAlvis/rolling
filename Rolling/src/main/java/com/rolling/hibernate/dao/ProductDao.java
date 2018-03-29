package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Product;

public interface ProductDao  {

	void saveProduct(Product product);
	
	boolean deleteProduct(Long idProduct);
	
	void updateProduct(Product product);
	
	List<Product> findProducts();
	
	Product findById(Long idProduct);
}
