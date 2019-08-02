package com.spring.customer.controller;

import com.spring.customer.configuration.SpringContextConfiguration;
import com.spring.customer.model.Order;
import com.spring.customer.service.ManageOrderService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringContextConfiguration.class)
public class ManageOrderControllerTest {

    @Autowired
    ManageOrderController controller;


    @Test
    public void testCreateOrder_success(){

        Order order=new Order("cust01",10,false,1005);
        Assert.assertNotNull(controller.createOrder(order));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateOrder_failure(){

        Order order=null;
        controller.createOrder(order);
    }

    @Test
    public void testGetOrder_success(){

        Assert.assertNotNull(controller.getOrder(1004));
    }

    @Test
    public void testGetOrder_failure(){
        Assert.assertNull(controller.getOrder(234));
    }

    @Test
    public void testGetOrders_success(){
        Assert.assertTrue(controller.getOrders().size()>0);
    }

    @Test
    public void testUpdateOrder_success(){

        Order order=new Order("cust01",30,false,1004);
        Assert.assertNotNull(controller.updateOrder(order));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateOrder_failure(){

        Order order=new Order("cust01",30,false,0);
        controller.updateOrder(order);
    }


    @Test
    public void testFulfillOrder_success(){

        Assert.assertTrue(controller.fulfillOrder(1004));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFulfillOrder_failure(){

        controller.fulfillOrder(100);
    }


}
