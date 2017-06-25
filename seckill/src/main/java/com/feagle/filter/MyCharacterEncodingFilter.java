package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于注解的，支持异步的过滤器
 * Created by Feagle on 2017/6/12.
 */
@WebFilter(filterName = "CharacterFilter", urlPatterns = "/*")
public class MyCharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init() in MyCharacterEncodingFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("doFilter() in MyCharacterEncodingFilter...begin");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter() in MyCharacterEncodingFilter...end");
    }

    @Override
    public void destroy() {
        System.out.println("destroy() in MyCharacterEncodingFilter...");
    }
}
