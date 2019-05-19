package com.store.controller.reception;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.pojo.BookInfo;
import com.store.pojo.BookTypeInfo;
import com.store.service.BookInfoService;
import com.store.service.BookTypeInfoService;
import com.store.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class BookTypeInfoController {
    
    @Autowired
    private BookTypeInfoService bookTypeInfoService;
    
    @Autowired
    private BookInfoService bookInfoService;
    
    @GetMapping("/query")
    @ResponseBody
    public Msg queryBookTypeInfoList(){
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
        return Msg.success().add("bookTypeInfos", bookTypeInfos);
    }

//    @GetMapping("/index")
//    public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model){
//
//        // 获取所有图书类型
//        List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
//        System.out.println(bookTypeInfos);
//
//        // 引入PageHelper分页插件 在查询之前调用，传入页码， 以及每一页的大小
//        PageHelper.startPage(page, 2);
//        // 此查询在 startPage 之后，那么这个方法就是一个分页查询
//        List<BookInfo> books = bookInfoService.queryBookInfoListPaged();
//        // 使用PageInfo对查询到的结果进行包装，封装完成之后，只需要将PageInfo交给页面就行
//        // 封装了详细的分页信息，包括有我们查询出来的数据 , 传入连续显示的页数（导航页） -- 具体参照官方文档
//        PageInfo pageInfo = new PageInfo(books, 3);//navigatePages 导航页显示的个数
//        Msg result = Msg.success().add("pageInfo", pageInfo)
//                .add("types", bookTypeInfos)
//                .add("typeId", "ALL");
//        model.addAttribute("result", result);
////        return result;
//        return "reception/index";
//    }
    
}
