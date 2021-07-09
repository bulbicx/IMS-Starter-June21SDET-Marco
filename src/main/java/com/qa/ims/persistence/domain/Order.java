package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {
	
	private Long orderId;
	private Long customerId;
	private double total;
	
	public Order(Long orderId, Long customerId, double total) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.total = total;
	}
	
	public Order(Long customerId, double total) {
		this.customerId = customerId;
		this.total = total;
	}
	
	public Order(Long customerId) {
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "order ID: " + orderId + " | customerId: " + customerId + " | total: £" + total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, orderId, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(orderId, other.orderId)
				&& Double.doubleToLongBits(total) == Double.doubleToLongBits(other.total);
	}
	
}
