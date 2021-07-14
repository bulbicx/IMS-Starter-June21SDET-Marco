package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAOTest {

	private final OrderItemDAO DAO = new OrderItemDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		OrderItem orderItem = new OrderItem(1L, 1L, 1, 5.5);
		OrderItem created = new OrderItem(2L, 1L, 1L, 1, 5.5);
		assertEquals(created, DAO.create(orderItem));
	}
	
	@Test
	public void testReadAll() {
		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(new OrderItem(1L, 1L, 1L, 1, 5.5));
		assertEquals(orderItems, DAO.readAll());
	}
	
	@Test
	public void testRead() {
		OrderItem orderItem = new OrderItem(1L, 1L, 1L, 1, 5.5);
		assertEquals(orderItem, DAO.read(1L));
	}
	
	@Test
	public void testReadOrderItems() {
		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(new OrderItem(1L, 1L, 1L, 1, 5.5));
		assertEquals(orderItems, DAO.readOrderItems(1L));
	}
	
	@Test
	public void testReadOrderItem() {
		OrderItem orderItem = new OrderItem(1L, 1L, 1L, 1, 5.5);
		assertEquals(orderItem, DAO.readOrderItem(1L, 1L));
	}
	
	@Test 
	public void testUpdate() {
		OrderItem updated = new OrderItem(1L, 1L, 1L, 2, 5.5);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testUpdateQty() {
		OrderItem updated = new OrderItem(1L, 1L, 1L, 2, 5.5);
		assertEquals(updated, DAO.updateQty(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testDeleteOrderItems() {
		assertEquals(1, DAO.deleteOrderItems(1L));
	}
	
}
