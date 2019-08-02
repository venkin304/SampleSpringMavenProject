package com.spring.customer.service.impl;

import com.spring.customer.model.Order;
import com.spring.customer.repository.ManageOrderDAO;
import com.spring.customer.service.ManageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("manageOrderService")
public class ManageOrderServiceImpl implements ManageOrderService {

    @Autowired()
    private ManageOrderDAO manageOrderDAO;


    @Override
    public long createOrder(Order order) throws IllegalArgumentException{

        return manageOrderDAO.createOrder(order);

    }

    @Override
    public Order getOrder(long orderRef) throws IllegalArgumentException{

       return manageOrderDAO.getOrder(orderRef);
    }

    @Override
    public List<Order> getOrders() {

        return manageOrderDAO.getOrders();

    }

    @Override
    public long updateOrder(Order order) throws IllegalArgumentException{

        return manageOrderDAO.updateOrder(order);
    }

    @Override
    public boolean fulfillOrder(long orderRef)throws IllegalArgumentException{


        return manageOrderDAO.fulfillOrder(orderRef);
    }
}
