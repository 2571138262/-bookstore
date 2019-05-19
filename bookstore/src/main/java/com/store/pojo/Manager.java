package com.store.pojo;

import javax.persistence.*;

@Table(name = "tb_manager")
public class Manager {
    /**
     * 管理员编号
     */
    @Id
    @Column(name = "admin_Id")
    private Integer adminId;

    /**
     * 管理员名称
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 密码
     */
    @Column(name = "admin_pwd")
    private String adminPwd;

    /**
     * 获取管理员编号
     *
     * @return admin_Id - 管理员编号
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员编号
     *
     * @param adminId 管理员编号
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取管理员名称
     *
     * @return admin_name - 管理员名称
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员名称
     *
     * @param adminName 管理员名称
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取密码
     *
     * @return admin_pwd - 密码
     */
    public String getAdminPwd() {
        return adminPwd;
    }

    /**
     * 设置密码
     *
     * @param adminPwd 密码
     */
    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }
}