package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private ItemDAO dao;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	public void readAllTest() {
		List<Item> items = new ArrayList<>();
		
		//Test empty database
		Mockito.when(dao.readAll()).thenReturn(items);
		assertEquals(items, controller.readAll());
		
		//Test Db with data
		items.add(new Item(1L, "name", "description", 2.5));
		
		Mockito.when(dao.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		Mockito.verify(dao, Mockito.times(2)).readAll();
	}
	
	@Test
	public void readOneTest() {
		Item item = new Item(1L, "name", "description", 2.3);
		
		//Test wrong or non-existent id
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.read(1L)).thenReturn(null);
		
		assertEquals(null, controller.readOne());
		
		//Test existent id
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.read(1L)).thenReturn(item);
		
		assertEquals(item, controller.readOne());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(2)).read(1L);
		
	}

	@Test
	public void createTest() {
		String name = "airmax", description = "a description";
		double price = 2.5;
		Item item = new Item(name, description, price);
		
		Mockito.when(utils.getString()).thenReturn(name, description);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(dao.create(item)).thenReturn(item);
		
		assertEquals(item, controller.create());
		
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).create(item);
	}

	@Test
	public void updateTest() {
		Item item = new Item(1L, "name", "description", 2.3);
		Item update = new Item(1L, "aname", "adescription", 3.4);
		
		//Test a not found object based on id provided
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(dao.read(2L)).thenReturn(null);
		
		assertEquals(null, controller.update());
		
		//Test a found object
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.read(1L)).thenReturn(item);
		Mockito.when(utils.getString()).thenReturn(update.getItemName(), update.getDescription());
		Mockito.when(utils.getDouble()).thenReturn(3.4);
		Mockito.when(dao.update(update)).thenReturn(update);
		
		assertEquals(update, controller.update());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).read(2L);
		Mockito.verify(dao, Mockito.times(1)).read(1L);
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).update(update);
	}

	@Test
	public void deleteTest() {
		Item item = new Item(1L, "name", "description", 2.3);
		
		//Test a non-existent id
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(dao.read(2L)).thenReturn(null);
		
		assertEquals(0, controller.delete());
		
		//Test an existing id
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.read(1L)).thenReturn(item);
		Mockito.when(dao.delete(1L)).thenReturn(1);
		
		assertEquals(1, controller.delete());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).read(2L);
		Mockito.verify(dao, Mockito.times(1)).read(1L);
		Mockito.verify(dao, Mockito.times(1)).delete(1L);
	}
}
