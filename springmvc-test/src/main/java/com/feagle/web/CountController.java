package com.feagle.web;

/**
 * Created by Feagle on 2017/6/9.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class CountController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "listener/login";

    }

    @RequestMapping(value = "/showOnline")
    public String showOnline(HttpServletRequest request, HttpServletResponse response){
        return "listener/showOnline";

    }

    @RequestMapping(value = "/isOnline")
    public String isOnline(HttpServletRequest request, HttpServletResponse response){
        return "listener/isOnline";

    }
}
