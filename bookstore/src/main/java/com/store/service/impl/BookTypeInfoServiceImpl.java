package com.store.service.impl;

import com.store.mapper.BookTypeInfoMapper;
import com.store.pojo.BookTypeInfo;
import com.store.service.BookTypeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class BookTypeInfoServiceImpl implements BookTypeInfoService {

    @Autowired
    private BookTypeInfoMapper bookTypeInfoMapper;
    
    @Override
    public void saveBookTypeInfo(BookTypeInfo bookTypeInfo) throws Exception {
        bookTypeInfoMapper.insert(bookTypeInfo);
    }

    @Override
    public void deleteBookTypeInfo(Integer bookTypeId) {
        bookTypeInfoMapper.deleteByPrimaryKey(bookTypeId);
    }

    @Override
    public BookTypeInfo queryBookTypeInfoByBookId(Integer bookTypeId) {
        return bookTypeInfoMapper.selectByPrimaryKey(bookTypeId);
    }

    @Override
    public List<BookTypeInfo> queryBookTypeInfoList() {
        return bookTypeInfoMapper.selectAll();
    }

    @Override
    public BookTypeInfo queryBookTypeInfoByBookTypeName(String bookTypeName) {
        Example example = new Example(BookTypeInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookTypeName", bookTypeName);
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoMapper.selectByExample(example);
        if (bookTypeInfos != null && bookTypeInfos.size() > 0){
            return bookTypeInfos.get(0);
        }else {
            return null;
        }
    }
}
