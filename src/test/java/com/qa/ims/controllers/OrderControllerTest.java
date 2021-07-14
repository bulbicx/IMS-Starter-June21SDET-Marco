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
		Order order = new Order(1L, 1L, 10.0);
		Customer customer = new Customer(1L, "mario", "bob");
		
		//Test wrong customer id
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(customerDAO.read(2L)).thenReturn(null);
		assertEquals(null, controller.create());
		
		//Test correct customer id
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(customerDAO.read(1L)).thenReturn(customer);
		Mockito.when(orderDAO.create(new Order(1L))).thenReturn(order);	
		

		Mockito.when(orderDAO.readLatest()).thenReturn(order);
		Mockito.when(utils.getString()).thenReturn("NO");
		Mockito.when(orderDAO.update(order)).thenReturn(order);
		assertEquals(order, controller.create());
	}
	
	@Test
	public void createOrderItemTest() {
		//order_item_id - order_id - item_id - qty - line_total
		OrderItem orderItem = new OrderItem(1L, 1L, 3L, 2, 10.0);
		OrderItem orderItem2 = new OrderItem(1L, 3L, 2, 10.0);
		Order order = new Order(1L, 7L, 10.0);
		Item item = new Item(3L, "name", "description", 5.0);
		
		//Test wrong item id input
		Mockito.when(orderDAO.readLatest()).thenReturn(order);
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(itemDAO.read(2L)).thenReturn(null);
		assertEquals(null, controller.createOrderItem());
		
		//Test correct item id input
		Mockito.when(orderDAO.readLatest()).thenReturn(order);
		Mockito.when(utils.getLong()).thenReturn(3L);
		Mockito.when(itemDAO.read(3L)).thenReturn(item);
		Mockito.when(utils.getInt()).thenReturn(2);
		Mockito.when(orderItemDAO.create(orderItem2)).thenReturn(orderItem);
		
		assertEquals(orderItem, controller.createOrderItem());
		
		Mockito.verify(orderDAO, Mockito.times(2)).readLatest();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(itemDAO, Mockito.times(1)).read(2L);
		Mockito.verify(itemDAO, Mockito.times(1)).read(3L);
		Mockito.verify(utils, Mockito.times(1)).getInt();
		Mockito.verify(orderItemDAO, Mockito.times(1)).create(orderItem2);
	}

	@Test
	public void addItemToOrderTest() {
		OrderItem orderItem = new OrderItem(1L, 1L, 3L, 2, 10.0);
		OrderItem orderItem2 = new OrderItem(2L, 1L, 1L, 2, 10.0);
		Item item = new Item(3L, "name", "description", 5.0);
		Item item2 = new Item(1L, "name", "description", 5.0);
		
		//Test on order item with same item adding on order-item
		Mockito.when(utils.getLong()).thenReturn(3L);
		Mockito.when(itemDAO.read(3L)).thenReturn(item);
		Mockito.when(orderItemDAO.readOrderItem(1L, 3L)).thenReturn(orderItem);
		Mockito.when(orderItemDAO.update(orderItem)).thenReturn(orderItem);
		assertEquals(orderItem, controller.addItemToOrder(1L));
		
		//Test on order item when item to be added is not on the order
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(itemDAO.read(1L)).thenReturn(item2);
		Mockito.when(orderItemDAO.readOrderItem(1L, 1L)).thenReturn(null);
		Mockito.when(utils.getInt()).thenReturn(2);
		Mockito.when(orderItemDAO.create(new OrderItem(1L, 1L, 2, 10.0))).thenReturn(orderItem2);
		assertEquals(orderItem2, controller.addItemToOrder(1L));
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getInt();
		Mockito.verify(itemDAO, Mockito.times(2)).read(3L);
		Mockito.verify(itemDAO, Mockito.times(1)).read(1L);
		Mockito.verify(orderItemDAO, Mockito.times(1)).readOrderItem(1L, 3L);
		Mockito.verify(orderItemDAO, Mockito.times(1)).readOrderItem(1L, 1L);
		Mockito.verify(orderItemDAO, Mockito.times(1)).update(orderItem);
		Mockito.verify(orderItemDAO, Mockito.times(1)).create(new OrderItem(1L, 1L, 2, 10.0));
	}
	
	@Test
	public void updateOrderTotalTest() {
		Order order = new Order(1L, 7L, 10.0);
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem orderItem = new OrderItem(1L, 1L, 5L, 2, 10);
		orderItems.add(orderItem);
		
		Mockito.when(orderDAO.readLatest()).thenReturn(order);
		Mockito.when(orderItemDAO.readOrderItems(order.getOrderId())).thenReturn(orderItems);
		Mockito.when(orderDAO.update(order)).thenReturn(order);
		
		assertEquals(order, controller.updateOrderTotal());
		
		Mockito.verify(orderDAO, Mockito.times(1)).readLatest();
		Mockito.verify(orderDAO, Mockito.times(1)).update(order);
		Mockito.verify(orderItemDAO, Mockito.times(1)).readOrderItems(order.getOrderId());
	}
	
	@Test
	public void updateTotalOrderChangedTest() {
		Order order = new Order(1L, 7L, 10.0);
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem orderItem = new OrderItem(1L, 1L, 5L, 2, 10);
		orderItems.add(orderItem);
		
		Mockito.when(orderDAO.read(1L)).thenReturn(order);
		Mockito.when(orderItemDAO.readOrderItems(order.getOrderId())).thenReturn(orderItems);
		Mockito.when(orderDAO.update(order)).thenReturn(order);
		
		assertEquals(order, controller.updateTotalOrderChanged(1L));
		
		Mockito.verify(orderDAO, Mockito.times(1)).read(1L);
		Mockito.verify(orderDAO, Mockito.times(1)).update(order);
		Mockito.verify(orderItemDAO, Mockito.times(1)).readOrderItems(order.getOrderId());
	}

	@Test
	public void updateAddConditionTest() {
		Order order = new Order(1L, 7L, 10.0);
		
		//order id wrong
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(orderDAO.read(2L)).thenReturn(null);
		assertEquals(null, controller.update());
		
		//Test correct order id with ADD option
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderDAO.read(1L)).thenReturn(order);
		Mockito.when(utils.getString()).thenReturn("ADD");
		assertEquals(order, controller.update());
		
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).read(2L);
		Mockito.verify(orderDAO, Mockito.times(2)).read(1L);
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	
	@Test
	public void updateDeleteConditionTest() {
		Order order = new Order(1L, 7L, 10.0);
		
		//Test correct order id with DELETE option
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderDAO.read(1L)).thenReturn(order);
		Mockito.when(utils.getString()).thenReturn("DELETE");
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderItemDAO.delete(1L)).thenReturn(1);
		
		assertEquals(order, controller.update());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderDAO, Mockito.times(2)).read(1L);
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(orderItemDAO, Mockito.times(1)).delete(1L);
	}
	
	@Test
	public void deleteTest() {
		Order order = new Order(1L, 7L, 10.0);
		
		//Test order id wrong
		Mockito.when(utils.getLong()).thenReturn(2L);
		Mockito.when(orderDAO.read(2L)).thenReturn(null);
		assertEquals(0, controller.delete());
		
		//Test order id correct
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderDAO.read(1L)).thenReturn(order);
		Mockito.when(orderDAO.delete(1L)).thenReturn(1);
		assertEquals(1, controller.delete());
		
		//Test order id correct but error occured when deleting
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderDAO.read(1L)).thenReturn(order);
		Mockito.when(orderDAO.delete(1L)).thenReturn(0);
		assertEquals(0, controller.delete());
		
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).read(2L);
		Mockito.verify(orderDAO, Mockito.times(2)).read(1L);
		Mockito.verify(orderDAO, Mockito.times(2)).delete(1L);
	}
}

