package com.store.utils;

import com.store.pojo.CustomerInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Common {
    
    public static CustomerInfo checkUserIsLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerInfo user = (CustomerInfo) request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }else {
            return user;
        }
    }
    
}
