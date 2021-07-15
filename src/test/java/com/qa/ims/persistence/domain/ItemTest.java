package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemTest {
	
	@Test
	public void constructor1Test() {
		//String itemName, String description, double price
		Item item = new Item("name", "A nice pair of shoes", 22.5);
		assertTrue(item instanceof Item);
	}
	
	@Test
	public void constructor2Test() {
		//Long itemId, String itemName, String description, double price
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		assertTrue(item instanceof Item);
	}

	@Test
	public void getItemIdTest() {
		Long expectedId = 1L;
		Item item = new Item(expectedId, "name", "A nice pair of shoes", 22.5);
		
		Long actualId = item.getItemId();
		
		assertEquals(expectedId, actualId);
	}

	@Test
	public void setItemIdTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		Long expectedId = 2L;
		item.setItemId(expectedId);
		
		Long actualId = item.getItemId();
		assertEquals(expectedId, actualId);
	}

	@Test
	public void getItemNameTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		String actualName = item.getItemName();
		
		assertEquals("name", actualName);
	}

	@Test
	public void setItemNameTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		item.setItemName("blue");
		
		String actualName = item.getItemName();
		
		assertEquals("blue", actualName);
	}

	@Test
	public void getDescriptionTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		String actualResult = item.getDescription();
		
		assertEquals("A nice pair of shoes", actualResult);
	}

	@Test
	public void setDescriptionTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		String expectedResult = "a new description";
		item.setDescription(expectedResult);
		 
		String actualResult = item.getDescription();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getPriceTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		double actualResult = item.getPrice();
		
		assertEquals(22.5, actualResult, 0.0);
	}

	@Test
	public void setPriceTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		double expectedPrice = 25.7;
		item.setPrice(expectedPrice);
		
		double actualPrice = item.getPrice();
		
		assertEquals(expectedPrice, actualPrice, 0.0);
	}

	@Test
	public void toStringTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		String expectedResult = "Item ID: 1 | item name: name | description: A nice pair of shoes | price: �22.5";
		String actualResult = item.toString();
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void hashCodeTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		Item item2 = new Item(1L, "name", "A nice pair of shoes", 22.5);
		
		assertTrue(item.hashCode() == item2.hashCode());
	}

	@Test
	public void equalsTest() {
		Item item = new Item(1L, "name", "A nice pair of shoes", 22.5);
		Item item2 = new Item(1L, "name", "A nice pair of shoes", 22.5);
		Item item3 = new Item(2L, "another", "A nice pair of shoes", 25.5);
		
		assertTrue(item.equals(item2));
		assertTrue(item2.equals(item));
		assertFalse(item.equals(item3));
		assertFalse(item3.equals(item));
	}
	
}
