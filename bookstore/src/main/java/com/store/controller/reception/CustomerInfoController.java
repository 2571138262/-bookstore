package com.store.controller.reception;

import com.store.pojo.*;
import com.store.service.*;
import com.store.utils.Common;
import com.store.utils.Msg;
import com.store.vo.CustOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/customer")
public class CustomerInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BookTypeInfoService bookTypeInfoServic;

    // 用户注册
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@Valid CustomerInfo customerInfo, String custPwdConfirm, HttpServletRequest request, HttpServletResponse response, Model model, BindingResult bindingResult) throws IOException, ServletException {
        // 用户名检验
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!customerInfo.getCustName().matches(regex)) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "用户名必须为2-5为中文或者是6-16位英文和数字的组合");
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }

        // 用户名是否存在
        if (customerInfoService.queryCustomerIsExists(customerInfo)) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "当前用户已经存在");
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }

        // 密码检验
        regex = "^.{8,}$";
        if (!customerInfo.getCustPwd().matches(regex)) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "密码长度至少8位");
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }
        // 俩次密码不一样
        if (!Objects.equals(customerInfo.getCustPwd(), custPwdConfirm)) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "俩次密码不一致");
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }

        // 电话号码校验
        regex = "^1[3|4|5|8]\\d{9}$";
        if (!customerInfo.getCustTel().matches(regex)) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "手机号码格式不正确");
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }

        // 邮箱校验
        regex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        if (!customerInfo.getCustEmail().matches(regex)) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "邮箱格式不正确");
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }

        try {
            customerInfo.setCustRegTime(new Date());
            customerInfoService.saveCustomerInfo(customerInfo);
            Msg result = Msg.success().add("userLogin", customerInfo);
            return getSignInPage(model, result, "YES");
        } catch (Exception e) {
            e.printStackTrace();
            Msg result = Msg.fail().add("message", "添加失败").add("userLogin", customerInfo);
            result.add("isFirst", "NO");
            // 获取所有图书类型
            return getSignUpPage(model, result);
        }

