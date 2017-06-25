package com.feagle.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Feagle on 2017/6/12.
 */
public class AsyncServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Servlet执行开始时间：" + new Date());
        AsyncContext asyncContext = request.startAsync();
        new Thread(new Executor(asyncContext)).start();//开启异步线程
        System.out.println("Servlet执行结束时间：" + new Date());
    }

    class Executor implements Runnable {

        private AsyncContext asyncContext;

        public Executor(AsyncContext asyncContext) {
            this.asyncContext = asyncContext;
        }

        @Override
        public void run() {
            //执行相关复杂业务
            try {
                Thread.sleep(1000 * 10);
                //ServletRequest request = asyncContext.getRequest();
                //ServletResponse response = asyncContext.getResponse();
                System.out.println("业务执行完成时间:" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
