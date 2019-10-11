package com.bikerental.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bikerental.backend.apirest.models.entity.Service;


public interface IServiceDao extends CrudRepository<Service, Long >{

}
