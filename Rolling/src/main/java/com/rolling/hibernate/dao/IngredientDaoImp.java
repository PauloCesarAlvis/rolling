package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Ingredient;

public class IngredientDaoImp extends RollingSession implements IngredientDao {

	RollingSession rollingSession; 
	
	public IngredientDaoImp() {
		
		rollingSession = new  RollingSession();
		
	}

	public void saveIngredient(Ingredient ingredient) {

		rollingSession.getSession().persist(ingredient);
		rollingSession.getSession().getTransaction().commit();
	}

	public void deleteIngredient(Long idIngredient) {
		
		Ingredient i = findById(idIngredient);
		if (i != null) {
			
			rollingSession.getSession().delete(i);
			rollingSession.getSession().getTransaction().commit();
		}
	}

	public void updateIngredient(Ingredient ingredient) {

		rollingSession.getSession().update(ingredient);
		rollingSession.getSession().getTransaction().commit();
	}

	public List<Ingredient> findIngredients() {

		return rollingSession.getSession().createQuery("from Ingredient").getResultList();
	}

	public Ingredient findById(Long idIngredient) {
		
		return rollingSession.getSession().get(Ingredient.class, idIngredient);
	}

}
