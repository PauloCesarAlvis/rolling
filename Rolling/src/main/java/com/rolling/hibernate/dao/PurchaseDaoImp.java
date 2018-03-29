package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Purchase;

public class PurchaseDaoImp extends RollingSession implements PurchaseDao {

	RollingSession rollingSession; 
	
	public PurchaseDaoImp() {
		
		rollingSession = new  RollingSession();
		
	}

	public void savePurchase(Purchase purchase) {

		rollingSession.getSession().persist(purchase);
		rollingSession.getSession().getTransaction().commit();
	}

	public void deletePurchase(Long idPurchase) {
		
		Purchase p = findById(idPurchase);
		if (p != null) {
			
			rollingSession.getSession().delete(p);
			rollingSession.getSession().getTransaction().commit();
		}
	}

	public void updatePurchase(Purchase purchase) {

		rollingSession.getSession().update(purchase);
		rollingSession.getSession().getTransaction().commit();
	}

	public List<Purchase> findPurchases() {

		return rollingSession.getSession().createQuery("from Purchase").getResultList();
	}

	public Purchase findById(Long idPurchase) {
		
		return rollingSession.getSession().get(Purchase.class, idPurchase);
	}

}
