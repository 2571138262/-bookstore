package com.store.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_order")
public class Order {

    public Order() {
    }

    public Order(Integer id, Integer orderId, Integer customerId, Date orderDate, String message, String postMethod, String payMethod, String receverName, String receverAddr, String receverTel, Double totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.message = message;
        this.postMethod = postMethod;
        this.payMethod = payMethod;
        this.receverName = receverName;
        this.receverAddr = receverAddr;
        this.receverTel = receverTel;
        this.totalPrice = totalPrice;
    }

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "order_Id")
    private Integer orderId;

    /**
     * 客户编号
     */
    @Column(name = "customer_Id")
    private Integer customerId;

    /**
     * 下单日期
     */
    @Column(name = "order_date")
    private Date orderDate;

    /**
     * 留言
     */
    private String message;

    /**
     * 送货方式
     */
    @Column(name = "post_method")
    private String postMethod;

    /**
     * 支付方式
     */
    @Column(name = "pay_method")
    private String payMethod;

    /**
     * 收货人姓名
     */
    @Column(name = "recever_name")
    private String receverName;

    /**
     * 收货地址
     */
    @Column(name = "recever_addr")
    private String receverAddr;

    /**
     * 收货人电话号码
     */
    @Column(name = "recever_tel")
    private String receverTel;

    /**
     * 总卖出价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_Id - 订单编号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取客户编号
     *
     * @return customer_Id - 客户编号
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户编号
     *
     * @param customerId 客户编号
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取下单日期
     *
     * @return order_date - 下单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 设置下单日期
     *
     * @param orderDate 下单日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 获取留言
     *
     * @return message - 留言
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置留言
     *
     * @param message 留言
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取送货方式
     *
     * @return post_method - 送货方式
     */
    public String getPostMethod() {
        return postMethod;
    }

    /**
     * 设置送货方式
     *
     * @param postMethod 送货方式
     */
    public void setPostMethod(String postMethod) {
        this.postMethod = postMethod;
    }

    /**
     * 获取支付方式
     *
     * @return pay_method - 支付方式
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * 设置支付方式
     *
     * @param payMethod 支付方式
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取收货人姓名
     *
     * @return recever_name - 收货人姓名
     */
    public String getReceverName() {
        return receverName;
    }

    /**
     * 设置收货人姓名
     *
     * @param receverName 收货人姓名
     */
    public void setReceverName(String receverName) {
        this.receverName = receverName;
    }

    /**
     * 获取收货地址
     *
     * @return recever_addr - 收货地址
     */
    public String getReceverAddr() {
        return receverAddr;
    }

    /**
     * 设置收货地址
     *
     * @param receverAddr 收货地址
     */
    public void setReceverAddr(String receverAddr) {
        this.receverAddr = receverAddr;
    }

    /**
     * 获取收货人电话号码
     *
     * @return recever_tel - 收货人电话号码
     */
    public String getReceverTel() {
        return receverTel;
    }

    /**
     * 设置收货人电话号码
     *
     * @param receverTel 收货人电话号码
     */
    public void setReceverTel(String receverTel) {
        this.receverTel = receverTel;
    }

    /**
     * 获取总卖出价
     *
     * @return total_price - 总卖出价
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总卖出价
     *
     * @param totalPrice 总卖出价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", message='" + message + '\'' +
                ", postMethod='" + postMethod + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", receverName='" + receverName + '\'' +
                ", receverAddr='" + receverAddr + '\'' +
                ", receverTel='" + receverTel + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}