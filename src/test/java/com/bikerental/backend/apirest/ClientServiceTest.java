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
import com.bikerental.backend.apirest.models.entity.ItemInvoice;
import com.bikerental.backend.apirest.models.entity.Service;
import com.bikerental.backend.apirest.models.services.ClientServiceImp;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientServiceImp clienteService;

	@Test
	public void clientFindAllTest() {

		Collection<Client> list = clienteService.findAll();

		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - exected size ", 11, list.size());
	}

	@Test
	public void clientFindById() {
		Client client = clienteService.findById((long) 1);

		Assert.assertNotNull("failure - expected not null", client);
		Assert.assertEquals("failure - exected name ", client.getName(), "Jeremias");
		Assert.assertEquals("failure - exected surname ", client.getLastName(), "Vallejo");

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

		Client clientNew = clienteService.findById((long) 13);

		Assert.assertEquals(client.getName(), clientNew.getName());

	}

	@Test
	public void findInvoiceById() {

		Invoice invoice = clienteService.findInvoiceById((long) 1);

		Assert.assertNotNull("failure - expected not null", invoice);
		Assert.assertEquals(invoice.getClient().getId(), 1);

	}

	@SuppressWarnings("null")
	@Test
	public void saveInvoice() {

		Service service = new Service();
		service.setCreateAt(new Date());
		service.setDuration("time");
		service.setId((long) 4);
		service.setName("name");
		service.setPrice(10);

		ItemInvoice item1 = new ItemInvoice();
		item1.setId((long) 4);
		item1.setQuantity(6);
		item1.setService(service);

		Client clientNew = clienteService.findById((long) 4);
		Invoice invoice = new Invoice();
		invoice.setClient(clientNew);
		invoice.setCreateAt(new Date());

		clienteService.saveInvoice(invoice);

		Invoice invoiceNew = clienteService.findInvoiceById((long) 3);

		Assert.assertNotNull(invoiceNew);
		Assert.assertEquals(invoice.getId(), invoiceNew.getId());
	}

	@Test
	public void deleteInvoiceById() {

		clienteService.deleteInvoiceById((long) 2);

		Invoice invoice = clienteService.findInvoiceById((long) 2);

		Assert.assertNull("failure - expected null", invoice);
	}
}
