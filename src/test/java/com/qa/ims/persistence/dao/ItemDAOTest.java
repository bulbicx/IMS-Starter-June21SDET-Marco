package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();
	private final OrderItemDAO orderItemDAO = new OrderItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		Item created = new Item(2L, "shoes", "description", 5.5);
		Item newItem = new Item("shoes", "description", 5.5);
		assertEquals(created, DAO.create(newItem));
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		Item newItem = new Item(1L, "shoes", "description", 5.5);
		items.add(newItem);
		assertEquals(items, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		Item newItem = new Item(1L, "shoes", "description", 5.5);
		assertEquals(newItem, DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		Item newItem = new Item(1L, "shoes", "description", 5.5);
		final long ID = 1L;
		assertEquals(newItem, DAO.read(ID));
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "shoes", "description", 5.5);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		orderItemDAO.delete(1);
		assertEquals(1, DAO.delete(1));
	}
}
