package com.store.pojo;

import javax.persistence.*;

@Table(name = "tb_booktypeinfo")
public class BookTypeInfo {
    /**
     * 图书类型编号
     */
    @Id
    @Column(name = "book_type_Id")
    private Integer bookTypeId;

    /**
     * 图书类型名称
     */
    @Column(name = "book_type_name")
    private String bookTypeName;

    /**
     * 获取图书类型编号
     *
     * @return book_type_Id - 图书类型编号
     */
    public Integer getBookTypeId() {
        return bookTypeId;
    }

    /**
     * 设置图书类型编号
     *
     * @param bookTypeId 图书类型编号
     */
    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    /**
     * 获取图书类型名称
     *
     * @return book_type_name - 图书类型名称
     */
    public String getBookTypeName() {
        return bookTypeName;
    }

    /**
     * 设置图书类型名称
     *
     * @param bookTypeName 图书类型名称
     */
    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    @Override
    public String toString() {
        return "BookTypeInfo{" +
                "bookTypeId=" + bookTypeId +
                ", bookTypeName='" + bookTypeName + '\'' +
                '}';
    }
}