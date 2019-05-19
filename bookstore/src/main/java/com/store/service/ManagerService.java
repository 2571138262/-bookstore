package com.store.service;

import com.store.pojo.Manager;

import java.util.List;

public interface ManagerService {

    public void saveManager(Manager manager) throws Exception;

    public void updateManager(Manager manager);

    public void deleteManager(Integer adminId);

    public Manager queryManagerByAdminId(Integer adminId);

    public List<Manager> queryManagerList();
    
    Manager queryManagerByAdminName(String adminName);
    
}
