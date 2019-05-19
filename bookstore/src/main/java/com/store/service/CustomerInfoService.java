package com.store.service;

import com.store.pojo.CustomerInfo;

import java.util.List;

public interface CustomerInfoService {

    void saveCustomerInfo(CustomerInfo customerInfo) throws Exception;

    void updateCustomerInfo(CustomerInfo customerInfo);

    void deleteCustomerInfo(Integer custId);

    CustomerInfo queryCustomerInfoByCustId(Integer custId);

    List<CustomerInfo> queryCustomerInfofoList();
    
    CustomerInfo queryCustomerInfoByCustName(String custName);
    
    boolean queryCustomerIsExists(CustomerInfo customerInfo);
    
}
