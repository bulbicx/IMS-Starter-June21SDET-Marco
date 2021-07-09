package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
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
	
	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order readOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
