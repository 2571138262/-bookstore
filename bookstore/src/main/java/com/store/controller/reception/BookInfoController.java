package com.store.controller.reception;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.pojo.BookInfo;
import com.store.pojo.BookTypeInfo;
import com.store.pojo.CustomerInfo;
import com.store.service.BookInfoService;
import com.store.service.BookTypeInfoService;
import com.store.utils.Common;
import com.store.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookstore")
public class BookInfoController {
    
    @Autowired
    private BookInfoService bookInfoService;
    
    @Autowired
    private BookTypeInfoService bookTypeInfoServic;
    
    @GetMapping("/save")
    @ResponseBody
    public Msg saveBookInfo() throws Exception {
        
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTypeId(5);
        bookInfo.setBookName("神探夏洛克");
        bookInfo.setBookPress("法国出版社");
        bookInfo.setBookAuthor("法国佬");
        bookInfo.setBookPrice(22.00);
        bookInfo.setBookDesc("讲的是一个侦探破案的故事");
        bookInfo.setBookStoreCount(60);
        
        bookInfoService.saveBookInfo(bookInfo);
        
        return Msg.success().add("success", "保存成功");
    }
    
    @GetMapping(value = "/query")
//    @ResponseBody
    public String queryBookInfoList(Model model){
        List<BookInfo> bookInfos = bookInfoService.queryBookInfoList();
        Msg result = Msg.success().add("bookInfos", bookInfos);
        model.addAttribute("result", result);
        return "reception/bookstore";
    }
    
    /**
     * 分页查询所有图书信息
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/querypaged")
//    @ResponseBody 
    public String queryBookInfoListPaged(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 获取所有图书类型
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoServic.queryBookTypeInfoList();
        System.out.println(bookTypeInfos);
        
        // 引入PageHelper分页插件 在查询之前调用，传入页码， 以及每一页的大小
        PageHelper.startPage(page, 12);
        // 此查询在 startPage 之后，那么这个方法就是一个分页查询
        List<BookInfo> books = bookInfoService.queryBookInfoListPaged();
        // 使用PageInfo对查询到的结果进行包装，封装完成之后，只需要将PageInfo交给页面就行
        // 封装了详细的分页信息，包括有我们查询出来的数据 , 传入连续显示的页数（导航页） -- 具体参照官方文档
        PageInfo pageInfo = new PageInfo(books, 3);//navigatePages 导航页显示的个数
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success().add("pageInfo", pageInfo)
                .add("types", bookTypeInfos)
                .add("typeId", "ALL");
        if (user == null){
            result.add("user", "NOUSER");
        }else{
            result.add("user", user);
        }
        model.addAttribute("result", result);
        return "reception/bookstore";
    }

    /**
     * 通过书名查询图书
     * @param name
     * @return
     */
    @GetMapping("/queryByName")
    public String queryBookListByBookName(@RequestParam String name, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PageHelper.startPage(1, 10000);
        List<BookInfo> bookInfos = new ArrayList<>();
        bookInfos.addAll(bookInfoService.queryBookListByBookName(name));
        bookInfos.addAll(bookInfoService.queryBooInfoListByBookAuthor(name));
        PageInfo pageInfo = new PageInfo(bookInfos, 1);//navigatePages 导航页显示的个数
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success().add("pageInfo", pageInfo)
                .add("types", bookTypeInfoServic.queryBookTypeInfoList())
                .add("typeId", "SEARCH");
        if (user == null){
            result.add("user", "NOUSER");
        }else{
            result.add("user", user);
        }
        model.addAttribute("result", result);
        return "reception/bookstore";
    }
    
    // 根据图书类型分类查询图书
    @RequestMapping("/queryByType")
//    @ResponseBody
    public String queryBooListByTypeId(@RequestParam Integer typeId, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageHelper.startPage(1, 10000);
        PageInfo pageInfo = new PageInfo(bookInfoService.queryBooListByTypeId(typeId), 0);//navigatePages 导航页显示的个数
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success().add("pageInfo", pageInfo)
                .add("types", bookTypeInfoServic.queryBookTypeInfoList()) // 显示所有图书类型
                .add("typeId", typeId);
        if (user == null){
            result.add("user", "NOUSER");
        }else{
            result.add("user", user);
        }
        model.addAttribute("result", result);
        return "reception/bookstore";
//        return result;
    }
    
    // 图书详情
    @RequestMapping("/bookDetail")
    public String queryBookInfoByBookId(@RequestParam Integer bookId, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(bookId);
        BookTypeInfo bookTypeInfo = bookTypeInfoServic.queryBookTypeInfoByBookId(bookInfo.getTypeId());
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success();
        if (user != null) {
            result.add("bookInfo", bookInfo)
                    .add("bookTypeInfo", bookTypeInfo)
                    .add("types", bookTypeInfoServic.queryBookTypeInfoList())
                    .add("typeId", "NOTALL")
                    .add("user", user);
            model.addAttribute("result", result);
        } else {
            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
            request.getRequestDispatcher("/customer/signIn").forward(request, response);
            return null;
        }
        return "reception/details";
    }
    
}
