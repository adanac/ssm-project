package com.feagle.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Feagle on 2017/6/11.
 */
public class MyFilter2 implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init2()...");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter2()...开始");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter2()...结束");

    }

    public void destroy() {
        System.out.println("destroy2()...");
    }
}
