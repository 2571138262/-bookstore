package com.store.service.impl;

import com.store.mapper.CustomerInfoMapper;
import com.store.pojo.CustomerInfo;
import com.store.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    
    @Override
    public void saveCustomerInfo(CustomerInfo customerInfo) throws Exception {
        customerInfoMapper.insert(customerInfo);
    }

    @Override
    public void updateCustomerInfo(CustomerInfo customerInfo) {
        customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
    }

    @Override
    public void deleteCustomerInfo(Integer custId) {
        customerInfoMapper.deleteByPrimaryKey(custId);
    }

    @Override
    public CustomerInfo queryCustomerInfoByCustId(Integer custId) {
        return customerInfoMapper.selectByPrimaryKey(custId);
    }

    @Override
    public List<CustomerInfo> queryCustomerInfofoList() {
        return customerInfoMapper.selectAll();
    }

    @Override
    public CustomerInfo queryCustomerInfoByCustName(String custName) {
        Example example = new Example(CustomerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("custName", custName);
        List<CustomerInfo> customerInfos = customerInfoMapper.selectByExample(example);
        if (customerInfos != null && customerInfos.size() > 0){
            return customerInfos.get(0);
        }else{
            return null;
        }
    }

    @Override
    public boolean queryCustomerIsExists(CustomerInfo customerInfo) {
        List<CustomerInfo> users = customerInfoMapper.selectAll();
        return users.contains(customerInfo);
    }
}
