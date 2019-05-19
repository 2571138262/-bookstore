package com.store.vo;

import java.util.Date;

public class CustOrderVo {

    public CustOrderVo() {
    }

    public CustOrderVo(Integer id, String bookName, Integer orderCount, Double totalPrice, String receverName, String receverAddr, String receverTel, Date orderDate, Boolean postStatus, Boolean recevStatus) {
        this.id = id;
        this.bookName = bookName;
        this.orderCount = orderCount;
        this.totalPrice = totalPrice;
        this.receverName = receverName;
        this.receverAddr = receverAddr;
        this.receverTel = receverTel;
        this.orderDate = orderDate;
        this.postStatus = postStatus;
        this.recevStatus = recevStatus;
    }

    /**
     * id
     */
    private Integer id;

    /**
     * 图书名
     */
    private String bookName;

    /**
     * 订购数量
     */
    private Integer orderCount;

    /**
     * 总卖出价
     */
    private Double totalPrice;

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
     * 下单日期
     */
    private Date orderDate;

    /**
     * 发货状态 1-发货 0-未发货
     */
    private Boolean postStatus;

    /**
     * 收货状态 1-收货 0-未收货
     */
    private Boolean recevStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getRecevStatus() {
        return recevStatus;
    }

    public void setRecevStatus(Boolean recevStatus) {
        this.recevStatus = recevStatus;
    }

    public Boolean getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Boolean postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return "CustOrderVo{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", orderCount=" + orderCount +
                ", totalPrice=" + totalPrice +
                ", receverName='" + receverName + '\'' +
                ", receverAddr='" + receverAddr + '\'' +
                ", receverTel='" + receverTel + '\'' +
                ", orderDate=" + orderDate +
                ", postStatus=" + postStatus +
                ", recevStatus=" + recevStatus +
                '}';
    }
}
