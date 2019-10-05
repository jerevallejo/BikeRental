package com.bikerental.backend.apirest.models.services;

import java.util.List;

import com.bikerental.backend.apirest.models.entity.Client;



public interface IClientService {

	public List<Client> findAll();

	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(long id);
}
