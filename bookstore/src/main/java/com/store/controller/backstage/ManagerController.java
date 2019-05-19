package com.store.controller.backstage;

import com.store.pojo.*;
import com.store.service.*;
import com.store.utils.Msg;
import com.store.vo.BookInfoVo;
import com.store.vo.OrderVo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/Manager")
public class ManagerController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private BookTypeInfoService bookTypeInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ManagerService managerService;

    // 用户管理
    @RequestMapping("/UserManagement")
    public String userManagement(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null){
            List<CustomerInfo> customerInfos = customerInfoService.queryCustomerInfofoList();
            Msg result = Msg.success().add("customerInfos", customerInfos);
            result.add("option", "USERMANAGEMENT");
            result.add("action", "LOOK");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 跳转到修改用户信息
    @RequestMapping("/UserDetail")
    public String goToUserDetailInfo(@RequestParam("custId") String custId, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            CustomerInfo customerInfo = customerInfoService.queryCustomerInfoByCustId(Integer.parseInt(custId));
            Msg result = Msg.success().add("customerInfo", customerInfo);
            result.add("option", "USERMANAGEMENT");
            result.add("action", "UPDATE");
            result.add("isFirst", "YES");
            result.add("manager", manager);
            result.add("message", "NOTIP");
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 用户修改
    @RequestMapping("/UserUpdate")
    public String userUpdate(CustomerInfo customerInfo, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            CustomerInfo customerInfoOld = customerInfoService.queryCustomerInfoByCustId(customerInfo.getCustId());
            // 电话号码校验
            String  regex = "^1[3|4|5|8]\\d{9}$";
            if (!customerInfo.getCustTel().matches(regex)) {
                Msg result = Msg.success().add("customerInfo", customerInfoOld);
                result.add("option", "USERMANAGEMENT");
                result.add("action", "UPDATE");
                result.add("isFirst", "NO");
                result.add("manager", manager);
                result.add("message", "手机号码格式不正确");
                model.addAttribute("result", result);
                return "backstage/index";
            }

            // 邮箱校验
            regex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
            if (!customerInfo.getCustEmail().matches(regex)) {
                Msg result = Msg.success().add("customerInfo", customerInfo);
                result.add("option", "USERMANAGEMENT");
                result.add("action", "UPDATE");
                result.add("isFirst", "NO");
                result.add("manager", manager);
                result.add("message", "邮箱格式不正确");
                model.addAttribute("result", result);
                return "backstage/index";
            }
            
            customerInfoService.updateCustomerInfo(customerInfo);
            response.sendRedirect("/Manager/UserManagement");
            return null;
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 删除用户
    @RequestMapping("/UserDelete")
    public String userDelete(@RequestParam("custId") Integer custId, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            customerInfoService.deleteCustomerInfo(custId);
            response.sendRedirect("/Manager/UserManagement");
            return null;
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 图书信息展示
    @RequestMapping("/BookManagement")
    public String showBookInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            List<BookInfo> bookInfos = bookInfoService.queryBookInfoList();
            List<BookInfoVo> bookInfoVoList = new ArrayList<>();
            for (BookInfo bookinfo :
                    bookInfos) {
                BookInfoVo bookInfoVo = new BookInfoVo(bookinfo.getBookId(),
                        bookTypeInfoService.queryBookTypeInfoByBookId(bookinfo.getTypeId()).getBookTypeName(),
                        bookinfo.getBookName(), bookinfo.getBookPress(), bookinfo.getBookPubDate(), bookinfo.getBookAuthor(),
                        bookinfo.getBookPrice(), bookinfo.getBookDesc(), bookinfo.getBookPicture(), bookinfo.getBookStoreCount());
                bookInfoVoList.add(bookInfoVo);
            }
            Msg result = Msg.success().add("bookInfoVoList", bookInfoVoList);
            result.add("option", "BOOKMANAGEMENT");
            result.add("action", "LOOK");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 图书详细信息展示
    @RequestMapping("/BookDetail")
    public String showBookDetail(@RequestParam("bookId") String bookId, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            BookInfo bookinfo = bookInfoService.queryBookInfoByBookId(Integer.parseInt(bookId));
            BookInfoVo bookInfoVo = new BookInfoVo(bookinfo.getBookId(),
                    bookTypeInfoService.queryBookTypeInfoByBookId(bookinfo.getTypeId()).getBookTypeName(),
                    bookinfo.getBookName(), bookinfo.getBookPress(), bookinfo.getBookPubDate(), bookinfo.getBookAuthor(),
                    bookinfo.getBookPrice(), bookinfo.getBookDesc(), bookinfo.getBookPicture(), bookinfo.getBookStoreCount());
            Msg result = Msg.success().add("bookInfo", bookInfoVo);
            result.add("option", "BOOKMANAGEMENT");
            result.add("action", "DETAIL");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 跳转到图书信息修改
    @RequestMapping("/BookBeforeUpdate")
    public String goToBookInfoUpdate(@RequestParam("bookId") String bookId, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            BookInfo bookinfo = bookInfoService.queryBookInfoByBookId(Integer.parseInt(bookId));
            BookInfoVo bookInfoVo = new BookInfoVo(bookinfo.getBookId(),
                    bookTypeInfoService.queryBookTypeInfoByBookId(bookinfo.getTypeId()).getBookTypeName(),
                    bookinfo.getBookName(), bookinfo.getBookPress(), bookinfo.getBookPubDate(), bookinfo.getBookAuthor(),
                    bookinfo.getBookPrice(), bookinfo.getBookDesc(), bookinfo.getBookPicture(), bookinfo.getBookStoreCount());
//        List<BookTypeInfo> bookTypeInfos = bookTypeInfoService.queryBookTypeInfoList();
            Msg result = Msg.success().add("bookInfo", bookInfoVo);
//        result.add("bookTypeInfos", bookTypeInfos);
            result.add("option", "BOOKMANAGEMENT");
            result.add("message", "FIRST");
            result.add("action", "UPDATE");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 图书更新
    @RequestMapping("/BookUpdate")
    public String bookInfoUpdate(BookInfo bookInfo, @RequestParam("dateTime") String dateTime, @RequestParam("bookTypeName") String bookTypeName, @RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            result.add("option", "BOOKMANAGEMENT");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            BookInfoVo bookInfoVo = new BookInfoVo(bookInfo.getBookId(), bookTypeName,
                    bookInfo.getBookName(), bookInfo.getBookPress(), bookInfo.getBookPubDate(), bookInfo.getBookAuthor(),
                    bookInfo.getBookPrice(), bookInfo.getBookDesc(), bookInfo.getBookPicture(), bookInfo.getBookStoreCount());
            try {
                Date date = sdf.parse(dateTime);
                bookInfoVo.setBookPubDate(date);
                bookInfo.setBookPubDate(date);
            } catch (ParseException e) {
                result.add("action", "UPDATE");
                result.add("message", "时间格式有误");
                result.add("bookInfo", bookInfoVo);
                e.printStackTrace();
                result.add("manager", manager);
                model.addAttribute("result", result);
                return "backstage/index";
            }
            BookTypeInfo bookTypeInfo = bookTypeInfoService.queryBookTypeInfoByBookTypeName(bookTypeName);
            bookInfo.setTypeId(bookTypeInfo.getBookTypeId());
            if (!file.isEmpty()) {
                try {
                    // 删除之前的图片
                    BookInfo oldBookInfo = bookInfoService.queryBookInfoByExample(bookInfo.getBookName());
                    File oldFile = new File("E:\\我的毕业设计\\bookstore\\src\\main\\resources\\static" + oldBookInfo.getBookPicture());
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                    String imgName = bookInfo.getBookName() + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File("E:\\我的毕业设计\\bookstore\\src\\main\\resources\\static\\img\\book\\" + imgName + ".jpg")));//保存图片到目录下
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    String filename = "/img/book/" + imgName + ".jpg";
                    bookInfo.setBookPicture(filename);
                    bookInfoVo.setBookPicture(filename);
                    bookInfoService.updateBookInfo(bookInfo);
                    result.add("action", "DETAIL");
                    result.add("bookInfo", bookInfoVo);
                    result.add("manager", manager);
                    model.addAttribute("result", result);
                    return "backstage/index";
                } catch (Exception e) {
                    e.printStackTrace();
                    result.add("message", "上传失败");
                    result.add("action", "UPDATE");
                    result.add("bookInfo", bookInfoVo);
                    result.add("manager", manager);
                    model.addAttribute("result", result);
                    return "backstage/index";
                }
            } else {
                result.add("message", "上传失败，文件为空");
                result.add("action", "UPDATE");
                result.add("bookInfo", bookInfoVo);
                result.add("manager", manager);
                model.addAttribute("result", result);
                return "backstage/index";
            }
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 跳转到图书信息添加
    @RequestMapping("/BookBeforeInsert")
    public String goToBookInfoInsert(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            result.add("option", "BOOKMANAGEMENT");
            result.add("action", "INSERT");
            result.add("message", "FIRST");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 添加图书信息
    @RequestMapping("/BookInsert")
    public String bookInfoInsert(BookInfo bookInfo, @RequestParam("dateTime") String dateTime, @RequestParam("bookTypeName") String bookTypeName, @RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            result.add("option", "BOOKMANAGEMENT");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            BookInfoVo bookInfoVo = new BookInfoVo(null, bookTypeName,
                    bookInfo.getBookName(), bookInfo.getBookPress(), null, bookInfo.getBookAuthor(),
                    bookInfo.getBookPrice(), bookInfo.getBookDesc(), bookInfo.getBookPicture(), bookInfo.getBookStoreCount());
            try {
                Date date = sdf.parse(dateTime);
                bookInfoVo.setBookPubDate(date);
                bookInfo.setBookPubDate(date);
            } catch (ParseException e) {
                result.add("manager", manager);
                result.add("action", "INSERT");
                result.add("bookInfo", bookInfoVo);
                result.add("message", "时间格式有误");
                e.printStackTrace();
                model.addAttribute("result", result);
                return "backstage/index";
            }
            BookTypeInfo bookTypeInfo = bookTypeInfoService.queryBookTypeInfoByBookTypeName(bookTypeName);
            bookInfo.setTypeId(bookTypeInfo.getBookTypeId());
            if (!file.isEmpty()) {
                try {
                    String imgName = bookInfo.getBookName() + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File("E:\\我的毕业设计\\bookstore\\src\\main\\resources\\static\\img\\book\\" + imgName + ".jpg")));//保存图片到目录下
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    String filename = "/img/book/" + imgName + ".jpg";
                    bookInfo.setBookPicture(filename);
                    bookInfoVo.setBookPicture(filename);
                    if (bookInfoService.queryBookInfoByExample(bookInfo.getBookName()) == null) {
                        bookInfoService.saveBookInfo(bookInfo);
                        bookInfoVo.setBookId(bookInfoService.queryBookInfoByExample(bookInfo.getBookName()).getBookId());
                        result.add("action", "DETAIL");
                        result.add("bookInfo", bookInfoVo);
                        result.add("manager", manager);
                        model.addAttribute("result", result);
                        return "backstage/index";
                    } else {
                        result.add("message", "图书名已经存在");
                        result.add("action", "INSERT");
                        result.add("bookInfo", bookInfoVo);
                        result.add("manager", manager);
                        model.addAttribute("result", result);
                        return "backstage/index";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.add("bookInfo", bookInfoVo);
                    result.add("message", "上传失败");
                    result.add("action", "INSERT");
                    result.add("manager", manager);
                    model.addAttribute("result", result);
                    return "backstage/index";
                }
            } else {
                result.add("message", "上传失败，文件为空");
                result.add("action", "INSERT");
                result.add("bookInfo", bookInfoVo);
                result.add("manager", manager);
                model.addAttribute("result", result);
                return "backstage/index";
            }
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 图书信息删除
    @RequestMapping("/BookDelete")
    public String deleteBookInfo(@RequestParam("bookId") Integer bookId, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            BookInfo oldBookInfo = bookInfoService.queryBookInfoByBookId(bookId);
            bookInfoService.deleteBookInfo(bookId);
            File oldFile = new File("E:\\我的毕业设计\\bookstore\\src\\main\\resources\\static" + oldBookInfo.getBookPicture());
            if (oldFile.exists()) {
                oldFile.delete();
            }
            response.sendRedirect("/Manager/BookManagement");
            return null;
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 显示所有订单
    @RequestMapping("/OrderManagement")
    public String showOrderInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            List<Order> orders = orderService.queryOrderList();
            System.out.println(orders);
            List<OrderVo> orderVos = new ArrayList<>();
            if (orders != null && orders.size() > 0) {
                for (int i = 0; i < orders.size(); i++) {
                    CustomerInfo customerInfo = customerInfoService.queryCustomerInfoByCustId(orders.get(i).getCustomerId());
                    String custName;
                    if (customerInfo == null){
                        custName = "用户已删除";
                    }else{
                        custName = customerInfo.getCustName();
                    }
                    OrderDetail orderDetail = orderDetailService.queryOrderDetailByDetailId(orders.get(i).getOrderId());
                    BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(orderDetail.getbId());
                    String bookName;
                    String bookPicture;
                    if (bookInfo == null){
                        bookName = "图书已下架";
                        bookPicture = "/img/g1.jpg";
                    }else{
                        bookName = bookInfo.getBookName();
                        bookPicture = bookInfo.getBookPicture();
                    }
                    OrderVo ov = new OrderVo(orders.get(i).getId(), custName, bookName, orders.get(i).getOrderDate(), orders.get(i).getMessage(),
                            orders.get(i).getPostMethod(), orders.get(i).getPayMethod(), orders.get(i).getReceverName(), orders.get(i).getReceverAddr(),
                            orders.get(i).getReceverTel(), orders.get(i).getTotalPrice(), orderDetail.getOrderCount(), orderDetail.getPostStatus(),
                            orderDetail.getRecevStatus(), bookPicture);
                    System.out.println(ov);
                    orderVos.add(ov);
                }
                System.out.println(orderVos);
                result.add("orders", orderVos);
            } else {
                result.add("orders", "NOORDER");
            }
            result.add("option", "ORDERMANAGEMENT");
            result.add("action", "LOOK");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 查看订单详情
    @RequestMapping("/OrderDetail")
    public String showOrderDetail(@RequestParam("id") Integer id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            Order order = orderService.queryOrderByOrderId(id);
            CustomerInfo customerInfo = customerInfoService.queryCustomerInfoByCustId(order.getCustomerId());
            String custName;
            if (customerInfo == null){
                custName = "用户已删除";
            }else{
                custName = customerInfo.getCustName();
            }
            OrderDetail orderDetail = orderDetailService.queryOrderDetailByDetailId(order.getOrderId());
            BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(orderDetail.getbId());
            String bookName;
            String bookPicture;
            if (bookInfo == null){
                bookName = "图书已下架";
                bookPicture = "/img/g1.jpg";
            }else{
                bookName = bookInfo.getBookName();
                bookPicture = bookInfo.getBookPicture();
            }
            OrderVo ov = new OrderVo(order.getId(), custName, bookName, order.getOrderDate(), order.getMessage(),
                    order.getPostMethod(), order.getPayMethod(), order.getReceverName(), order.getReceverAddr(),
                    order.getReceverTel(), order.getTotalPrice(), orderDetail.getOrderCount(), orderDetail.getPostStatus(),
                    orderDetail.getRecevStatus(), bookPicture);
            result.add("order", ov);
            result.add("option", "ORDERMANAGEMENT");
            result.add("action", "DETAIL");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 删除订单
    @RequestMapping("/OrderDelete")
    public String deleteOrder(@RequestParam("id") Integer id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Order order = orderService.queryOrderByOrderId(id);
            orderDetailService.deleteOrderDetail(order.getOrderId());
            orderService.deleteOrder(id);
            response.sendRedirect("/Manager/OrderManagement");
            return null;
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 确认发货
    @RequestMapping("/ConfirmShipment")
    public String confirmShipment(@RequestParam("id") Integer id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            Order order = orderService.queryOrderByOrderId(id);
            CustomerInfo customerInfo = customerInfoService.queryCustomerInfoByCustId(order.getCustomerId());
            String custName;
            if (customerInfo == null){
                custName = "用户已删除";
            }else{
                custName = customerInfo.getCustName();
            }
            OrderDetail orderDetail = orderDetailService.queryOrderDetailByDetailId(order.getOrderId());
            BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(orderDetail.getbId());
            String bookName;
            String bookPicture;
            if (bookInfo == null){
                bookName = "图书已下架";
                bookPicture = "/img/g1.jpg";
            }else{
                bookName = bookInfo.getBookName();
                bookPicture = bookInfo.getBookPicture();
            }
            orderDetail.setPostStatus(true);
            orderDetailService.updateOrderDetail(orderDetail);
            OrderVo ov = new OrderVo(order.getId(), custName, bookName, order.getOrderDate(), order.getMessage(),
                    order.getPostMethod(), order.getPayMethod(), order.getReceverName(), order.getReceverAddr(),
                    order.getReceverTel(), order.getTotalPrice(), orderDetail.getOrderCount(), orderDetail.getPostStatus(),
                    orderDetail.getRecevStatus(), bookPicture);
            result.add("order", ov);
            result.add("option", "ORDERMANAGEMENT");
            result.add("action", "DETAIL");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }

    // 确认收货
    @RequestMapping("/ConfirmReceipt")
    public String confirmReceipt(@RequestParam("id") Integer id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null) {
            Msg result = Msg.success();
            Order order = orderService.queryOrderByOrderId(id);
            CustomerInfo customerInfo = customerInfoService.queryCustomerInfoByCustId(order.getCustomerId());
            String custName;
            if (customerInfo == null){
                custName = "用户已删除";
            }else{
                custName = customerInfo.getCustName();
            }
            OrderDetail orderDetail = orderDetailService.queryOrderDetailByDetailId(order.getOrderId());
            BookInfo bookInfo = bookInfoService.queryBookInfoByBookId(orderDetail.getbId());
            String bookName;
            String bookPicture;
            if (bookInfo == null){
                bookName = "图书已下架";
                bookPicture = "/img/g1.jpg";
            }else{
                bookName = bookInfo.getBookName();
                bookPicture = bookInfo.getBookPicture();
            }
            orderDetail.setRecevStatus(true);
            orderDetailService.updateOrderDetail(orderDetail);
            OrderVo ov = new OrderVo(order.getId(), custName, bookName, order.getOrderDate(), order.getMessage(),
                    order.getPostMethod(), order.getPayMethod(), order.getReceverName(), order.getReceverAddr(),
                    order.getReceverTel(), order.getTotalPrice(), orderDetail.getOrderCount(), orderDetail.getPostStatus(),
                    orderDetail.getRecevStatus(), bookPicture);
            result.add("order", ov);
            result.add("option", "ORDERMANAGEMENT");
            result.add("action", "DETAIL");
            result.add("manager", manager);
            model.addAttribute("result", result);
            return "backstage/index";
        }else{
            response.sendRedirect("/manager/login");
            return null;
        }
    }
    
}
