package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	/*
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/*
	 * Reads one order by using the id provided from user input to the logger
	 */
	@Override
	public Order readOne() {
		LOGGER.info("Please enter the id of the order you would like to read");
		Long id = utils.getLong();
		Order order = orderDAO.read(id);
		LOGGER.info(order);
		return order;
	}

	/*
	 * It creates an order by using user inputs
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the customer id making this order");
		Long id = utils.getLong();
		Order order = orderDAO.create(new Order(id));
		LOGGER.info("Order created");
		return order;
	}
	
	/*
	 * It creates an order by using user inputs
	 */
	public OrderItem createOrderItem() {
		LOGGER.info("Please enter the customer id making this order");
		Long id = utils.getLong();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Order order = orderDAO.create(new Order(id));
		LOGGER.info("Order created");
		return order;
	}

	/*
	 * It updates an order by taking an order id
	 */
	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * It deletes an order by taking an order id
	 */
	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
