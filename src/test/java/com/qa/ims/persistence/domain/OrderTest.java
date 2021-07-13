package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderTest {
	
	@Test
	public void constructor1Test() {
		//Long orderId, Long customerId, double total
		Order order = new Order(1L, 2L, 23.6);
		assertTrue(order instanceof Order);
	}
	
	@Test
	public void constructor2Test() {
		//Long customerId, double total
		Order order = new Order(2L, 23.6);
		assertTrue(order instanceof Order);
	}
	
	@Test
	public void constructor3Test() {
		//Long customerId
		Order order = new Order(2L);
		assertTrue(order instanceof Order);
	}

	@Test
	public void getOrderIdTest() {
		Long expectedResult = 1L;
		Order order = new Order(expectedResult, 2L, 23.6);
		
		Long actualResult = order.getOrderId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void setOrderIdTest() {
		Order order = new Order(1L, 2L, 23.6);
		
		Long expectedResult = 3L;
		order.setOrderId(expectedResult);
		Long actualResult = order.getOrderId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getCustomerIdTest() {
		Long expectedResult = 2L;
		Order order = new Order(1L, expectedResult, 23.6);
		
		Long actualResult = order.getCustomerId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void setCustomerIdTest() {
		Order order = new Order(1L, 2L, 23.6);
		
		Long expectedResult = 3L;
		order.setCustomerId(expectedResult);
		Long actualResult = order.getCustomerId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getTotalTest() {
		Order order = new Order(1L, 2L, 23.6);
		
		double actualResult = order.getTotal();
		
		assertEquals(23.6, actualResult, 0.0);
	}

	@Test
	public void setTotalTest() {
		Order order = new Order(1L, 2L, 23.6);
		
		double expectedResult = 26.7;
		order.setTotal(expectedResult);
		double actualResult = order.getTotal();
		
		assertEquals(expectedResult, actualResult, 0.0);
	}

	@Test
	public void toStringTest() {
		Order order = new Order(1L, 2L, 23.6);
		String expectedResult = "order ID: 1 | customerId: 2 | total: �23.6";
		String actualResult = order.toString();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void hashCodeTest() {
		Order order = new Order(1L, 2L, 23.6);
		Order order2 = new Order(1L, 2L, 23.6);
		
		assertTrue(order.hashCode() == order2.hashCode());
	}

	@Test
	public void equalsTest() {
		Order order = new Order(1L, 2L, 23.6);
		Order order2 = new Order(1L, 2L, 23.6);
		Order order3 = new Order(2L, 3L, 27.6);
		
		assertTrue(order.equals(order2) && order2.equals(order));
		assertFalse(order.equals(order3) && order3.equals(order));
	}
	
}
