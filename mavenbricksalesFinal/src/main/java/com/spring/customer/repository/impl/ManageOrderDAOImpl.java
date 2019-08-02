package com.spring.customer.repository.impl;

import com.spring.customer.model.Order;
import com.spring.customer.repository.ManageOrderDAO;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository("manageOrderDAO")
public class ManageOrderDAOImpl implements ManageOrderDAO {

    //dummy orderList :Ideally should be retrieved from DB /Web Service
    private  static List<Order> dummyOrderList = new ArrayList<Order>();


    public ManageOrderDAOImpl(){
        //creating dummy data
        Order order1=new Order("Cust01",20,false,1001);
        Order order2=new Order("Cust02",200,false,1002);
        Order order3=new Order("Cust03",50,false,1003);
        Order order4=new Order("Cust04",90,true,1004);
        dummyOrderList.add(order1);
        dummyOrderList.add(order2);
        dummyOrderList.add(order3);
        dummyOrderList.add(order4);
    }

    /**
     * method to create a new Order
     * @param order
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public long createOrder(Order order) throws IllegalArgumentException{

        if(order==null){
            throw new IllegalArgumentException("Order passed as part of the request is null");
        }

        OptionalLong maxId = dummyOrderList.stream().mapToLong(Order::getOrderRef).max();
        if (maxId.isPresent()) {
            order.setOrderRef(maxId.getAsLong()+1);
        }
        dummyOrderList.add(order);

        return order.getOrderRef();
    }

    /**
     *  method to return a single order based on order referenceId
     * @param orderRef
     * @return
     */
    @Override
    public Order getOrder(long orderRef){

        return dummyOrderList.stream()
                .filter(eachOrder -> orderRef==eachOrder.getOrderRef())
                .findFirst()
                .orElse(null);
    }

    /**
     * method to return all the orders
     * @return
     */
    @Override
    public List<Order> getOrders(){
        return dummyOrderList;
    }

    /**
     * Updates the existing order with new data
     * @param newOrder
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public long updateOrder(Order newOrder) throws IllegalArgumentException{

        if(getOrder(newOrder.getOrderRef())!=null) {
            dummyOrderList.stream().forEach(eachOrder -> {
                if (!eachOrder.isOrderDispatched() && eachOrder.getOrderRef() == newOrder.getOrderRef() ) {
                    eachOrder.setNoOfBricks(newOrder.getNoOfBricks());
                }
            });
            return newOrder.getOrderRef();
        }
        else
            throw new IllegalArgumentException("The new Order seems invalid");
    }

    /**
     * Fulfills the order if the order is not fulfilled
     * @param orderRef
     * @return
     */
    @Override
    public boolean fulfillOrder(long orderRef){

        AtomicBoolean isMatched = new AtomicBoolean(false);
        dummyOrderList.stream().filter(eachOrder -> eachOrder.getOrderRef()==orderRef)
                                .forEach(order-> {
                                    order.setOrderDispatched(true);
                                    isMatched.set(true);
                                });
        if(isMatched.get()){
            return true;
        }else{
            throw new IllegalArgumentException("Invalid Order Reference");
        }

    }
}
