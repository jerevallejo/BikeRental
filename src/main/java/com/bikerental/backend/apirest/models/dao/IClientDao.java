package com.bikerental.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bikerental.backend.apirest.models.entity.Client;


public interface IClientDao extends CrudRepository<Client, Long >{

}
