package com.bikerental.backend.apirest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bikerental.backend.apirest.controller.ClientRestController;
import com.bikerental.backend.apirest.controller.InvoiceRestController;
import com.bikerental.backend.apirest.controller.ServiceRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllersApplicationTests {

	@Autowired
	private ClientRestController clientController;

	@Autowired
	private InvoiceRestController invoiceController;

	@Autowired
	private ServiceRestController serviceController;

	@Test
	public void contextLoads() {

		assertThat(clientController).isNotNull();

		assertThat(invoiceController).isNotNull();

		assertThat(serviceController).isNotNull();
	}

}
