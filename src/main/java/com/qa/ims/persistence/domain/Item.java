package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long itemId;
	private String itemName;
	private String description;
	private double price;
	
	public Item(String itemName, String description, double price) {
		this.itemName = itemName;
		this.description = description;
		this.price = price;
	}
	
	public Item(Long itemId, String itemName, String description, double price) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item ID: " + itemId + " | item name: " + itemName + " | description: " + description + " | price: £" + price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, itemId, itemName, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(description, other.description) && itemId == other.itemId
				&& Objects.equals(itemName, other.itemName)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
	
	
}
