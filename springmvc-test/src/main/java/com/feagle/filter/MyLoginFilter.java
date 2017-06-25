package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义用户登录过滤器
 * Created by Feagle on 2017/6/14.
 */
public class MyLoginFilter implements Filter {
    private FilterConfig filterConfig;
    String[] urlPaths;//需放行的urls

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if(request.getRequestURI().contains("login")){

        }

        String noLoginPaths = filterConfig.getInitParameter("noLoginPaths");
        if (noLoginPaths != null) {
            urlPaths = noLoginPaths.split(",");
            for (int i = 0; i < urlPaths.length; i++) {
                if (urlPaths[i] == null || "".equals(urlPaths[i]))
                    continue;
                if (request.getServletPath().endsWith(urlPaths[i])) {//如果是noLoginPath，放行
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
