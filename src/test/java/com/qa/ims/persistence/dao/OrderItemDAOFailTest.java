package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAOFailTest {
	
	private final OrderItemDAO DAO = new OrderItemDAO();

	@Before
	public void setup() {
		DBUtils.connect("fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		OrderItem orderItem = new OrderItem(1L, 1L, 1, 5.5);
		assertNull(DAO.create(orderItem));
	}
	
	@Test
	public void testReadAll() {
		assertEquals(new ArrayList<>(), DAO.readAll());
	}
	
	@Test
	public void testRead() {
		assertNull(DAO.read(1L));
	}
	
	@Test
	public void testReadOrderItems() {
		assertEquals(new ArrayList<>(),DAO.readOrderItems(1L));
	}
	
	@Test
	public void testReadOrderItem() {
		assertNull(DAO.readOrderItem(1L, 1L));
	}
	
	@Test 
	public void testUpdate() {
		OrderItem updated = new OrderItem(1L, 1L, 1L, 2, 5.5);
		assertNull(DAO.update(updated));
	}
	
	@Test
	public void testUpdateQty() {
		OrderItem updated = new OrderItem(1L, 1L, 1L, 2, 5.5);
		assertNull(DAO.updateQty(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
	
	@Test
	public void testDeleteOrderItems() {
		assertEquals(0, DAO.deleteOrderItems(1L));
	}
}
