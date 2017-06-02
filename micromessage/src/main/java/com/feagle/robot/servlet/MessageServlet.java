package com.feagle.robot.servlet;

import com.feagle.robot.bean.Message;
import com.feagle.robot.servcie.MessageService;
import com.feagle.robot.servcie.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Feagle on 2017/6/1.
 */
public class MessageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //设置字符集，防止乱码
        req.setCharacterEncoding("UTF-8");
        //接收页面查询参数
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        //向页面传值
        req.setAttribute("command", command);
        req.setAttribute("description", description);
        //servlet调用service
        MessageService messageService = new MessageServiceImpl();
        List<Message> messageList = messageService.listMessage(command, description);
        req.setAttribute("messageList", messageList);
        //跳转
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.doGet(req, resp);
    }
}
