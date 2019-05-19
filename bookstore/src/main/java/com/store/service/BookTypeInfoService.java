package com.store.service;

import com.store.pojo.BookTypeInfo;

import java.util.List;

public interface BookTypeInfoService {
    
    public void saveBookTypeInfo(BookTypeInfo bookTypeInfo) throws Exception;
    
    public void deleteBookTypeInfo(Integer bookTypeId);

    public BookTypeInfo queryBookTypeInfoByBookId(Integer bookTypeId);

    public List<BookTypeInfo> queryBookTypeInfoList();
    
    BookTypeInfo queryBookTypeInfoByBookTypeName(String bookTypeName);
}
