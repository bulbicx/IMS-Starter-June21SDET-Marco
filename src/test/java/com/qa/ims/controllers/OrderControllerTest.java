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

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private OrderItemDAO orderItemDAO;
	
	@InjectMocks
	private OrderController controller;
	
	@Test
	public void readAllTest() {
		List<Order> orders = new ArrayList<>();
		
		//Test no data on db found
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		//Test data present on db
		Order order = new Order(1L, 2L, 22.6);
		orders.add(order);
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		Mockito.verify(orderDAO, Mockito.times(2)).readAll();
	}

	@Test
	public void readOneTest() {
		Order order = new Order(1L, 2L, 22.5);
		Long orderID = 1L;
		Customer customer = new Customer(7L, "mario", "bob");
		List<OrderItem> orderitems = new ArrayList<>();
		
		//first order-item
		Item item = new Item(5L, "name", "description", 10);
		OrderItem orderItem = new OrderItem(10L, orderID, 5L, 2, 20.0);

		//second order-item
		Item item2 = new Item(8L, "new", "haha", 6);
		OrderItem orderItem2 = new OrderItem(11L, orderID, 8L, 2, 12.0);
		
		orderitems.add(orderItem);
		orderitems.add(orderItem2);
		
		//Test not found id
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(orderDAO.read(2L)).thenReturn(null);
		
		assertEquals(null, controller.readOne());
		
		//Test found ID
		Mockito.when(utils.getLong()).thenReturn(orderID);
		Mockito.when(orderDAO.read(orderID)).thenReturn(order);
		Mockito.when(customerDAO.read(order.getCustomerId())).thenReturn(customer);
		Mockito.when(orderItemDAO.readOrderItems(orderID)).thenReturn(orderitems);
		Mockito.when(itemDAO.read(orderitems.get(0).getItemId())).thenReturn(item);
		Mockito.when(itemDAO.read(orderitems.get(1).getItemId())).thenReturn(item2);
		
		assertEquals(order, controller.readOne());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).read(2L);
		Mockito.verify(orderDAO, Mockito.times(1)).read(orderID);
		Mockito.verify(customerDAO, Mockito.times(1)).read(order.getCustomerId());
		Mockito.verify(orderItemDAO, Mockito.times(1)).readOrderItems(orderID);
		Mockito.verify(itemDAO, Mockito.times(1)).read(orderitems.get(0).getItemId());
		Mockito.verify(itemDAO, Mockito.times(1)).read(orderitems.get(1).getItemId());
	}

	@Test
	public void createTest() {
		Order order = new Order(1L, 7L, 10.0);
		Customer customer = new Customer(7L, "mario", "bob");
		
		//Test wrong customer id
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(customerDAO.read(2L)).thenReturn(null);
		
		assertEquals(null, controller.create());
		
		//Test correct customer id
		Mockito.when(utils.getLong()).thenReturn(7L);
		Mockito.when(customerDAO.read(7L)).thenReturn(customer);
		Mockito.when(orderDAO.create(order)).thenReturn(order);
		OrderItem orderItem = new OrderItem(1L, 5L, 2, 10);
		Mockito.when(orderItemDAO.create(orderItem)).thenReturn(orderItem);
		Mockito.when(utils.getString()).thenReturn("NO");
		
		
		assertEquals(new Order(1L, 7L), controller.create());
		
	}

	@Test
	public void createOrderItemTest() {
		//order_item_id - order_id - item_id - qty - line_total
		OrderItem orderItem = new OrderItem(1L, 1L, 5L, 2, 10);
		Order order = new Order(1L, 7L, 10.0);
		Item item = new Item(3L, "name", "description", 5);
		
		Mockito.when(orderDAO.readLatest()).thenReturn(order.getOrderId()).thenReturn(order.getOrderId());
		Mockito.when(Mockito.when(orderDAO.readLatest()).thenReturn(order)).thenReturn(order.getOrderId());
		//Test wrong item id input
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(itemDAO.read(2L)).thenReturn(null);
		assertEquals(null, controller.createOrderItem());
		
		//Test correct item id input
		Mockito.when(utils.getLong()).thenReturn(3L);
		Mockito.when(itemDAO.read(3L)).thenReturn(item);
		Mockito.when(utils.getInt()).thenReturn(2);
		Mockito.when(itemDAO.read(3L).getPrice()).thenReturn(item.getPrice());
		Mockito.when(orderItemDAO.create(orderItem)).thenReturn(orderItem);
		
		
	}

	@Test
	public void addItemToOrderTest() {
	}

	@Test
	public void updateOrderTotalTest() {
	}

	@Test
	public void updateTotalOrderChangedTest() {
	}

	@Test
	public void updateTest() {
	}
	
	@Test
	public void deleteTest() {
	}

	@Test
	public void deleteOrderItemsTest() {
	}
	
	@Test
	public void deleteOrderItemTest() {
	}
	
	@Test
	public void updateMsgTest() {
	}
	
	@Test
	public void deleteMsgTest() {
	}
}