//        try {
//            if (!Objects.equals(customerInfo.getCustPwd(), "")){
//                if (Objects.equals(customerInfo.getCustPwd(), custPwdConfirm)) {
//                    if (!customerInfoService.queryCustomerIsExists(customerInfo)) {
//                        customerInfo.setCustRegTime(new Date());
//                        customerInfoService.saveCustomerInfo(customerInfo);
//                        Msg result = Msg.success().add("userLogin", customerInfo);
//                        return getSignInPage(model, result, "YES");
//                    } else {
//                        Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "当前用户已经存在");
//                        result.add("isFirst", "NO");
//                        // 获取所有图书类型
//                        return getSignUpPage(model, result);
//                    }
//                } else {
//                    Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "俩次密码不一致");
//                    result.add("isFirst", "NO");
//                    // 获取所有图书类型
//                    return getSignUpPage(model, result);
//                }
//            }else{
//                Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "密码不能为空");
//                result.add("isFirst", "NO");
//                // 获取所有图书类型
//                return getSignUpPage(model, result);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Msg result = Msg.fail().add("message", "添加失败").add("userLogin", customerInfo);
//            result.add("isFirst", "NO");
//            // 获取所有图书类型
//            return getSignUpPage(model, result);
//        }
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public String signIn(CustomerInfo customerInfo, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {
        String custName = customerInfo.getCustName();
        CustomerInfo user = customerInfoService.queryCustomerInfoByCustName(custName);
        if (user != null && Objects.equals(customerInfo.getCustPwd(), user.getCustPwd())) {
            user.setCustLastLogin(new Date());
            customerInfoService.updateCustomerInfo(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/bookstore/querypaged");
            return null;
        } else if (user == null) {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "该用户不存在,请先注册");
            return getSignInPage(model, result, "NO");
        } else {
            Msg result = Msg.fail().add("userLogin", customerInfo).add("message", "密码错误请重新输入");
            return getSignInPage(model, result, "NO");
        }
    }

    private String getSignInPage(Model model, Msg result, String no) {
        result.add("isFirst", no);
        // 获取所有图书类型
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoServic.queryBookTypeInfoList();
        result.add("types", bookTypeInfos)
                .add("user", "NOUSER")
                .add("typeId", "ALL");
        model.addAttribute("result", result);
        return "reception/signIn";
    }

    // 查看个人信息
    @RequestMapping("/userInfo")
    public String selectUserInfo(@RequestParam(value = "action", defaultValue = "LOOK") String action, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        if (user != null) {
            Msg result = getUserInfoMessage(user);
            result.add("action", action);
            result.add("message", "NOTIP");
            model.addAttribute("result", result);
            return "reception/user";
        } else {
//            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
//            request.getRequestDispatcher("/customer/signIn").forward(request, response);
//            return null;
            response.sendRedirect("/bookstore/querypaged");
            return null;
        }
    }

    // 修改个人信息
    @RequestMapping("/userInfoUpdate")
    public String updateUserInfo(CustomerInfo customerInfo, String custPwdConfirm, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        if (user != null) {

            // 密码检验
            String regex = "^.{8,}$";
            if (!customerInfo.getCustPwd().matches(regex)) {
                Msg result = getUserInfoMessage(user);
                result.add("message", "密码长度至少8位");
                result.add("action", "UPDATE");
                model.addAttribute("result", result);
                return "reception/user";
            }
            // 俩次密码不一样
            if (!Objects.equals(customerInfo.getCustPwd(), custPwdConfirm)) {
                Msg result = getUserInfoMessage(user);
                result.add("message", "俩次密码不一致");
                result.add("action", "UPDATE");
                model.addAttribute("result", result);
                return "reception/user";
            }

            // 电话号码校验
            regex = "^1[3|4|5|8]\\d{9}$";
            if (!customerInfo.getCustTel().matches(regex)) {
                Msg result = getUserInfoMessage(user);
                result.add("message", "手机号码格式不正确");
                result.add("action", "UPDATE");
                model.addAttribute("result", result);
                return "reception/user";
            }

            // 邮箱校验
            regex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
            if (!customerInfo.getCustEmail().matches(regex)) {
                Msg result = getUserInfoMessage(user);
                result.add("message", "邮箱格式不正确");
                result.add("action", "UPDATE");
                model.addAttribute("result", result);
                return "reception/user";
            }
            
            customerInfo.setCustId(user.getCustId());
            customerInfo.setCustName(user.getCustName());
            customerInfoService.updateCustomerInfo(customerInfo);
            CustomerInfo newUser = customerInfoService.queryCustomerInfoByCustName(customerInfo.getCustName());
            request.getSession().setAttribute("user", newUser);
            Msg result = getUserInfoMessage(newUser);
            result.add("action", "LOOK");
            model.addAttribute("result", result);
            return "reception/user";
        } else {
//            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
//            request.getRequestDispatcher("/customer/signIn").forward(request, response);
//            return null;
            response.sendRedirect("/bookstore/querypaged");
            return null;
        }
    }

    private Msg getUserInfoMessage(CustomerInfo user) {
        Msg result = Msg.success();
        // 获取所有图书类型
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoServic.queryBookTypeInfoList();
        result.add("types", bookTypeInfos)
                .add("userName", user.getCustName())
                .add("typeId", "ALL");
        result.add("center", "MYINFO");
        result.add("user", user);
        return result;
    }

    // 查看我的订单
    @RequestMapping("/queryuserorder")
    public String queryCustomerOrder(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = Common.checkUserIsLogin(request, response);
        if (user != null) {
            //获取用户所有订单
            List<Order> orders = orderService.queryOrderListByCustomerId(user.getCustId());
            List<CustOrderVo> custOrderVos = new ArrayList<>();
            Msg result = Msg.success();
            // 获取所有图书类型
            List<BookTypeInfo> bookTypeInfos = bookTypeInfoServic.queryBookTypeInfoList();
            result.add("types", bookTypeInfos)
                    .add("userName", user.getCustName())
                    .add("typeId", "ALL");
            result.add("center", "MYORDER");
            result.add("user", user);
            if (orders != null && orders.size() > 0) {
                for (Order order :
                        orders) {
                    // 通过order.orderId查询订单详情信息
                    OrderDetail orderDetail = orderDetailService.queryOrderDetailByDetailId(order.getId());
                    // 根据订单详情中的bId查询出对应的图书信息
                    BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(orderDetail.getbId());
                    String bookName;
                    if (bookInfo == null) {
                        bookName = "图书已下架";
                    } else {
                        bookName = bookInfo.getBookName();
                    }
                    CustOrderVo cv = new CustOrderVo(order.getId(), bookName, orderDetail.getOrderCount(), order.getTotalPrice(), order.getReceverName(), order.getReceverAddr(), order.getReceverTel(), order.getOrderDate(), orderDetail.getPostStatus(), orderDetail.getRecevStatus());
                    custOrderVos.add(cv);
                }
                result.add("custOrderVos", custOrderVos);
                model.addAttribute("result", result);
                return "reception/user";
            } else {
                result.add("message", "您还没有订单，快去下单吧");
                result.add("custOrderVos", "NOORDER");
                result.add("user", user);
                model.addAttribute("result", result);
                return "reception/user";
            }
        } else {
//            request.setAttribute("message", Msg.fail().add("message", "您还没有登录，请先登录!"));
//            request.getRequestDispatcher("/customer/signIn").forward(request, response);
//            return null;
            response.sendRedirect("/bookstore/querypaged");
            return null;
        }
    }

    @RequestMapping(value = "/signUp")
    public String signUp(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Msg result = Msg.success();
        result.add("isFirst", "YES");
        // 获取所有图书类型
        return getSignUpPage(model, result);
    }

    private String getSignUpPage(Model model, Msg result) {
        List<BookTypeInfo> bookTypeInfos = bookTypeInfoServic.queryBookTypeInfoList();
        result.add("types", bookTypeInfos)
                .add("user", "NOUSER")
                .add("typeId", "ALL");
        model.addAttribute("result", result);
        return "reception/signUp";
    }

    @RequestMapping(value = "/signIn")
    public String signIn(Model model) {
        Msg result = Msg.success();
        return getSignInPage(model, result, "YES");
    }

    // 顾客确认收货
    @RequestMapping("/ConfirmReceipt")
    public String confirmReceipt(@RequestParam("id") Integer id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order order = orderService.queryOrderByOrderId(id);
        OrderDetail orderDetail = orderDetailService.queryOrderDetailByDetailId(order.getOrderId());
        orderDetail.setRecevStatus(true);
        orderDetailService.updateOrderDetail(orderDetail);
        response.sendRedirect("/customer/queryuserorder");
        return null;
    }

    // 用户退出
    @RequestMapping("/userSignOut")
    public String userSignOut(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo customerInfo = Common.checkUserIsLogin(request, response);
        if (customerInfo != null) {
            customerInfo = null;
        }
        request.getSession().setAttribute("user", customerInfo);
        response.sendRedirect("/customer/signIn");
        return null;
    }

}
