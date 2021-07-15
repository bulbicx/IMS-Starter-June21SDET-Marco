package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	/*
	 * Displays all the items to the Logger 
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		if(items.size() < 1) {
			LOGGER.info("There are no items stored in the database.");
			return items;
		}
		LOGGER.info("*".repeat(50));
		for (Item item : items) {
			LOGGER.info(item);
		}
		LOGGER.info("*".repeat(50));
		return items;
	}
	
	/**
	 * Reads an item by taking their id
	 */
	@Override
	public Item readOne() {
		LOGGER.info("Please enter the id of the item you would like to read");
		Long id = utils.getLong();
		Item item = itemDAO.read(id);
		if(item == null) {
			LOGGER.info("Item with the id specified could not be found. Please insert a valid ID");
			return item;
		}
		LOGGER.info("*".repeat(50));
		LOGGER.info(item);
		LOGGER.info("*".repeat(50));
		return item;
	}

	/*
	 * Creates an item by taking in user input 
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter a description");
		String description = utils.getString();
		LOGGER.info("Please enter a price");
		Double price = utils.getDouble();
		Item item = itemDAO.create(new Item(itemName, description, price));
		LOGGER.info("Item created");
		LOGGER.info("*".repeat(50));
		return item;
	}

	/*
	 * Updates an existing item by taking in user input 
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		Item item = itemDAO.read(id);
		if(item == null) {
			LOGGER.info("Item with the id specified could not be found. Please insert a valid ID");
			return item;
		}
		LOGGER.info("Please enter the new item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter the new description");
		String description = utils.getString();
		LOGGER.info("Please enter the new price");
		Double price = utils.getDouble();
		item = itemDAO.update(new Item(id, itemName, description, price));
		LOGGER.info("Item Updated");
		LOGGER.info("*".repeat(50));
		return item;
	}

	/*
	 * Deletes an existing item by taking item id from user input
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		Item item = itemDAO.read(id);
		if(item == null) {
			LOGGER.info("Item with the id specified could not be found. Please insert a valid ID");
			return 0;
		}
		int result = itemDAO.delete(id);
		if(result > 0) {
			LOGGER.info("Item Deleted");			
		}
		return result;
	}

}
