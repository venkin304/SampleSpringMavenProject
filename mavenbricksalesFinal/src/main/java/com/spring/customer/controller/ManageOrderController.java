package com.spring.customer.controller;
import com.spring.customer.model.Order;
import com.spring.customer.service.ManageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/rest/customer")
public class ManageOrderController {

    private final ManageOrderService manageOrderService;

    @Autowired
    public ManageOrderController(ManageOrderService manageOrderService) {
        this.manageOrderService = manageOrderService;
    }

    @RequestMapping(value="/createOrder" ,method = POST)
    @ResponseStatus(CREATED)
    public long createOrder(@RequestBody Order order) throws IllegalArgumentException{

        return  manageOrderService.createOrder(order);
    }

    @RequestMapping(value = "/getOrder/{id}", method = GET)
    @ResponseBody
    public Order getOrder(@PathVariable long id){

        return manageOrderService.getOrder(id);
    }

    @RequestMapping(value="/allOrders", method = GET)
    @ResponseBody
    public List<Order> getOrders(){

        return manageOrderService.getOrders();
    }

    @RequestMapping(value="/updateOrder",method = PUT)
    @ResponseStatus(OK)
    public long updateOrder(@RequestBody Order order) {

        return manageOrderService.updateOrder(order);
    }

    @RequestMapping(value="/fulfillOrder",method = PUT)
    @ResponseBody
    public ResponseEntity<String> fulfillOrder(@RequestBody long orderRef){
        try{
            if(manageOrderService.fulfillOrder(orderRef)){
                return new ResponseEntity<String>(OK);
            }
        }catch(IllegalArgumentException exception){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }



}
