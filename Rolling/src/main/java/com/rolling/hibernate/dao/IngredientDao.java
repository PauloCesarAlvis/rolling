package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Ingredient;

public interface IngredientDao  {

	void saveIngredient(Ingredient ingredient);
	
	void deleteIngredient(Long idIngredient);
	
	void updateIngredient(Ingredient ingredient);
	
	List<Ingredient> findIngredients();
	
	Ingredient findById(Long idIngredient);
}
