package com.store.service;

import com.store.pojo.BookInfo;

import java.util.List;

public interface BookInfoService {
    
    public void saveBookInfo(BookInfo bookInfo) throws Exception;
    
    void updateBookInfo(BookInfo bookInfo);
    
    void deleteBookInfo(Integer bookId);
    
    BookInfo queryBookInfoByBookId(Integer bookId);
    
    List<BookInfo> queryBookInfoList();

    /**
     * 分页查询所有图书信息
     * @return
     */
    List<BookInfo> queryBookInfoListPaged();

    /**
     * 通过图书名查询图书
     * @param bookName
     * @return
     */
    List<BookInfo> queryBookListByBookName(String bookName);
    
    /**
     * 通过作者查询图书
     * @param bookAuthor
     * @return
     */
    List<BookInfo> queryBooInfoListByBookAuthor(String bookAuthor);

    /**
     * 通过typeId查询对应的图书
     * @param typeId
     * @return
     */
    List<BookInfo> queryBooListByTypeId(Integer typeId);

    /**
     * 根据图书名信息查出对应的记录
     * @param bookName
     * @return
     */
    BookInfo queryBookInfoByExample(String bookName);
}
