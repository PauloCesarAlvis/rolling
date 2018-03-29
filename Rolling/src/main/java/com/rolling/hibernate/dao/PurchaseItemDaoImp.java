package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.PurchaseItem;

public class PurchaseItemDaoImp extends RollingSession implements PurchaseItemDao {

	RollingSession rollingSession; 
	
	public PurchaseItemDaoImp() {
		
		rollingSession = new  RollingSession();
		
	}

	public void savePurchaseItem(PurchaseItem purchaseItem) {

		rollingSession.getSession().persist(purchaseItem);
		rollingSession.getSession().getTransaction().commit();
	}

	public void deletePurchaseItem(Long idPurchaseItem) {
		
		PurchaseItem p = findById(idPurchaseItem);
		if (p != null) {
			
			rollingSession.getSession().delete(p);
			rollingSession.getSession().getTransaction().commit();
		}
	}

	public void updatePurchaseItem(PurchaseItem purchaseItem) {

		rollingSession.getSession().update(purchaseItem);
		rollingSession.getSession().getTransaction().commit();
	}

	public List<PurchaseItem> findPurchaseItems() {

		return rollingSession.getSession().createQuery("from PurchaseItem").getResultList();
	}

	public PurchaseItem findById(Long idPurchaseItem) {
		
		return rollingSession.getSession().get(PurchaseItem.class, idPurchaseItem);
	}

}
