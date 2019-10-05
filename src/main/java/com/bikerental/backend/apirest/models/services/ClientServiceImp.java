package com.bikerental.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bikerental.backend.apirest.models.dao.IClientDao;
import com.bikerental.backend.apirest.models.entity.Client;

@Service
public class ClientServiceImp implements IClientService{

	@Autowired
	private IClientDao clientDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {

		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) 
	{
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Client save(Client Client)
	{
		return clientDao.save(Client);
	}

	@Override
	@Transactional
	public void delete(long id) 
	{
		clientDao.deleteById(id);
	}
}
