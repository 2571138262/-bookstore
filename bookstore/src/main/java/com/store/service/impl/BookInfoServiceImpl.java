package com.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.store.mapper.BookInfoMapper;
import com.store.pojo.BookInfo;
import com.store.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoMapper bookInfoMapper;
    
    @Override
    public void saveBookInfo(BookInfo bookInfo) throws Exception {
        bookInfoMapper.insert(bookInfo);
    }

    @Override
    public void updateBookInfo(BookInfo bookInfo) {
        bookInfoMapper.updateByPrimaryKeySelective(bookInfo);
    }

    @Override
    public void deleteBookInfo(Integer bookId) {
        bookInfoMapper.deleteByPrimaryKey(bookId);
    }

    @Override
    public BookInfo queryBookInfoByBookId(Integer bookId) {
        return bookInfoMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public List<BookInfo> queryBookInfoList() {
//        Example example = new Example(BookInfo.class);
//        Example.Criteria criteria = example.createCriteria();
//        return bookInfoMapper.selectByExample(criteria);
        return bookInfoMapper.selectAll();
    }

    @Override
    public List<BookInfo> queryBookInfoListPaged() {
        Example example = new Example(BookInfo.class);
        Example.Criteria criteria = example.createCriteria();
        
//        criteria.andLike("bookName", "%" + bookInfo.getBookName() + "%");
        
        example.orderBy("bookId").asc();

        return bookInfoMapper.selectByExample(example);
    }

    /**
     * 通过书名查询图书
     * @param bookName
     * @return
     */
    @Override
    public List<BookInfo> queryBookListByBookName(String bookName) {
        Example example = new Example(BookInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("bookName", "%" + bookName + "%");
        example.orderBy("bookId").asc();
        return bookInfoMapper.selectByExample(example);
    }

    /**
     * 通过作者名查询图书
     * @param bookAuthor
     * @return
     */
    @Override
    public List<BookInfo> queryBooInfoListByBookAuthor(String bookAuthor) {
        Example example = new Example(BookInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("bookAuthor", "%" + bookAuthor + "%");
        example.orderBy("bookId").asc();
        return bookInfoMapper.selectByExample(example);
    }

    /**
     * 根据typeId查询对应的图书
     * @param typeId
     * @return
     */
    @Override
    public List<BookInfo> queryBooListByTypeId(Integer typeId) {
        Example example = new Example(BookInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId", typeId);
        example.orderBy("bookId").asc();
        return bookInfoMapper.selectByExample(example);
    }

    /**
     * 根据书名查出对应的图书信息
     * @param bookName
     * @return
     */
    @Override
    public BookInfo queryBookInfoByExample(String bookName) {
        Example example = new Example(BookInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bookName", bookName);
        example.orderBy("bookId");
        List<BookInfo> bookInfos = bookInfoMapper.selectByExample(example);
        if (bookInfos != null && bookInfos.size() > 0){
            return bookInfos.get(0);
        }else{
            return null;
        }
    }
}
