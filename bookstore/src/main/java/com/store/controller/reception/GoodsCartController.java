package com.store.controller.reception;

import com.store.pojo.*;
import com.store.service.BookInfoService;
import com.store.service.BookTypeInfoService;
import com.store.service.GoodsService;
import com.store.utils.Common;
import com.store.utils.Msg;
import com.store.vo.AddressVo;
import com.store.vo.GoodsCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsCartController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BookTypeInfoService bookTypeInfoServic;

    //查看我的购物车
    @RequestMapping(value = "/goodslist")
    public String userInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success();
        if (user != null) {
            return getGoodList(result, model, user);
        } else {
            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
            request.getRequestDispatcher("/customer/signIn").forward(request, response);
            return null;
        }
    }

    @RequestMapping("/addGoods")
    public String saveGoods(@RequestParam(value = "bookId") Integer bookId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success();
        if (user != null) {
            BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(bookId);
            Double bookPrice = bookInfo.getBookPrice();
            String bookName = bookInfo.getBookName();
            Goods goods = goodsService.queryGoodsByBookInfoIdAndCustomerId(bookId, user.getCustId());
            if (goods == null) {
                goods = new Goods(null, user.getCustId(), bookId, bookName, 1, bookPrice, bookPrice);
                goodsService.saveGoods(goods);
            } else {
                Integer count = goods.getCount();
                Integer bookStoreCount = bookInfo.getBookStoreCount();
                if (bookStoreCount > count) {
                    count++;
                } else {
                    count = bookStoreCount;
                }
                goods = new Goods(goods.getGoodsId(), user.getCustId(), bookId, bookName, count, bookPrice, bookPrice * count);
                goodsService.updateGoods(goods);
            }
            return getGoodList(result, model, user);
        } else {
            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
            request.getRequestDispatcher("/customer/signIn").forward(request, response);
            return null;
        }
    }

    // 删除
    @RequestMapping("/deleteGoods")
    public String deleteGoods(@RequestParam(value = "goodsId") Integer goodsId, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success();
        if (user != null) {
            goodsService.deleteGoods(goodsId);
            return getGoodList(result, model, user);
        } else {
            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
            request.getRequestDispatcher("/customer/signIn").forward(request, response);
            return null;
        }
    }

    // -
    @RequestMapping("/decr")
    public String decrCount(@RequestParam(value = "goodsId") Integer goodsId, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success();
        if (user != null) {
            Goods goods = goodsService.queryGoodsByGoodsId(goodsId);
            Integer count = goods.getCount();
            if (count == 1) {
                count = 1;
            } else {
                count -= 1;
            }
            goods.setTotalPrice(goods.getPrice() * count);
            goods.setCount(count);
            goodsService.updateGoods(goods);
            return getGoodList(result, model, user);
        } else {
            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
            request.getRequestDispatcher("/customer/signIn").forward(request, response);
            return null;
        }
    }

    // +
    @RequestMapping("/incr")
    public String incrCount(@RequestParam(value = "goodsId") Integer goodsId, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        Msg result = Msg.success();
        if (user != null) {
            Goods goods = goodsService.queryGoodsByGoodsId(goodsId);
            BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(goods.getBookinfoId());
            Integer bookStoreCount = bookInfo.getBookStoreCount();
            Integer count = goods.getCount();
            if (bookStoreCount > count) {
                count += 1;
            } else {
                count = bookStoreCount;
            }
            goods.setCount(count);
            goods.setTotalPrice(goods.getPrice() * count);
            goodsService.updateGoods(goods);
            return getGoodList(result, model, user);
        } else {
            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
            request.getRequestDispatcher("/customer/signIn").forward(request, response);
            return null;
        }
    }

    // 获取购物车列表
    private String getGoodList(Msg result, Model model, CustomerInfo user) {
        result.add("messageTip", "NOTIP");
        result.add("message", "NOTIP");
        // 获取所有图书类型
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoServic.queryBookTypeInfoList();
        result.add("types", bookTypeInfos)
                .add("typeId", "ALL");
        result.add("user", user);
        result.add("addressVo", "NOADDR");
        List<Goods> goodsList = goodsService.queryGoodsListByCustomerId(user.getCustId());
        List<GoodsCartVo> goodsCartVos = new ArrayList<>();
        if (goodsList != null && goodsList.size() > 0) {
            for (Goods goods :
                    goodsList) {
                // 得到图书的id
                Integer bookinfoId = goods.getBookinfoId();
                // 根据id得到图书
                BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(bookinfoId);
                GoodsCartVo goodsCartVo = new GoodsCartVo(goods.getGoodsId(), goods.getCustomerId(), bookinfoId, goods.getBookinfoName(),
                        goods.getCount(), goods.getTotalPrice(), goods.getTotalPrice(), bookInfo.getBookPicture());
                goodsCartVos.add(goodsCartVo);
            }
            result.add("goodsList", goodsCartVos);
        } else {
//            result.add("messageTip", "空空如也");
            result.add("goodsList", "NOGOODS");
        }
        result.add("center", "MYCART");
        model.addAttribute("result", result);
        return "reception/user";
    }


}
