package com.feagle.servlet;

import com.feagle.service.ServletService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于测试异常过滤器，MyExceptionFilter
 * Created by Feagle on 2017/6/11.
 */
public class ExceptionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletService t = new ServletService();
        t.add();//模拟调用业务层方法，在此方法内抛出异常，此异常会在filter中进行捕获。
    }
}
