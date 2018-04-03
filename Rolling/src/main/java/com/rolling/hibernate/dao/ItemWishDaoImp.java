package com.rolling.hibernate.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.rolling.hibernate.model.ItemWish;
import com.rolling.hibernate.model.Product;

public class ItemWishDaoImp extends RollingSession implements ItemWishDao {

	RollingSession rollingSession; 
	
	public ItemWishDaoImp() {
		
		rollingSession = new  RollingSession();
		
	}

	public void saveItemWish(ItemWish itemWish) {

		rollingSession.getSession().persist(itemWish);
		rollingSession.getSession().getTransaction().commit();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
	}

	public boolean deleteItemWish(Long idItemWish) {
		
		ItemWish i = findById(idItemWish);
		if (i != null) {
			
			rollingSession.getSession().delete(i);
			rollingSession.getSession().getTransaction().commit();
//			rollingSession.getSession().clear();
			rollingSession.getSession().getSessionFactory().close();
			return true;
		}
		return false;
	}

	public void updateItemWish(ItemWish itemWish) {

		rollingSession.getSession().update(itemWish);
		rollingSession.getSession().getTransaction().commit();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
	}

	public List<ItemWish> findItemWishes() {

		List<ItemWish> list = rollingSession.getSession().createQuery("from ItemWish").getResultList();
		rollingSession.getSession().getSessionFactory().close();
		return list;
	}

	public ItemWish findById(Long idItemOrder) {
		
		ItemWish iw = rollingSession.getSession().get(ItemWish.class, idItemOrder);
//		rollingSession.getSession().clear();
//		rollingSession.getSession().getSessionFactory().close();
		return iw;
	}

}
