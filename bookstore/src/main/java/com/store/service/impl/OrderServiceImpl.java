package com.store.service.impl;

import com.store.mapper.OrderMapper;
import com.store.pojo.Order;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Override
    public void saveOrder(Order order) throws Exception {
        orderMapper.insert(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public Order queryOrderByOrderId(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<Order> queryOrderList() {
        return orderMapper.selectAll();
    }

    @Override
    public List<Order> queryOrderListByCustomerId(Integer customerId) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("customerId", customerId);
        example.orderBy("id").asc();
        return orderMapper.selectByExample(example);
    }
}
