package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义日志过滤器
 * Created by Feagle on 2017/6/13.
 */
@WebFilter(filterName = "myLogFilter", urlPatterns = {"/*"})
public class MyLogFilter implements Filter {

    //FilterConfig用于访问Filter的配置信息
    private FilterConfig filterConfig;

    /**
     * 实现初始化
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对用户请求执行预处理
        ServletContext servletContext = this.filterConfig.getServletContext();
        System.out.println("doFilter() in MyLogFilter...开始过滤...");
        String initIp = servletContext.getInitParameter("initIp");
        System.out.println("initParameter:" + initIp);
        long before = System.currentTimeMillis();
        //将请求转换成 HttpServletRequest 请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("doFilter()方法已截获到用户的请求地址：" + request.getServletPath());
        //FilterChain链式处理，将请求放行到目的地址
        filterChain.doFilter(servletRequest, servletResponse);
        //对服务器响应执行后处理
        long after = System.currentTimeMillis();
        System.out.println("请求被定位到：" + request.getRequestURI() + " ，所花时间：" + (after - before));
        System.out.println("doFilter() in MyLogFilter...过滤结束...");
    }

    /**
     * 实现销毁
     */
    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
