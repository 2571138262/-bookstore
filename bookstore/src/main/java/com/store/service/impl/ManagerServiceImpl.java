package com.store.service.impl;

import com.store.mapper.ManagerMapper;
import com.store.pojo.Manager;
import com.store.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;
    
    @Override
    public void saveManager(Manager manager) throws Exception {
        managerMapper.insert(manager);
    }

    @Override
    public void updateManager(Manager manager) {
        managerMapper.updateByPrimaryKeySelective(manager);
    }

    @Override
    public void deleteManager(Integer adminId) {
        managerMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Manager queryManagerByAdminId(Integer adminId) {
        return managerMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public List<Manager> queryManagerList() {
        return managerMapper.selectAll();
    }

    @Override
    public Manager queryManagerByAdminName(String adminName) {
        Example example = new Example(Manager.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adminName", adminName);
        List<Manager> managers = managerMapper.selectByExample(example);
        if (managers != null && managers.size() > 0){
            return managers.get(0);
        }else {
            return null;
        }
    }
}
