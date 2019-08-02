package com.spring.customer.model;


public class Order {

    private String customerId;
    private long orderRef;
    private long noOfBricks;
    private boolean isOrderDispatched;


    public Order(String customerId, int noOfBricks, boolean isOrderDispatched,long orderRef) {

        this.customerId = customerId;
        this.noOfBricks = noOfBricks;
        this.isOrderDispatched = isOrderDispatched;
        this.orderRef=orderRef;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isOrderDispatched() {
        return isOrderDispatched;
    }

    public void setOrderDispatched(boolean orderDispatched) {
        isOrderDispatched = orderDispatched;
    }

    public long getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(long orderRef) {
        this.orderRef = orderRef;
    }

    public long getNoOfBricks() {
        return noOfBricks;
    }

    public void setNoOfBricks(long noOfBricks) {
        this.noOfBricks = noOfBricks;
    }
}
