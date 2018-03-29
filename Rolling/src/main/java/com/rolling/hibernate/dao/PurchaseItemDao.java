package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.PurchaseItem;

public interface PurchaseItemDao  {

	void savePurchaseItem(PurchaseItem purchaseItem);
	
	void deletePurchaseItem(Long idPurchaseItem);
	
	void updatePurchaseItem(PurchaseItem purchaseItem);
	
	List<PurchaseItem> findPurchaseItems();
	
	PurchaseItem findById(Long idPurchaseItem);
}
