package com.bikerental.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bikerental.backend.apirest.models.entity.Invoice;


public interface IInvoiceDao extends CrudRepository<Invoice, Long >{

}
