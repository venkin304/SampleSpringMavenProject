package com.spring.customer.repository;

import com.spring.customer.model.Order;

import java.util.List;


public interface ManageOrderDAO {

    long createOrder(Order order);

    Order getOrder(long orderRef);

    List<Order> getOrders();

    long updateOrder(Order order);

    boolean fulfillOrder(long orderRef);


}
