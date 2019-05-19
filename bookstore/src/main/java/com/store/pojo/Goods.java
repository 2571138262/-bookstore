package com.store.pojo;

import javax.persistence.*;

@Table(name = "tb_goods")
public class Goods {
    public Goods() {
    }

    public Goods(Integer goodsId, Integer customerId, Integer bookinfoId, String bookinfoName, Integer count, Double price, Double totalPrice) {
        this.goodsId = goodsId;
        this.customerId = customerId;
        this.bookinfoId = bookinfoId;
        this.bookinfoName = bookinfoName;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    /**
     * 购物车Id编号
     */
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 用户Id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 图书的Id
     */
    @Column(name = "bookinfo_id")
    private Integer bookinfoId;

    /**
     * 书名
     */
    @Column(name = "bookinfo_name")
    private String bookinfoName;

    /**
     * 总数
     */
    private Integer count;

    /**
     * 单价
     */
    private Double price;

    /**
     * 总价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * 获取购物车Id编号
     *
     * @return goods_id - 购物车Id编号
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置购物车Id编号
     *
     * @param goodsId 购物车Id编号
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取用户Id
     *
     * @return customer_id - 用户Id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置用户Id
     *
     * @param customerId 用户Id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取图书的Id
     *
     * @return bookinfo_id - 图书的Id
     */
    public Integer getBookinfoId() {
        return bookinfoId;
    }

    /**
     * 设置图书的Id
     *
     * @param bookinfoId 图书的Id
     */
    public void setBookinfoId(Integer bookinfoId) {
        this.bookinfoId = bookinfoId;
    }

    /**
     * 获取书名
     *
     * @return bookinfo_name - 书名
     */
    public String getBookinfoName() {
        return bookinfoName;
    }

    /**
     * 设置书名
     *
     * @param bookinfoName 书名
     */
    public void setBookinfoName(String bookinfoName) {
        this.bookinfoName = bookinfoName;
    }

    /**
     * 获取总数
     *
     * @return count - 总数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置总数
     *
     * @param count 总数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取总价
     *
     * @return total_price - 总价
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总价
     *
     * @param totalPrice 总价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}