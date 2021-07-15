package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOFailTest {

	private final ItemDAO DAO = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect("fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		Item newItem = new Item("shoes", "description", 5.5);
		assertNull(DAO.create(newItem));
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		assertEquals(items, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertNull(DAO.read(ID));
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "shoes", "description", 5.5);
		assertNull(DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
}
