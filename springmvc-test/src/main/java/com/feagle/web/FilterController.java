package com.feagle.web;

import com.feagle.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Feagle on 2017/6/11.
 */
@Controller
@RequestMapping("/filter")
public class FilterController {

    @RequestMapping("/first")
    public String first(HttpServletRequest request, Model model) {
        return "filter/first";
    }

    @RequestMapping("/second")
    public String second(HttpServletRequest request, Model model) {
        return "filter/second";
    }

    /**
     * 测试异常过滤器，捕捉后跳转到错误信息提示页面
     *
     * @param request
     * @param model
     */
    @RequestMapping("/exception")
    public void doWork(HttpServletRequest request, Model model){
        System.out.println("in doWork()...");
        if (true)
            throw new MyException("in doWork() throws Exception");//业务逻辑，抛出异常
    }

    @RequestMapping("/async")
    public String async(Model model){
        model.addAttribute("msgInfo","基于@WebFilter注解的，支持异步的过滤器");
        return "filter/async";
    }

    @RequestMapping("/forbid")
    public String forbid(HttpServletRequest request,Model model){
        model.addAttribute("visitIp",request.getRemoteAddr());
        return "filter/forbid";
    }
}
