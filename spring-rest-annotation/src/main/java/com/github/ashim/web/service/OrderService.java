package com.github.ashim.web.service;

import java.util.List;

import com.github.ashim.persistence.model.Order;

public interface OrderService {

	public List<Order> getAllOrdersForCustomer(String customerId);

	public Order getOrderByIdForCustomer(String customerId, String orderId);

}
