package com.store.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_bookinfo")
public class BookInfo {
    /**
     * 图书编号
     */
    @Id
    @Column(name = "book_Id")
    private Integer bookId;

    /**
     * 图书类型
     */
    @Column(name = "type_Id")
    private Integer typeId;

    /**
     * 图书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 出版社
     */
    @Column(name = "book_press")
    private String bookPress;

    /**
     * 出版日期
     */
    @Column(name = "book_pub_date")
    private Date bookPubDate;

    /**
     * 图书作者
     */
    @Column(name = "book_author")
    private String bookAuthor;

    /**
     * 图书定价
     */
    @Column(name = "book_price")
    private Double bookPrice;

    /**
     * 图书简介
     */
    @Column(name = "book_desc")
    private String bookDesc;

    /**
     * 图书封面图
     */
    @Column(name = "book_picture")
    private String bookPicture;

    /**
     * 图书库存量
     */
    @Column(name = "book_store_count")
    private Integer bookStoreCount;

    /**
     * 获取图书编号
     *
     * @return book_Id - 图书编号
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * 设置图书编号
     *
     * @param bookId 图书编号
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取图书类型
     *
     * @return type_Id - 图书类型
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置图书类型
     *
     * @param typeId 图书类型
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取图书名
     *
     * @return book_name - 图书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置图书名
     *
     * @param bookName 图书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取出版社
     *
     * @return book_press - 出版社
     */
    public String getBookPress() {
        return bookPress;
    }

    /**
     * 设置出版社
     *
     * @param bookPress 出版社
     */
    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    /**
     * 获取出版日期
     *
     * @return book_pub_date - 出版日期
     */
    public Date getBookPubDate() {
        return bookPubDate;
    }

    /**
     * 设置出版日期
     *
     * @param bookPubDate 出版日期
     */
    public void setBookPubDate(Date bookPubDate) {
        this.bookPubDate = bookPubDate;
    }

    /**
     * 获取图书作者
     *
     * @return book_author - 图书作者
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * 设置图书作者
     *
     * @param bookAuthor 图书作者
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * 获取图书定价
     *
     * @return book_price - 图书定价
     */
    public Double getBookPrice() {
        return bookPrice;
    }

    /**
     * 设置图书定价
     *
     * @param bookPrice 图书定价
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * 获取图书简介
     *
     * @return book_desc - 图书简介
     */
    public String getBookDesc() {
        return bookDesc;
    }

    /**
     * 设置图书简介
     *
     * @param bookDesc 图书简介
     */
    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    /**
     * 获取图书封面图
     *
     * @return book_picture - 图书封面图
     */
    public String getBookPicture() {
        return bookPicture;
    }

    /**
     * 设置图书封面图
     *
     * @param bookPicture 图书封面图
     */
    public void setBookPicture(String bookPicture) {
        this.bookPicture = bookPicture;
    }

    /**
     * 获取图书库存量
     *
     * @return book_store_count - 图书库存量
     */
    public Integer getBookStoreCount() {
        return bookStoreCount;
    }

    /**
     * 设置图书库存量
     *
     * @param bookStoreCount 图书库存量
     */
    public void setBookStoreCount(Integer bookStoreCount) {
        this.bookStoreCount = bookStoreCount;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", typeId=" + typeId +
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