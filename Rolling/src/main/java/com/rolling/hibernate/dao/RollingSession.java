package com.rolling.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RollingSession {

	private Session session;
	
	public RollingSession() {

	     /*Con estas lineas se establece e inicia la lectura de datos para 
         * acceder a la base de datos y manipular los datos.*/
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
	}

	public Session getSession() { return session;} 
	
}
