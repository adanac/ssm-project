package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义权限过滤器
 * Created by Feagle on 2017/6/13.
 */
@WebFilter(filterName = "myAuthorityFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "loginPage", value = "/login.jsp"), @WebInitParam(name = "proLogin", value = "/proLogin.jsp")})
public class MyAuthorityFilter implements Filter {
    //FilterConfig可用于访问Filter的配置信息
    private FilterConfig filterConfig;


    /**
     * 实现初始化方法
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * 执行过滤的核心方法
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取此filter的配置参数
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String encoding = filterConfig.getInitParameter("encoding");
        request.setCharacterEncoding(encoding);
        String loginPage = filterConfig.getInitParameter("loginPage");//需要过滤的登录页面，也可通过配置不过滤的初始化参数实现

        HttpSession session = request.getSession();
        //获取客户请求的页面
        String servletPath = request.getServletPath();
        //如果session范围的user为null，即表明没有登录
        //且用户请求的既不是登录页面，也不是处理登录的页面
        if (null == session
                || session.getAttribute("user") == null
                && !servletPath.endsWith(loginPage)) {
            //forward到登录页面
            request.setAttribute("tip", "您还没有登录");
            request.getRequestDispatcher("/login/login1").forward(servletRequest, servletResponse);
        } else {
            //放行请求
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * 实现销毁方法
     */
    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
