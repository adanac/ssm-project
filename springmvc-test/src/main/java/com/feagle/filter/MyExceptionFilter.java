package com.feagle.filter;

import com.feagle.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feagle on 2017/6/11.
 */
public class MyExceptionFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String errorPage;//跳转的错误信息页面

    //初始化读取你配置的提示页面路径
    public void init(FilterConfig filterConfig) throws ServletException {
        //读取错误信息提示页面路径
        String errorPage = filterConfig.getInitParameter("errorPage");
        if (null == errorPage || "".equals(errorPage.trim())) {
            throw new RuntimeException("没有配置错误信息跳转页面，请在web.xml中进行配置\n<init-param>\\n<param-name>errorPage</param-name>\\n<param-value>/error.jsp</param-value>\\n </init-param>\\n路径可以是你自己设定的任何有效路径页面！！");
            //System.out.println("没有配置错误信息跳转页面");
        }


    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter() in MyExceptionFilter...开始");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            if (e instanceof MyException) {//如果是自定义的业务异常
                request.setAttribute("MyExceptionInfo",e);//存储业务异常信息类
                request.getRequestDispatcher(errorPage).forward(request,response);//跳转到错误信息提示页面
            }
        }finally {
            System.out.println("doFilter() in MyExceptionFilter...结束");
        }
    }

    public void destroy() {

    }
}
