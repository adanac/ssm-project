package com.feagle.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

/**
 * Created by Feagle on 2017/6/9.
 */
public class OnlineListener implements HttpSessionListener, HttpSessionAttributeListener {
    // 在session中添加对象时触发此操作，在list中添加一个对象
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        list.add((String) httpSessionBindingEvent.getValue());
        sc.setAttribute("list", list);
    }

    // 修改、删除session中添加对象时触发此操作
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    // 参数
    ServletContext sc;
    ArrayList list = new ArrayList();

    // 新建一个session时触发此操作
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sc = httpSessionEvent.getSession().getServletContext();
        System.out.println("新建一个session");
    }

    // 销毁一个session时触发此操作
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("销毁一个session");
        if (!list.isEmpty()) {
            list.remove((String) httpSessionEvent.getSession().getAttribute("userName"));
            sc.setAttribute("list", list);
        }
    }
}
