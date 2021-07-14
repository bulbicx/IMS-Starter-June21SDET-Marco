package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAOTest {

	private final CustomerDAO customerDAO = new CustomerDAO();
	private final OrderDAO orderDAO = new OrderDAO();
	private final OrderItemDAO DAO = new OrderItemDAO();
	private final ItemDAO itemDAO = new ItemDAO();

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
	public void testRead() {
		
	}
	
}
