package com.feagle.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 拦截器Controller
 * Created by Feagle on 2017/6/11.
 */
@Controller
@RequestMapping("/")
public class InterceptorController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        return "interceptor/login";
    }

    @RequestMapping("/view")
    public String view(HttpServletRequest request, Model model){

        System.out.println("进入到view()....");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        model.addAttribute("loginName",username);
        model.addAttribute("lognpwd",password);
        System.out.println("username="+username+",password="+password);
        model.addAttribute("msg","从view方法传回视图的数据!");
        return "interceptor/hello1";
    }
    @RequestMapping("/query")
    public void query(HttpServletRequest request, Model model){
        System.out.println("InterceptorController.query()...");
    }
}
