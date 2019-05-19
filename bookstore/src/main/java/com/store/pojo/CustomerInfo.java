package com.store.pojo;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Table(name = "tb_customerinfo")
public class CustomerInfo {
    /**
     * 客户编号
     */
    @Id
    @Column(name = "cust_Id")
    private Integer custId;

    /**
     * 客户名称
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 客户密码
     */
    @Column(name = "cust_pwd")
    private String custPwd;

    /**
     * 真实姓名
     */
    @Column(name = "cust_true_name")
    private String custTrueName;

    /**
     * 性别、1-男 2-女
     */
    @Column(name = "cust_sex")
    private Boolean custSex;

    /**
     * 客户电话
     */
    @Column(name = "cust_tel")
    private String custTel;

    /**
     * E-mail
     */
    @Column(name = "cust_email")
    private String custEmail;

    /**
     * 地址
     */
    @Column(name = "cust_addr")
    private String custAddr;

    /**
     * 注册时间
     */
    @Column(name = "cust_reg_time")
    private Date custRegTime;

    /**
     * 最近登陆时间
     */
    @Column(name = "cust_last_login")
    private Date custLastLogin;

    /**
     * 获取客户编号
     *
     * @return cust_Id - 客户编号
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * 设置客户编号
     *
     * @param custId 客户编号
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    /**
     * 获取客户名称
     *
     * @return cust_name - 客户名称
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置客户名称
     *
     * @param custName 客户名称
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * 获取客户密码
     *
     * @return cust_pwd - 客户密码
     */
    public String getCustPwd() {
        return custPwd;
    }

    /**
     * 设置客户密码
     *
     * @param custPwd 客户密码
     */
    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }

    /**
     * 获取真实姓名
     *
     * @return cust_true_name - 真实姓名
     */
    public String getCustTrueName() {
        return custTrueName;
    }

    /**
     * 设置真实姓名
     *
     * @param custTrueName 真实姓名
     */
    public void setCustTrueName(String custTrueName) {
        this.custTrueName = custTrueName;
    }

    /**
     * 获取性别、1-男 2-女
     *
     * @return cust_sex - 性别、1-男 2-女
     */
    public Boolean getCustSex() {
        return custSex;
    }

    /**
     * 设置性别、1-男 2-女
     *
     * @param custSex 性别、1-男 2-女
     */
    public void setCustSex(Boolean custSex) {
        this.custSex = custSex;
    }

    /**
     * 获取客户电话
     *
     * @return cust_tel - 客户电话
     */
    public String getCustTel() {
        return custTel;
    }

    /**
     * 设置客户电话
     *
     * @param custTel 客户电话
     */
    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    /**
     * 获取E-mail
     *
     * @return cust_email - E-mail
     */
    public String getCustEmail() {
        return custEmail;
    }

    /**
     * 设置E-mail
     *
     * @param custEmail E-mail
     */
    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    /**
     * 获取地址
     *
     * @return cust_addr - 地址
     */
    public String getCustAddr() {
        return custAddr;
    }

    /**
     * 设置地址
     *
     * @param custAddr 地址
     */
    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    /**
     * 获取注册时间
     *
     * @return cust_reg_time - 注册时间
     */
    public Date getCustRegTime() {
        return custRegTime;
    }

    /**
     * 设置注册时间
     *
     * @param custRegTime 注册时间
     */
    public void setCustRegTime(Date custRegTime) {
        this.custRegTime = custRegTime;
    }

    /**
     * 获取最近登陆时间
     *
     * @return cust_last_login - 最近登陆时间
     */
    public Date getCustLastLogin() {
        return custLastLogin;
    }

    /**
     * 设置最近登陆时间
     *
     * @param custLastLogin 最近登陆时间
     */
    public void setCustLastLogin(Date custLastLogin) {
        this.custLastLogin = custLastLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfo that = (CustomerInfo) o;
        return custName.equals(that.custName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custName);
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custPwd='" + custPwd + '\'' +
                ", custTrueName='" + custTrueName + '\'' +
                ", custSex=" + custSex +
                ", custTel='" + custTel + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custAddr='" + custAddr + '\'' +
                ", custRegTime=" + custRegTime +
                ", custLastLogin=" + custLastLogin +
                '}';
    }
}