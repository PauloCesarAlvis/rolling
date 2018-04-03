package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.ItemWish;

public interface ItemWishDao  {

	void saveItemWish(ItemWish itemWish);
	
	boolean deleteItemWish(Long idItemWish);
	
	void updateItemWish(ItemWish itemWish);
	
	List<ItemWish> findItemWishes();
	
	ItemWish findById(Long idItemWish);
}
