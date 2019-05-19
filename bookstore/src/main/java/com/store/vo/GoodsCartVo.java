package com.store.vo;

public class GoodsCartVo {

    public GoodsCartVo() {
    }

    public GoodsCartVo(Integer goodsId, Integer customerId, Integer bookinfoId, String bookinfoName, Integer count, Double price, Double totalPrice, String bookPicture) {
        this.goodsId = goodsId;
        this.customerId = customerId;
        this.bookinfoId = bookinfoId;
        this.bookinfoName = bookinfoName;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.bookPicture = bookPicture;
    }

    /**
     * 购物车Id编号
     */
    private Integer goodsId;

    /**
     * 用户Id
     */
    private Integer customerId;

    /**
     * 图书的Id
     */
    private Integer bookinfoId;

    /**
     * 书名
     */
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
    private Double totalPrice;

    /**
     * 图书封面图
     */
    private String bookPicture;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookinfoId() {
        return bookinfoId;
    }

    public void setBookinfoId(Integer bookinfoId) {
        this.bookinfoId = bookinfoId;
    }

    public String getBookinfoName() {
        return bookinfoName;
    }

    public void setBookinfoName(String bookinfoName) {
        this.bookinfoName = bookinfoName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    @Override
    public String toString() {
        return "GoodsCartVo{" +
                "goodsId=" + goodsId +
                ", customerId=" + customerId +
                ", bookinfoId=" + bookinfoId +
                ", bookinfoName='" + bookinfoName + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", bookPicture='" + bookPicture + '\'' +
                '}';
    }
}
