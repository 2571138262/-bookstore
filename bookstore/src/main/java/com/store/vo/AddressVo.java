package com.store.vo;

public class AddressVo {

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
     * 留言
     */
    private String message;


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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AddressVo{" +
                "receverName='" + receverName + '\'' +
                ", receverAddr='" + receverAddr + '\'' +
                ", receverTel='" + receverTel + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
