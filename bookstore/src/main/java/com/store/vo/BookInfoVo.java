package com.store.vo;

import java.util.Date;

public class BookInfoVo {
    public BookInfoVo() {
    }

    public BookInfoVo(Integer bookId, String bookTypeName, String bookName, String bookPress, Date bookPubDate, String bookAuthor, Double bookPrice, String bookDesc, String bookPicture, Integer bookStoreCount) {
        this.bookId = bookId;
        this.bookTypeName = bookTypeName;
        this.bookName = bookName;
        this.bookPress = bookPress;
        this.bookPubDate = bookPubDate;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.bookDesc = bookDesc;
        this.bookPicture = bookPicture;
        this.bookStoreCount = bookStoreCount;
    }

    /**
     * 图书编号
     */
    private Integer bookId;

    /**
     * 图书类型
     */
    private String bookTypeName;

    /**
     * 图书名
     */
    private String bookName;

    /**
     * 出版社
     */
    private String bookPress;

    /**
     * 出版日期
     */
    private Date bookPubDate;

    /**
     * 图书作者
     */
    private String bookAuthor;

    /**
     * 图书定价
     */
    private Double bookPrice;

    /**
     * 图书简介
     */
    private String bookDesc;

    /**
     * 图书封面图
     */
    private String bookPicture;

    /**
     * 图书库存量
     */
    private Integer bookStoreCount;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public Date getBookPubDate() {
        return bookPubDate;
    }

    public void setBookPubDate(Date bookPubDate) {
        this.bookPubDate = bookPubDate;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookPicture() {
        return bookPicture;
    }

    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    public Integer getBookStoreCount() {
        return bookStoreCount;
    }

    public void setBookStoreCount(Integer bookStoreCount) {
        this.bookStoreCount = bookStoreCount;
    }

    @Override
    public String toString() {
        return "BookInfoVo{" +
                "bookId=" + bookId +
                ", bookTypeName='" + bookTypeName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPress='" + bookPress + '\'' +
                ", bookPubDate=" + bookPubDate +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookPicture='" + bookPicture + '\'' +
                ", bookStoreCount=" + bookStoreCount +
                '}';
    }
}
