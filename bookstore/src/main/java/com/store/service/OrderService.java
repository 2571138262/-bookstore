package com.store.service;

import com.store.pojo.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order) throws Exception;

    void updateOrder(Order order);

    void deleteOrder(Integer orderId);

    Order queryOrderByOrderId(Integer orderId);

    List<Order> queryOrderList();
    
    List<Order> queryOrderListByCustomerId(Integer customerId);
    
}
