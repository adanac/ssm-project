package com.feagle.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义编码过滤器
 * Created by Feagle on 2017/6/14.
 */
public class MyCharacterEncodingFilter implements Filter {

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String charset = filterConfig.getInitParameter("charset");
        if(charset == null){
            charset = "UTF-8";
        }
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
