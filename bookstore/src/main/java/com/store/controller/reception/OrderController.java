package com.store.controller.reception;

import com.store.pojo.*;
import com.store.service.*;
import com.store.utils.Common;
import com.store.utils.Msg;
import com.store.vo.AddressVo;
import com.store.vo.GoodsCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderDetailService detailService;

    @Autowired
    private BookTypeInfoService bookTypeInfoService;

    // 支付
    @RequestMapping(value = "/settlement", method = RequestMethod.POST)
    public String cartSettlement(AddressVo addressVo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        if (user == null) {
//            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
//            request.getRequestDispatcher("/customer/signIn").forward(request,response);
            response.sendRedirect("/bookstore/querypaged");
            return null;
        }

        // 地址信息不详细
        if (Objects.equals(addressVo.getReceverName(), "")
                || Objects.equals(addressVo.getReceverTel(), "")
                || Objects.equals(addressVo.getReceverAddr(), "")) {
            Msg result = Msg.fail().add("messageTip", "请填写全收货信息")
                    .add("addressVo", addressVo)
                    .add("message", "NOTIP");
            // 获取所有图书类型
            List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
            result.add("types", bookTypeInfos)
                    .add("userName", user.getCustName())
                    .add("typeId", "ALL");
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
                result.add("goodsList", "NOGOODS");
            }
            result.add("center", "MYCART");
            result.add("user", user);
            model.addAttribute("result", result);
            return "reception/user";
        }

        // 收货人电话不合法
        // 电话号码校验
        String regex = "^1[3|4|5|8]\\d{9}$";
        if (!addressVo.getReceverTel().matches(regex)) {
            Msg result = Msg.fail().add("message", "手机号码格式不正确")
                    .add("addressVo", addressVo);
            // 获取所有图书类型
            List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
            result.add("types", bookTypeInfos)
                    .add("userName", user.getCustName())
                    .add("typeId", "ALL");
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
                result.add("goodsList", "NOGOODS");
            }
            result.add("center", "MYCART");
            result.add("user", user);
            result.add("messageTip", "NOTIP");
            model.addAttribute("result", result);
            return "reception/user";
        }

        String[] goodsIds = request.getParameterValues("goodsIds");
        if (goodsIds == null) {
            Msg result = Msg.fail().add("messageTip", "你还没有选择要支付商品")
                    .add("addressVo", addressVo);
            // 获取所有图书类型
            List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
            result.add("types", bookTypeInfos)
                    .add("userName", user.getCustName())
                    .add("typeId", "ALL");
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
                result.add("goodsList", "NOGOODS");
            }
            result.add("center", "MYCART");
            result.add("user", user);
            result.add("message", "NOTIP");
            model.addAttribute("result", result);
            return "reception/user";
        }
        Double sumPrice = 0.0;
        Msg result = Msg.success();
        //生成订单信息
        for (String goodsId :
                goodsIds) {
            System.out.println(goodsId);
            Goods goods = goodsService.queryGoodsByGoodsId(Integer.parseInt(goodsId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String uniqueIdentifier = sdf.format(new Date()) + goods.toString();
            // 生成详细订单
            OrderDetail orderDetail = new OrderDetail(null, uniqueIdentifier, goods.getBookinfoId(), goods.getCount(), false, false, goods.getTotalPrice());
            // 保存详细订单
            detailService.saveOrderDetail(orderDetail);
            OrderDetail detail = detailService.queryOrderDetailByUniqueIdentifier(uniqueIdentifier);
            // 生成主订单
            Order order = new Order(null, detail.getDetailId(), goods.getCustomerId(), new Date(), addressVo.getMessage(), "顺丰快递", "微信", addressVo.getReceverName(), addressVo.getReceverAddr(), addressVo.getReceverTel(), goods.getTotalPrice());
            // 保存订单
            orderService.saveOrder(order);
            // 从购物车中删除这条记录
            goodsService.deleteGoods(Integer.parseInt(goodsId));
            // 修改图书的库存
            Integer count = goods.getCount();
            BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(goods.getBookinfoId());
            Integer bookStoreCount = bookInfo.getBookStoreCount();
            if (bookStoreCount <= count) {
                bookInfo.setBookStoreCount(0);
                bookInfoService.updateBookInfo(bookInfo);
            } else {
                bookInfo.setBookStoreCount(bookStoreCount - count);
                bookInfoService.updateBookInfo(bookInfo);
            }
            // 显示总价
            sumPrice += goods.getTotalPrice();
        }
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
        result.add("types", bookTypeInfos)
                .add("userName", user.getCustName())
                .add("typeId", "ALL");
        result.add("sumPrice", sumPrice);
        result.add("user", user);
        result.add("messageTip", "NOTIP");
        result.add("message", "NOTIP");
        model.addAttribute("result", result);
        return "reception/payment";
    }

}
