package com.bikerental.backend.apirest.models.services;

import java.util.List;

import com.bikerental.backend.apirest.models.entity.Service;

public interface IServiceService {

	public List<Service> findAll();

	public Service findById(Long id);
	
	public Service save(Service service);
	
	public void delete(long id);
}
