package com.store.vo;

import java.util.Date;

public class OrderVo {

    public OrderVo() {
    }

    public OrderVo(Integer id, String custName, String bookName, Date orderDate, String message, String postMethod, String payMethod, String receverName, String receverAddr, String receverTel, Double totalPrice, Integer orderCount, Boolean postStatus, Boolean recevStatus, String bookPicture) {
        this.id = id;
        this.custName = custName;
        this.bookName = bookName;
        this.orderDate = orderDate;
        this.message = message;
        this.postMethod = postMethod;
        this.payMethod = payMethod;
        this.receverName = receverName;
        this.receverAddr = receverAddr;
        this.receverTel = receverTel;
        this.totalPrice = totalPrice;
        this.orderCount = orderCount;
        this.postStatus = postStatus;
        this.recevStatus = recevStatus;
        this.bookPicture = bookPicture;
    }
    
    /**
     * 订单编号
     */
    private Integer id;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 图书名
     */
    private String bookName;

    /**
     * 下单日期
     */
    private Date orderDate;

    /**
     * 留言
     */
    private String message;

    /**
     * 送货方式
     */
    private String postMethod;

    /**
     * 支付方式
     */
    private String payMethod;

    /**
     * 收货人姓名
     */
    private String receverName;

    /**
     * 收货地址
     */
    private String receverAddr;

    /**
     * 收货人电话号码
     */
    private String receverTel;

    /**
     * 总卖出价
     */
    private Double totalPrice;

    /**
     * 订购数量
     */
    private Integer orderCount;

    /**
     * 发货状态 1-发货 0-未发货
     */
    private Boolean postStatus;

    /**
     * 收货状态 1-收货 0-未收货
     */
    private Boolean recevStatus;

    /**
     * 图书封面图
     */
    private String bookPicture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostMethod() {
        return postMethod;
    }

    public void setPostMethod(String postMethod) {
        this.postMethod = postMethod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getReceverName() {
        return receverName;
    }

    public void setReceverName(String receverName) {
        this.receverName = receverName;
    }

    public String getReceverAddr() {
        return receverAddr;
    }

    public void setReceverAddr(String receverAddr) {
        this.receverAddr = receverAddr;
    }

    public String getReceverTel() {
        return receverTel;
    }

    public void setReceverTel(String receverTel) {
        this.receverTel = receverTel;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Boolean getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Boolean postStatus) {
        this.postStatus = postStatus;
    }

    public Boolean getRecevStatus() {
        return recevStatus;
    }

    public void setRecevStatus(Boolean recevStatus) {
        this.recevStatus = recevStatus;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", orderDate=" + orderDate +
                ", message='" + message + '\'' +
                ", postMethod='" + postMethod + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", receverName='" + receverName + '\'' +
                ", receverAddr='" + receverAddr + '\'' +
                ", receverTel='" + receverTel + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderCount=" + orderCount +
                ", postStatus=" + postStatus +
                ", recevStatus=" + recevStatus +
                ", bookPicture='" + bookPicture + '\'' +
                '}';
    }
}
