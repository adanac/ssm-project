package com.feagle.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器
 * Created by Feagle on 2017/6/11.
 */
public class MyFilter1 implements Filter {
    /**
     * 过滤器的初始化方法，web容器创建过滤器实例后将调用这个方法，此方法中可读取web.xml文件中过滤器的参数
     * @param filterConfig
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init1()...");
    }

    /**
     * 完成实际的过滤操作，是过滤器的核心方法，当用户请求访问与过滤器关联的URL时，web容器将先调用过滤器的doFilter()方法。
     * FilterChain参数可调用chain.doFilter()方法，将请求传给下一个过滤器（或目标资源），或利用转发、重定向将请求转发到其他资源。
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter1()...开始");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("doFilter1()...结束");
    }

    /**
     * Web容器在销毁过滤器实例前将调用此方法，在此方法中可以释放过滤器占用的资源。（大多数情况用不到）
     */
    public void destroy() {
        System.out.println("destroy1()...");

    }
}
