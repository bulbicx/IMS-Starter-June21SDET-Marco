package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	private ItemDAO itemDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, ItemDAO itemDAO, OrderItemDAO orderItemDAO, Utils utils) {
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.orderItemDAO = orderItemDAO;
		this.utils = utils;
	}
	
	/*
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		LOGGER.info("*".repeat(50));
		for (Order order : orders) {
			LOGGER.info(order);
		}
		LOGGER.info("*".repeat(50));
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
		LOGGER.info("*".repeat(50));
		LOGGER.info(order);
		LOGGER.info("*".repeat(50));
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
		
		//Adding items to the order
		boolean carryOn = true;
		while(carryOn) {
			createOrderItem();
			LOGGER.info("Would you like to continue to add items to the order? (Yes/No)");
			String answer = utils.getString().toUpperCase();
			if (answer.equals("NO")) {
				carryOn = false;
			}
		}
		
		//Updating the order with its total
		updateOrderTotal();
		LOGGER.info("Order created");
		return order;
	}
	
	/*
	 * It adds an item to an order when creating one by using user inputs
	 */
	public OrderItem createOrderItem() {
		Long orderId = orderDAO.readLatest().getOrderId();
		LOGGER.info("Please enter the item id making this order");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the quantity for this item");
		int qty = utils.getInt();
		double priceItem = itemDAO.read(itemId).getPrice();
		double lineTotal = priceItem * qty;
		OrderItem orderItem = orderItemDAO.create(new OrderItem(orderId, itemId, qty, lineTotal));
		LOGGER.info("Item added to the order");
		return orderItem;
	}
	
	/*
	 * It adds an item to an existing order when updating one by using user inputs
	 */
	public OrderItem addItemToOrder(Long orderId) {
		LOGGER.info("Please enter the item id to add to this order");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the quantity for this item");
		int qty = utils.getInt();
		double priceItem = itemDAO.read(itemId).getPrice();
		double lineTotal = priceItem * qty;
		OrderItem orderItem = orderItemDAO.create(new OrderItem(orderId, itemId, qty, lineTotal));
		LOGGER.info("Item has been added to the order");
		return orderItem;
	}
	
	/*
	 * It updates an order total by summing all the line total of that
	 * particular order
	 */
	public Order updateOrderTotal() {
		Order order = orderDAO.readLatest();
		List<OrderItem> orderItems = orderItemDAO.readOrderItems(order.getOrderId());
		double total = 0;
		for (int i = 0; i < orderItems.size(); i++) {
			total += orderItems.get(i).getLineTotal();
		}
		order.setTotal(total);
		orderDAO.update(order);
		return order;
	}
	
	/*
	 * It updates an order total by summing all the line total of that
	 * particular order taking also new items added to the order
	 */
	public Order updateTotalOrderChanged(Long orderId) {
		Order order = orderDAO.read(orderId);
		List<OrderItem> orderItems = orderItemDAO.readOrderItems(order.getOrderId());
		double total = 0;
		for (int i = 0; i < orderItems.size(); i++) {
			total += orderItems.get(i).getLineTotal();
		}
		order.setTotal(total);
		orderDAO.update(order);
		return order;
	}

	/*
	 * It updates an order by allowing to either add items to an order or
	 * modifying an order item contained in an order
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderId = utils.getLong();
		LOGGER.info("Would you like to add an item(add) or delete an item(delete) to/from this existing order?");
		String answer = utils.getString().toUpperCase();
		if (answer.equals("ADD")) {
			addItemToOrder(orderId);
			return updateTotalOrderChanged(orderId);
		} else if (answer.equals("DELETE")) {
			LOGGER.info("Please enter the order item id you would like to delete");
			Long orderItemId = utils.getLong();
			deleteOrderItem(orderItemId);
			LOGGER.info("Order item deleted from order");
			return updateTotalOrderChanged(orderId);
		} else {
			LOGGER.warn("Option chosen is wrong. Please insert ADD or DELETE keywords");
			update();
		}
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
	
	public int deleteOrderItem(Long orderItemId) {
		return orderItemDAO.delete(orderItemId);
	}

}
