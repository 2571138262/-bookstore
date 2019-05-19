package com.store.pojo.CartUtils;

/**
 * 购物车
 */
public class Cart {

    private Integer id;
    private Integer productId;
    private String name;
    private Double price;
    private Integer count;
    private Double totalPrice; // 总价

    public Cart(Integer id, Integer productId, String name, Double price, int count) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.count = count;
        this.totalPrice = price * count;
    }

    // 同一个商品增加的方法
    public void incrCount(){
        count++;
        this.totalPrice = price * count;
    }

    // 商品减少的方法
    public boolean decrCount(){
        count--;
        this.totalPrice = price * count;
        if (count == 0)
            return true;
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
