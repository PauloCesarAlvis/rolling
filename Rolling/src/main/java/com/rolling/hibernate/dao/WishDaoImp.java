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
	}

	public void deleteWish(Long idWish) {
		
		Wish w = findById(idWish);
		if (w != null) {
			
			rollingSession.getSession().delete(w);
			rollingSession.getSession().getTransaction().commit();
		}
	}

	public void updateWish(Wish wish) {

		rollingSession.getSession().update(wish);
		rollingSession.getSession().getTransaction().commit();
	}

	public List<Wish> findWishes() {

		List<Wish> list = rollingSession.getSession().createQuery("from Order").getResultList();
		return list;
	}

	public Wish findById(Long idWish) {
		
		Wish order = rollingSession.getSession().get(Wish.class, idWish);
		return order;
	}

}
