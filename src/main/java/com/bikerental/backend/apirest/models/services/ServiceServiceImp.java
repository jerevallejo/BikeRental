package com.bikerental.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bikerental.backend.apirest.models.dao.IServiceDao;
import com.bikerental.backend.apirest.models.entity.Service;

@org.springframework.stereotype.Service
public class ServiceServiceImp implements IServiceService{
	
	@Autowired
	private IServiceDao serviceDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Service> findAll() {

		return (List<Service>) serviceDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Service findById(Long id) {
		
		return serviceDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Service save(Service service) {

		return serviceDao.save(service);
	}

	@Override
	public void delete(long id) {

		serviceDao.deleteById(id);
	}

}
