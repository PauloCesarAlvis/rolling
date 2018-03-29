package com.rolling.hibernate.dao;

import java.util.List;

import com.rolling.hibernate.model.Client;

public interface ClientDao  {

	void saveClient(Client client);
	
	boolean deleteClient(String idClient);
	
	void updateClient(Client client);
	
	List<Client> findClients();
	
	Client findById(String idClient);
}
