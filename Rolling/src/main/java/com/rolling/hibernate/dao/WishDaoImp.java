package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Wish;

public class WishDaoImp extends RollingSession implements WishDao {

	RollingSession rollingSession; 
	
	public WishDaoImp() {
		
			rollingSession = new  RollingSession();
		
	}

	public void saveWish(Wish wish) {

		rollingSession.getSession().persist(wish);
		rollingSession.getSession().getTransaction().commit();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
	}

	public void deleteWish(Long idWish) {
		
		Wish w = findById(idWish);
		if (w != null) {
			
			rollingSession.getSession().delete(w);
			rollingSession.getSession().getTransaction().commit();
//			rollingSession.getSession().clear();
			rollingSession.getSession().getSessionFactory().close();
		}
	}

	public void updateWish(Wish wish) {

		rollingSession.getSession().update(wish);
		rollingSession.getSession().getTransaction().commit();
//		rollingSession.getSession().clear();
		rollingSession.getSession().getSessionFactory().close();
	}

	public List<Wish> findWishes() {

		List<Wish> list = rollingSession.getSession().createQuery("from Wish").getResultList();
		rollingSession.getSession().getSessionFactory().close();
		return list;
	}

	public Wish findById(Long idWish) {
		
		Wish order = rollingSession.getSession().get(Wish.class, idWish);
//		rollingSession.getSession().clear();
//		rollingSession.getSession().getSessionFactory().close();
		return order;
	}

}
