package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();
	private final OrderItemDAO orderItemDAO = new OrderItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		Order order = new Order(1L);
		Order newOrder = new Order(2L, 1L, 0.0);
		assertEquals(newOrder, DAO.create(order));
	}
	
	@Test
	public void testRead() {
		Order order = new Order(1L, 1L, 5.5);
		assertEquals(order, DAO.read(1L));
	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		Order order = new Order(1L, 1L, 5.5);
		orders.add(order);
		assertEquals(orders, DAO.readAll());		
	}
	
	@Test
	public void testUpdate() {
		Order order = new Order(1L, 1L, 5.5);
		assertEquals(order, DAO.update(order));
	}
	
	@Test
	public void testDelete() {
		orderItemDAO.delete(1);
		assertEquals(1, DAO.delete(1));
	}
}
