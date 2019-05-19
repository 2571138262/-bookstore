package com.store.service;

import com.store.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    public void saveOrderDetail(OrderDetail orderDetail) throws Exception;

    public void updateOrderDetail(OrderDetail orderDetail);

    public void deleteOrderDetail(Integer detailId);

    public OrderDetail queryOrderDetailByDetailId(Integer detailId);

    public OrderDetail queryOrderDetailByUniqueIdentifier(String uniqueIdentifier);
    
    public List<OrderDetail> queryOrderDetailList();
    
}
