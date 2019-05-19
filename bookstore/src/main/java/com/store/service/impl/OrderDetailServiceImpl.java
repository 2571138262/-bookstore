package com.store.service.impl;

import com.store.mapper.OrderDetailMapper;
import com.store.pojo.OrderDetail;
import com.store.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    
    @Override
    public void saveOrderDetail(OrderDetail orderDetail) throws Exception {
        orderDetailMapper.insert(orderDetail);
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
    }

    @Override
    public void deleteOrderDetail(Integer detailId) {
        orderDetailMapper.deleteByPrimaryKey(detailId);
    }

    @Override
    public OrderDetail queryOrderDetailByDetailId(Integer detailId) {
        return orderDetailMapper.selectByPrimaryKey(detailId);
    }

    @Override
    public List<OrderDetail> queryOrderDetailList() {
        return orderDetailMapper.selectAll();
    }

    @Override
    public OrderDetail queryOrderDetailByUniqueIdentifier(String uniqueIdentifier) {
        Example example = new Example(OrderDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uniqueIdentifier", uniqueIdentifier);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(example);
        if (orderDetails != null && orderDetails.size() > 0){
            return orderDetails.get(0);
        }else {
            return null;
        }
    }
}
