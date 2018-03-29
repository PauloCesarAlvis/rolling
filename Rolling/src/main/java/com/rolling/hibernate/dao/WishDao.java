package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Wish;

public interface WishDao  {

	void saveWish(Wish wish);
	
	void deleteWish(Long idWish);
	
	void updateWish(Wish wish);
	
	List<Wish> findWishes();
	
	Wish findById(Long idWish);
}
