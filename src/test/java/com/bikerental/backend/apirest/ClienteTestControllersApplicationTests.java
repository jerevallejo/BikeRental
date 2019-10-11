package com.bikerental.backend.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

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
import com.bikerental.backend.apirest.models.entity.Client;
import com.bikerental.backend.apirest.models.services.ClientServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTestControllersApplicationTests {

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
	public void getAllClientsTest() throws Exception {

		mockMvc.perform(get("/api/clients")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andReturn();
	}

	@Test
	public void getClientTest() throws Exception {

		mockMvc.perform(get("/api/clients/{id}", 3)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().json(
						"{\"name\":\"Pedro\",\"lastName\":\"Suarez\",\"createAt\":\"2003-03-03\",\"invoices\":\"[]\",\"id\":3}",
						true))
				.andDo(print()).andReturn();

		// .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("nombre"))
	}

	@Test
	public void getClientNotFoundTest() throws Exception {

		mockMvc.perform(get("/api/clients/{id}", 45)).andExpect(status().is4xxClientError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andReturn();

	}

	@Test
	public void saveClientTest() throws Exception {

		Client client = new Client();
		client.setName("Juan");
		client.setLastName("Perez");
		client.setCreateAt(new Date());

		String request = asJsonString(client);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/clients")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(201, resul);
	}

	@Test
	public void upDateClientTest() throws Exception {
		
		Client client = new Client();
		client.setName("Carlos");
		client.setLastName("Perez");
		client.setCreateAt(new Date());
	
		clientService.save(client);
		
		client.setName("edit");
		
		String request = asJsonString(client);	
		
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.put("/api/clients/4")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(201, resul);
	
	}

	@Test
	public void badUpDateClientTest() throws Exception { 
		
		Client client = new Client();
		client.setName("Edit");
		client.setLastName("Perez");
		client.setCreateAt(new Date());
		
		String request = asJsonString(client);
		
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.put("/api/clients/100")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(404, resul);		
	}

	
	@Test
	public void deleteServiceTest() throws Exception {

		mockMvc.perform(delete("/api/clients/5")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andReturn();
	}

	@Test
	public void badDeleteServiceTest() throws Exception {

		mockMvc.perform(delete("/api/services/1")).andExpect(status().is5xxServerError())
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

	/*
	 * 
	 * List<stock> stocks = Arrays.asList(stock) *
	 * 
	 */

}
