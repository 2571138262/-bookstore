package com.store.controller.backstage;

import com.store.pojo.Manager;
import com.store.service.ManagerService;
import com.store.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/manager")
public class LoginController {
    
    @Autowired
    private ManagerService managerService;

    // 跳转到管理员登录界面
    @RequestMapping("/login")
    public String login(Model model) {
        Msg result = Msg.success().add("state", "FIRST");
        model.addAttribute("result", result);
        return "backstage/login";
    }

    // 管理员登录验证
    @RequestMapping("/validateLogon")
    public String validateLogon(Manager manager, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Msg result = Msg.fail();
        if (manager.getAdminName() != null) {
            Manager manager1 = managerService.queryManagerByAdminName(manager.getAdminName());
            if (manager1 != null) {
                if (Objects.equals(manager.getAdminPwd(), manager1.getAdminPwd())) {
                    request.getSession().setAttribute("manager", manager1);
                    response.sendRedirect("/Manager/UserManagement");
                    return null;
                } else {
                    result.add("state", "ERROR");
                    result.add("message", "密码有误");
                }
            } else {
                result.add("state", "ERROR");
                result.add("message", "用户不存在");
            }
        } else {
            result.add("state", "ERROR");
            result.add("message", "用户名为空");
        }
        model.addAttribute("result", result);
        return "backstage/login";
    }
    
    
    // 管理员退出
    @RequestMapping("/signout")
    public String managerSignOut(Model model, HttpServletRequest request, HttpServletResponse response){
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        if (manager != null){
            manager = null;    
            request.getSession().setAttribute("manager", manager);
        }
        Msg result = Msg.success().add("state", "FIRST");
        model.addAttribute("result", result);
        return "backstage/login";
    }
}
