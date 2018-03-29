package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Purchase;

public interface PurchaseDao  {

	void savePurchase(Purchase purchase);
	
	void deletePurchase(Long idPurchase);
	
	void updatePurchase(Purchase purchase);
	
	List<Purchase> findPurchases();
	
	Purchase findById(Long idPurchase);
}
