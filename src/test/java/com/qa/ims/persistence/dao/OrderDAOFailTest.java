package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOFailTest {

	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect("fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		Order order = new Order(1L);
		assertNull(DAO.create(order));
	}
	
	@Test
	public void testRead() {
		assertNull(DAO.read(1L));
	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		assertEquals(orders, DAO.readAll());		
	}
	
	@Test
	public void testUpdate() {
		Order order = new Order(1L, 1L, 5.5);
		assertNull(DAO.update(order));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
}
