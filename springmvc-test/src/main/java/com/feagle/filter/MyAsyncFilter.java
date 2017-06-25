package com.feagle.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 基于注解的，异步支持的过滤器
 * Created by Feagle on 2017/6/12.
 */
@WebFilter(filterName = "myAsyncFilter", urlPatterns = {"/servlet/AsyncServlet", "/servlet/asyncServlet"}, asyncSupported = true, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ASYNC})
public class MyAsyncFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter() in MyAsyncFilter...begin");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("doFilter() in MyAsyncFilter...end");
    }

    public void destroy() {

    }
}
