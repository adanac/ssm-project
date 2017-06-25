package com.feagle.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 自定义编码拦截器
 * Created by Feagle on 2017/6/11.
 */
public class MyCharacterEncodingInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String characterEncoding = request.getCharacterEncoding();
        String characterEncoding1 = response.getCharacterEncoding();
        System.out.println("characterEncoding:" + characterEncoding);
        try {
            if (null == characterEncoding) {
                request.setCharacterEncoding("UTF-8");//对请求设置编码
            }
            if (null == characterEncoding1) {
                response.setCharacterEncoding("UTF-8");//对响应设置编码
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
