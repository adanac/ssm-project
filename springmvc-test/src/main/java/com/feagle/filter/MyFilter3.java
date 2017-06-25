package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feagle on 2017/6/11.
 */
public class MyFilter3 implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter3()...开始");
        //filterChain.doFilter(servletRequest,servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //重定向
        //response.sendRedirect(request.getContextPath()+"/filter/second");
        //转发
        request.getRequestDispatcher("/filter/second").forward(request,response);
        System.out.println("doFilter3()...结束");
    }

    public void destroy() {

    }
}
