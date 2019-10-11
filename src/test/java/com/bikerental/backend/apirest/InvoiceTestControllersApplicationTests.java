package com.bikerental.backend.apirest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bikerental.backend.apirest.models.entity.Invoice;
import com.bikerental.backend.apirest.models.services.ClientServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceTestControllersApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private ClientServiceImp clientService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getInvoiceTest() throws Exception {

		mockMvc.perform(get("/api/invoices/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andReturn();
	}

	@Test
	public void getInvoiceNotFoundTest() throws Exception {

		mockMvc.perform(get("/api/invoices/{id}", 45)).andExpect(status().is4xxClientError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andReturn();

	}

	@Test
	public void saveServicesTest() throws Exception {

		Invoice invoice = new Invoice();

		invoice.setId((long) 4);

		clientService.saveInvoice(invoice);

		String request = asJsonString(invoice);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/invoices")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(201, resul);

	}

	@Test
	public void deleteServiceTest() throws Exception {

		Invoice invoice = new Invoice();

		invoice.setId((long) 4);

		clientService.saveInvoice(invoice);

		clientService.saveInvoice(invoice);

		mockMvc.perform(delete("/api/invoices/4")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andReturn();
	}

	@Test
	public void badDeleteServiceTest() throws Exception {

		mockMvc.perform(delete("/api/invoices/1")).andExpect(status().is5xxServerError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andReturn();
	}

	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
