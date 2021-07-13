package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private CustomerDAO dao;

	@InjectMocks
	private CustomerController controller;

	@Test
	public void testCreate() {
		final String F_NAME = "barry", L_NAME = "scott";
		final Customer created = new Customer(F_NAME, L_NAME);

		Mockito.when(utils.getString()).thenReturn(F_NAME, L_NAME);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));

		Mockito.when(dao.readAll()).thenReturn(customers);

		assertEquals(customers, controller.readAll());
		
		//customers database empty
		List<Customer> noCustomers = new ArrayList<>();
		
		Mockito.when(dao.readAll()).thenReturn(noCustomers);
		assertEquals(noCustomers, controller.readAll());

		Mockito.verify(dao, Mockito.times(2)).readAll();
	}
	
	@Test
	public void readOne() {
		Customer customer = new Customer(1L, "marie", "pop");
		Customer customer2 = null;
		Long ID = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.read(ID)).thenReturn(customer);
		
		assertEquals(customer, controller.readOne());
		
		//wrong or non existent ID
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.read(ID)).thenReturn(null);
		
		assertEquals(customer2, controller.readOne());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(2)).read(ID);
	}

	@Test
	public void testUpdate() {
		Customer updated = new Customer(1L, "chris", "perrins");

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(dao.read(1L)).thenReturn(updated);
		
		Mockito.when(this.utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());
		
		//Id wrong or non existent
		Mockito.when(this.utils.getLong()).thenReturn(2L);
		Mockito.when(dao.read(2L)).thenReturn(null);

		assertEquals(null, this.controller.update());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).read(1L);
		Mockito.verify(this.dao, Mockito.times(1)).read(2L);
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		Customer customer = new Customer(1L, "marie", "pop");
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.read(ID)).thenReturn(customer);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());
		
		//id wrong or non existent
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(dao.read(2L)).thenReturn(null);
		
		assertEquals(0, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).read(ID);
		Mockito.verify(dao, Mockito.times(1)).read(2L);
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
		
	}

}
