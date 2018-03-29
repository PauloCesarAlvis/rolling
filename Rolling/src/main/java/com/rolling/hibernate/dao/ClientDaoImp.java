package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Client;

public class ClientDaoImp extends RollingSession implements ClientDao {

	RollingSession rollingSession; 
	
	public ClientDaoImp() {
		
		rollingSession = new  RollingSession();
		
	}

	public void saveClient(Client client) {

		rollingSession.getSession().persist(client);
		rollingSession.getSession().getTransaction().commit();
	}

	public boolean deleteClient(String idClient) {
		
		Client c = findById(idClient);
		if (c != null) {
			
			rollingSession.getSession().delete(c);
			rollingSession.getSession().getTransaction().commit();
			return true;
		}
		return false;
	}

	public void updateClient(Client client) {

		rollingSession.getSession().update(client);
		rollingSession.getSession().getTransaction().commit();
	}

	public List<Client> findClients() {

		return rollingSession.getSession().createQuery("from Client").getResultList();
	}

	public Client findById(String id) {
		
		Client c = (Client) rollingSession.getSession().createQuery("FROM Client c WHERE c.identification = :id").setParameter("id", id).uniqueResult();
		rollingSession.getSession().clear();
		return c;
	}

}
