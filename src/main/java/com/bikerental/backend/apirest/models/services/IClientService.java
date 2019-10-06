package com.bikerental.backend.apirest.models.services;

import java.util.List;

import com.bikerental.backend.apirest.models.entity.Client;
import com.bikerental.backend.apirest.models.entity.Invoice;

public interface IClientService {

	public List<Client> findAll();

	public Client findById(Long id);

	public Client save(Client client);

	public void delete(long id);

	public Invoice findInvoiceById(Long id);

	public Invoice saveInvoice(Invoice invoice);

	public void deleteInvoiceById(Long id);
}
