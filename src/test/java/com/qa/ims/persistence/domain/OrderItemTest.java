package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderItemTest {

	@Test
	public void constructor1Test() {
		//Long orderItemId, Long orderId, Long itemId, int qty, double lineTotal
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		assertTrue(orderItem instanceof OrderItem);
	}
	
	@Test
	public void constructor2Test() {
		//Long orderId, Long itemId, int qty, double lineTotal
		OrderItem orderItem = new OrderItem(2L, 3L, 2, 22.5);
		assertTrue(orderItem instanceof OrderItem);
	}

	@Test
	public void getOrderItemIdTest() {
		Long expectedResult = 1L;
		OrderItem orderItem = new OrderItem(expectedResult, 2L, 3L, 2, 22.5);
		
		Long actualResult = orderItem.getOrderItemId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void setOrderItemIdTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		Long expectedResult = 5L;
		
		orderItem.setOrderItemId(expectedResult);
		Long actualResult = orderItem.getOrderItemId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getOrderIdTest() {
		Long expectedResult = 2L;
		OrderItem orderItem = new OrderItem(1L, expectedResult, 3L, 2, 22.5);
		
		Long actualResult = orderItem.getOrderId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void setOrderIdTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		Long expectedResult = 6L;
		
		orderItem.setOrderId(expectedResult);
		Long actualResult = orderItem.getOrderId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getItemIdTest() {
		Long expectedResult = 3L;
		OrderItem orderItem = new OrderItem(1L, 2L, expectedResult, 2, 22.5);
		
		Long actualResult = orderItem.getItemId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void setItemIdTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 2L, 2, 22.5);
		Long expectedResult = 3L;
		
		orderItem.setItemId(expectedResult);
		Long actualResult = orderItem.getItemId();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getQtyTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		
		int actualResult = orderItem.getQty();
		
		assertEquals(2, actualResult);
	}

	@Test
	public void setQtyTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		
		int expectedResult = 3;
		orderItem.setQty(expectedResult);
		int actualResult = orderItem.getQty();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getLineTotalTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		
		double actualResult = orderItem.getLineTotal();
		
		assertEquals(22.5, actualResult, 0.0);
	}

	@Test
	public void setLineTotal() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		
		double expectedResult = 25.9;
		orderItem.setLineTotal(expectedResult);
		double actualResult = orderItem.getLineTotal();
		
		assertEquals(expectedResult, actualResult, 0.0);
	}

	@Test
	public void toStringTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		
		String expectedResult = "order item ID: 1 | order ID: 2 | item ID:3 | quantity: 2 | lineTotal: �22.5";
		String actualResult = orderItem.toString();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void hashCodeTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		OrderItem orderItem2 = new OrderItem(1L, 2L, 3L, 2, 22.5);
		
		assertTrue(orderItem.hashCode() == orderItem2.hashCode());
	}

	@Test
	public void equalsTest() {
		OrderItem orderItem = new OrderItem(1L, 2L, 3L, 2, 22.5);
		OrderItem orderItem2 = new OrderItem(1L, 2L, 3L, 2, 22.5);
		OrderItem orderItem3 = new OrderItem(2L, 4L, 3L, 3, 25.5);
		
		assertTrue(orderItem.equals(orderItem2) && orderItem2.equals(orderItem));
		assertFalse(orderItem.equals(orderItem3) && orderItem3.equals(orderItem));
	}

}
