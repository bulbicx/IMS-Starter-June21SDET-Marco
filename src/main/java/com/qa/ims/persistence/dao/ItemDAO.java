package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemId = resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		String description = resultSet.getString("description");
		double price = resultSet.getDouble("price");
		return new Item(itemId, itemName, description, price);
	}

	/*
	 * Reads all items from database
	 * 
	 * @return a list of items
	 */
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Item> items = new ArrayList<>();
			if(resultSet.next()) {
				resultSet.previous();
				while(resultSet.next()) {
					items.add(modelFromResultSet(resultSet));
				}
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/*
	 * Return the most recent item added on the database 
	 */
	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			if(resultSet.next()) {
				return modelFromResultSet(resultSet);
			}
			return null;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	@Override
	public Item create(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items(item_name, description, price) VALUES (?, ?, ?)");) {
			statement.setString(1, item.getItemName());
			statement.setString(2, item.getDescription());
			statement.setDouble(3, item.getPrice());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Item read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE item_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				if(resultSet.next()) {
					return modelFromResultSet(resultSet);
				}
				return null;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	
	/*
	 * This method updates an existing item in the database
	*/
	@Override
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE items SET item_name = ?, description = ?, price = ? WHERE item_id = ?");) {
			statement.setString(1, item.getItemName());
			statement.setString(2, item.getDescription());
			statement.setDouble(3, item.getPrice());
			statement.setLong(4, item.getItemId());
			statement.executeUpdate();
			return read(item.getItemId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/*
	 * This method deletes an item from the database 
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE item_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
