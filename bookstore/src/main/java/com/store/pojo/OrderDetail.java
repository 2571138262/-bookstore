package com.store.pojo;

import javax.persistence.*;

@Table(name = "tb_orderdetail")
public class OrderDetail {

    public OrderDetail() {
    }

    public OrderDetail(Integer detailId, String uniqueIdentifier, Integer bId, Integer orderCount, Boolean postStatus, Boolean recevStatus, Double totalPrice) {
        this.detailId = detailId;
        this.uniqueIdentifier = uniqueIdentifier;
        this.bId = bId;
        this.orderCount = orderCount;
        this.postStatus = postStatus;
        this.recevStatus = recevStatus;
        this.totalPrice = totalPrice;
    }

    /**
     * 详细订单编号
     */
    @Id
    @Column(name = "detail_Id")
    private Integer detailId;

    /**
     * 唯一标示
     */
    @Column(name = "unique_identifier")
    private String uniqueIdentifier;

    /**
     * 图书编号
     */
    @Column(name = "b_Id")
    private Integer bId;

    /**
     * 订购数量
     */
    @Column(name = "order_count")
    private Integer orderCount;

    /**
     * 发货状态 1-发货 0-未发货
     */
    @Column(name = "post_status")
    private Boolean postStatus;

    /**
     * 收货状态 1-收货 0-未收货
     */
    @Column(name = "recev_status")
    private Boolean recevStatus;

    /**
     * 总价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * 获取详细订单编号
     *
     * @return detail_Id - 详细订单编号
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * 设置详细订单编号
     *
     * @param detailId 详细订单编号
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * 获取唯一标示
     *
     * @return unique_identifier - 唯一标示
     */
    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    /**
     * 设置唯一标示
     *
     * @param uniqueIdentifier 唯一标示
     */
    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    /**
     * 获取图书编号
     *
     * @return b_Id - 图书编号
     */
    public Integer getbId() {
        return bId;
    }

    /**
     * 设置图书编号
     *
     * @param bId 图书编号
     */
    public void setbId(Integer bId) {
        this.bId = bId;
    }

    /**
     * 获取订购数量
     *
     * @return order_count - 订购数量
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * 设置订购数量
     *
     * @param orderCount 订购数量
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 获取发货状态 1-发货 0-未发货
     *
     * @return post_status - 发货状态 1-发货 0-未发货
     */
    public Boolean getPostStatus() {
        return postStatus;
    }

    /**
     * 设置发货状态 1-发货 0-未发货
     *
     * @param postStatus 发货状态 1-发货 0-未发货
     */
    public void setPostStatus(Boolean postStatus) {
        this.postStatus = postStatus;
    }

    /**
     * 获取收货状态 1-收货 0-未收货
     *
     * @return recev_status - 收货状态 1-收货 0-未收货
     */
    public Boolean getRecevStatus() {
        return recevStatus;
    }

    /**
     * 设置收货状态 1-收货 0-未收货
     *
     * @param recevStatus 收货状态 1-收货 0-未收货
     */
    public void setRecevStatus(Boolean recevStatus) {
        this.recevStatus = recevStatus;
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "detailId=" + detailId +
                ", uniqueIdentifier='" + uniqueIdentifier + '\'' +
                ", bId=" + bId +
                ", orderCount=" + orderCount +
                ", postStatus=" + postStatus +
                ", recevStatus=" + recevStatus +
                ", totalPrice=" + totalPrice +
                '}';
    }
}