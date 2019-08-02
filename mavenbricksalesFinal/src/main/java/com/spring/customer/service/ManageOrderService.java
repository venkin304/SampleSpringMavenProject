package com.spring.customer.service;

import com.spring.customer.model.Order;

import java.util.List;



public interface ManageOrderService {

    long createOrder(Order order) throws IllegalArgumentException;

    Order getOrder(long orderRef);

    List<Order> getOrders();

    long updateOrder(Order order) throws IllegalArgumentException;

    boolean fulfillOrder(long orderRef);
}
