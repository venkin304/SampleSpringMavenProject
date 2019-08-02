package com.spring.customer.controller;

import com.spring.customer.configuration.SpringContextConfiguration;
import com.spring.customer.model.Order;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

       ResponseEntity<String> responseEntity= controller.fulfillOrder(1004);

       Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void testFulfillOrder_failure(){

        ResponseEntity<String> responseEntity= controller.fulfillOrder(100);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }


}
