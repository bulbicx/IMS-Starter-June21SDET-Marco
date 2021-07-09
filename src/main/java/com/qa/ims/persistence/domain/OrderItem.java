package com.qa.ims.persistence.domain;

import java.util.Objects;

public class OrderItem {

	private Long orderItemId;
	private Long orderId;
	private Long itemId;
	private int qty;
	private double lineTotal;

	public OrderItem(Long orderItemId, Long orderId, Long itemId, int qty, double lineTotal) {
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.itemId = itemId;
		this.qty = qty;
		this.lineTotal = lineTotal;
	}
	
	public OrderItem(Long orderId, Long itemId, int qty, double lineTotal) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.qty = qty;
		this.lineTotal = lineTotal;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

	@Override
	public String toString() {
		return "order item ID: " + orderItemId + " | order ID: " + orderId + " | item ID:" + itemId + " | quantity: " + qty
				+ " | lineTotal: £" + lineTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, lineTotal, orderId, orderItemId, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(itemId, other.itemId)
				&& Double.doubleToLongBits(lineTotal) == Double.doubleToLongBits(other.lineTotal)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(orderItemId, other.orderItemId)
				&& qty == other.qty;
	}

}
