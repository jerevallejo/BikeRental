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
import com.bikerental.backend.apirest.models.entity.Service;
import com.bikerental.backend.apirest.models.services.ServiceServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTestControllersApplicationTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	private ServiceServiceImp serviceService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void getAllServiceTest() throws Exception { 
		
			mockMvc.perform(get("/api/services"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
			.andReturn();		
	}
	
	@Test
	public void getServiceTest() throws Exception { 

			mockMvc.perform(get("/api/services/{id}", 2))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("{\"name\":\"a day\",\"duration\":\"day\",\"createAt\":\"2011-11-20\",\"id\":2,\"price\":20}",true))
			.andDo(print())
			.andReturn();		
	}
	
	@Test
	public void getServicesNotFoundTest() throws Exception { 
			
		mockMvc.perform(get("/api/services/{id}", 45))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print())
				.andReturn();		
				
	}
	
	@Test
	public void saveServicesTest() throws Exception {
		
		Service service = new Service();
		service.setName("time");
		service.setDuration("a time");
		service.setCreateAt(new Date());
		service.setPrice(12);
	
		String request = asJsonString(service);
		
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/api/services")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(201, resul);
	
	}

	@Test
	public void badUpDateServiceTest() throws Exception { 
		
		Service service = new Service();
		service.setName("time");
		service.setDuration("a time");
		service.setCreateAt(new Date());
		service.setPrice(12);
		service.setId((long) 45);
		
		serviceService.save(service);
		
		service.setName("edit");

		String request = asJsonString(service);
		
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.put("/api/services/45")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(404, resul);		
	}

	@Test
	public void upDateServiceTest() throws Exception { 
		
		Service service = new Service();
		service.setName("time");
		service.setDuration("a time");
		service.setCreateAt(new Date());
		service.setPrice(12);
		service.setId((long) 1);
		
		serviceService.save(service);
		
		service.setName("edit");

		String request = asJsonString(service);
		
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.put("/api/services/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(201, resul);		
	}  
	
	@Test
	public void deleteServiceTest() throws Exception { 
		
		Service service = new Service();
		service.setName("time");
		service.setDuration("a time");
		service.setCreateAt(new Date());
		service.setPrice(12);
		service.setId((long) 4);
		
		serviceService.save(service);
		
		mockMvc.perform(delete("/api/services/4"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
			.andReturn();		
	}
	
	@Test
	public void badDeleteServiceTest() throws Exception { 
			
		mockMvc.perform(delete("/api/services/3"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
			.andReturn();		
	}
	
	@Test
	public void badSaveServicesTest() throws Exception {
		
		Service service = null;
	
		String request = asJsonString(service);
		
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/api/services")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(request)).andReturn();
		int resul = mvcResult.getResponse().getStatus();
		assertEquals(400, resul);
	
	}
	
	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
