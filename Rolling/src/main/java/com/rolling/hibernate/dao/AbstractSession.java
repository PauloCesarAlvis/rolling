package com.rolling.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractSession {

//	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}
	
}
