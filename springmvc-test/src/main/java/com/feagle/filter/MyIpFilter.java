package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义IP过滤器
 * Created by Feagle on 2017/6/12.
 */
@WebFilter(filterName = "ipFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "forbidIps", value = "127.0.0.1,192.168.1.1")})
public class MyIpFilter implements Filter {
    String[] forbidIps;//禁止请求的ip列表
    String[] excludePages;//需要排除的请求


    /**
     * 获取需要禁止访问的ip，及需要排除在外的url
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init() in MyIpFilter...");
        //获取在web.xml中配置的<filter>的初始化参数
        String forbidIpsParameter = filterConfig.getInitParameter("forbidIps");
        if (forbidIpsParameter != null) {
            forbidIps = forbidIpsParameter.split(",");
        }

        String excludePagesParameter = filterConfig.getInitParameter("excludePages");
        if (excludePagesParameter != null) {
            excludePages = excludePagesParameter.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter() in MyIpFilter...开始");
        boolean isExcludePage = false;
        //由于拦截器定义的拦截规范是/* ,所以所有的请求都会被拦截，为了防止死循环 开一个特例显示具体的访问结果
        if (((HttpServletRequest) servletRequest).getRequestURI().contains("forbid")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;//不加return 会继续执行请求
        }

        //验证是否是禁止ip
        String remoteAddr = servletRequest.getRemoteAddr();
        if (forbidIps != null) {
            for (int i = 0; i < forbidIps.length; i++) {
                if (remoteAddr.equals(forbidIps[i])) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/filter/forbid");//过滤此IP
                    return;
                }
            }
        }

        //验证是否是排除在外的url
        if (excludePages != null) {
            for (String page : excludePages) {
                if (((HttpServletRequest) servletRequest).getServletPath().equals(page)) {
                    isExcludePage = true;
                    break;
                }
            }
        }
        if (isExcludePage) {//是要排除的url
            filterChain.doFilter(servletRequest, servletResponse);
        }
        System.out.println("doFilter() in MyIpFilter...结束");
    }

    @Override
    public void destroy() {

    }
}
