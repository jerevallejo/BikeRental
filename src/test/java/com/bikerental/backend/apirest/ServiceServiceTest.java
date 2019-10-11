package com.bikerental.backend.apirest;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bikerental.backend.apirest.models.entity.Service;
import com.bikerental.backend.apirest.models.services.ServiceServiceImp;

import org.junit.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceServiceTest {

	@Autowired
	private ServiceServiceImp serviceService;
	
	@Test 
	public void serviceFindAll() {

		Collection<Service> list = serviceService.findAll();
		
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - exected size " , 4, list.size());
	}
	
	@Test
	public void serviceFindById()
	{
		Service service = serviceService.findById((long) 1);
		
		Assert.assertNotNull(service);
		Assert.assertEquals(service.getName() , "per hour");
		Assert.assertEquals(service.getDuration() , "hour");
		Assert.assertEquals("failure - exected 5 ", (Integer) service.getPrice() ,(Integer) 5);
		
	}
	
	@Test
	public void serviceDelete() {
		
		serviceService.delete((long) 3);

		Service service = serviceService.findById((long) 3);
		
		Assert.assertNull("failure - expected null", service);
	
	}
	
	@Test
	public void serviceSave() {
		Service service = new Service();
		service.setName("name");
		service.setDuration("duration");
		service.setPrice(3);
		service.setCreateAt(new Date());
		
		serviceService.save(service);
		
		Service serviceNew = serviceService.findById((long) 4);
		
		Assert.assertEquals( service.getName(), serviceNew.getName());
	
	}
	
}
