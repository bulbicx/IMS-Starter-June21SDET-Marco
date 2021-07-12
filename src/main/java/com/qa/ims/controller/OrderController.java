package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	private ItemDAO itemDAO;
	private CustomerDAO customerDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, ItemDAO itemDAO, OrderItemDAO orderItemDAO, CustomerDAO customerDAO, Utils utils) {
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.orderItemDAO = orderItemDAO;
		this.customerDAO = customerDAO;
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
		if(order == null) {
			LOGGER.info("Order with the id specified could not be found. Please insert a valid ID");
			return order;
		}
		Customer customer = customerDAO.read(order.getCustomerId());
		List<OrderItem> orderItems = orderItemDAO.readOrderItems(id);
		LOGGER.info("*".repeat(50));
		LOGGER.info(order);
		LOGGER.info("-".repeat(50));
		LOGGER.info("ORDER DETAILS: ");
		LOGGER.info("-".repeat(50));
		for (OrderItem orderItem : orderItems) {
			Item item = itemDAO.read(orderItem.getItemId());
			LOGGER.info("order item id: " + orderItem.getOrderItemId() + " | item id: " + orderItem.getItemId() + " | quantity: " + orderItem.getQty() + " | lineTotal: £" + orderItem.getLineTotal());
			LOGGER.info("       ITEM INFO:");
			LOGGER.info("       item name: " + item.getItemName() + " | description: " + item.getDescription() + " | price: £" + item.getPrice()  );
		}
		LOGGER.info("-".repeat(50));
		LOGGER.info("CUSTOMER INFO:");
		LOGGER.info("-".repeat(50));
		LOGGER.info("name: " + customer.getFirstName() + " | surname: " + customer.getSurname());
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
		Customer customer = customerDAO.read(id);
		if(customer == null) {
			LOGGER.info("Customer with the id specified could not be found. Please insert a valid ID");
			return null;
		}
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
		LOGGER.info("Please enter the item id to include in this order");
		Long itemId = utils.getLong();
		Item item = itemDAO.read(itemId);
		if(item == null) {
			LOGGER.info("Item with the id specified could not be found. Please insert a valid ID");
			return null;
		}
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
		Item item = itemDAO.read(itemId);
		if(item == null) {
			LOGGER.info("Item with the id specified could not be found. Please insert a valid ID");
			return null;
		}
		OrderItem orderItem = orderItemDAO.readOrderItem(orderId, itemId);
		if (orderItem != null) {//if item is found on order just update the quantity
			int qty = orderItem.getQty() + 1;
			orderItem.setQty(qty);
			double priceItem = itemDAO.read(itemId).getPrice();
			orderItem.setLineTotal(priceItem * qty);
			orderItem = orderItemDAO.update(orderItem);
			LOGGER.info("Order has been updated");
			return orderItem;
		} else {
			LOGGER.info("Please enter the quantity for this item");
			int qty = utils.getInt();
			double priceItem = itemDAO.read(itemId).getPrice();
			double lineTotal = priceItem * qty;
			orderItem = orderItemDAO.create(new OrderItem(orderId, itemId, qty, lineTotal));
			LOGGER.info("Item has been added to the order");
			return orderItem;			
		}
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
		Order order = orderDAO.read(orderId);
		if(order == null) {
			LOGGER.info("Order with the id specified could not be found. Please insert a valid ID");
			return null;
		}
		LOGGER.info("Would you like to add an item(add) or delete an item(delete) to/from this existing order?");
		String answer = utils.getString().toUpperCase();
		if (answer.equals("ADD")) {
			addItemToOrder(orderId);
			return updateTotalOrderChanged(orderId);
		} else if (answer.equals("DELETE")) {
			LOGGER.info("Please enter the order item id you would like to delete");
			Long orderItemId = utils.getLong();
			deleteOrderItem(orderItemId);
			return updateTotalOrderChanged(orderId);
		} else {
			LOGGER.warn("Option chosen is wrong.");
			update();
		}
		return null;
	}
	
	/*
	 * It deletes an order by taking an order id
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		Order order = orderDAO.read(id);
		if(order == null) {
			LOGGER.info("Order with the id specified could not be found. Please insert a valid ID");
			return 0;
		}
		deleteOrderItems(id);
		int result = orderDAO.delete(id);
		if (result < 1) {
			LOGGER.warn("Some error occured. Order could not be deleted.");
		}
		return result;
	}
	
	/*
	 * It deletes all order items having same order id
	 */
	public int deleteOrderItems(Long orderId) {
		int result = orderItemDAO.deleteOrderItems(orderId);
		LOGGER.info(deleteMsg(result));
		return result;
	}
	
	/*
	 * It deletes an order_item from an order when updating the order
	 */
	public int deleteOrderItem(Long orderItemId) {
		int result = orderItemDAO.delete(orderItemId);
		LOGGER.info(updateMsg(result));
		return result;
	}
	
	//It return a message depending on successful or unsuccessful DELETE operation
	public String updateMsg(int result) {
		if(result > 0) {
			return "Order updated successfully";
		} else if (result < 1) {
			return "Some error occured. Retry.";
		}
		return "Some error occured.";
	}
	
	//It return a message depending on successful or unsuccessful DELETE operation
	public String deleteMsg(int result) {
		if(result > 0) {
			return "Order deleted successfully";
		} else if (result < 1) {
			return "Some error occured. Retry.";
		}
		return "Some error occured.";
	}

}
