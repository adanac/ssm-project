package com.feagle.web;

import com.feagle.entity.User;
import com.feagle.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Feagle on 2017/6/13.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login1")
    public String login1(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("".equals(username) || "".equals(password) || null == username || null == password) {
            return "login/login1";
        }
        if (loginService.login(username, password)) {//登录成功
            User user = new User();
            user.setUsername(username);
            request.getSession().setAttribute("user", user);//将用户名放入session
            model.addAttribute("username", username);
            return "login/success";
        } else {
            return "login/fail1";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);//防止创建session

        if (session != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            session.removeAttribute("user");
        }

        return "login/logout1";
    }

    @RequestMapping(value = "/fail/{page}")
    public String fail1(@PathVariable("page") String page, HttpServletRequest request, Model model) {
        return "login/fail" + page;
    }

}
