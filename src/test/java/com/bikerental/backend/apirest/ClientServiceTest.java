package com.bikerental.backend.apirest;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bikerental.backend.apirest.models.entity.Client;
import com.bikerental.backend.apirest.models.entity.Invoice;
import com.bikerental.backend.apirest.models.services.ClientServiceImp;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientServiceImp clienteService;
	
	@Test 
	public void clientFindAll() {

		Collection<Client> list = clienteService.findAll();
		
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - exected size " , 10, list.size());
	}
	
	@Test
	public void clientFindById()
	{
		Client client = clienteService.findById((long) 1);
		
		Assert.assertNotNull("failure - expected not null", client);
		Assert.assertEquals("failure - exected name " , client.getName() , "Jeremias");
		Assert.assertEquals("failure - exected surname " , client.getLastName() , "Vallejo");
		
	}
	
	@Test
	public void clientDelete() {
		
		clienteService.delete((long) 3);

		Client client = clienteService.findById((long) 3);
		
		Assert.assertNull("failure - expected null", client);
	
	}
	
	@Test
	public void clienteSave() {
		Client client = new Client();
		client.setName("name");
		client.setLastName("surname");
		client.setCreateAt(new Date());
		
		clienteService.save(client);
		
		Client clientNew = clienteService.findById((long) 12);
		
		Assert.assertEquals( client.getName(), clientNew.getName());
	
	}
	
	@Test
	public void findInvoiceById() {
		
		Invoice invoice = clienteService.findInvoiceById((long) 1);
		
		Assert.assertNotNull("failure - expected not null", invoice);
		Assert.assertEquals(invoice.getClient().getId(), 1);		
		Assert.assertEquals(invoice.getTotalAmount() , 30);
		Assert.assertEquals(invoice.getTotalItems() , 3);
		
		
	}

	@Test
	public void saveInvoice() {
		

		Client clientNew = clienteService.findById((long) 12);
		Invoice invoice = new Invoice();
		invoice.setClient(clientNew);
		invoice.setCreateAt(new Date());
		
		clienteService.saveInvoice(invoice);
		
		Invoice invoiceNew = clienteService.findInvoiceById((long)3);
		
		Assert.assertNotNull(invoiceNew);
		Assert.assertEquals( invoice.getId(), invoiceNew.getId());
		
	}
	
	@Test 
	public void deleteInvoiceById() {
		
		clienteService.deleteInvoiceById((long)2);

		Invoice invoice = clienteService.findInvoiceById((long) 2);
		
		Assert.assertNull("failure - expected null", invoice);
	}
}
