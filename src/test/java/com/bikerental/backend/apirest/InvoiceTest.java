package com.bikerental.backend.apirest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bikerental.backend.apirest.models.entity.Invoice;
import com.bikerental.backend.apirest.models.services.ClientServiceImp;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceTest {
	
	@Autowired
	private ClientServiceImp clienteService;

	private Invoice invoice = new Invoice();
	
	
	@Test
	public void getTotalAmountDiscount() {
	
		invoice = clienteService.findInvoiceById((long) 1);
		
		Double amount = invoice.getTotalAmount();
		
		Assert.assertEquals(invoice.getTotalAmount() , 30);
	}
	
	@Test
	public void getTotalItem() {
		

		invoice = clienteService.findInvoiceById((long) 1);
		
		int totalItems = invoice.getTotalItems();

		Assert.assertEquals(invoice.getTotalItems() , 3);
	}
	
	@Test
	public void getTotalAmount() {
	
		invoice = clienteService.findInvoiceById((long) 2);
		
		Double amount = invoice.getTotalAmount();
		
		Assert.assertEquals(invoice.getTotalAmount(), 80);
	}
}
