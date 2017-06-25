package com.feagle.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义拦截器
 * Created by Feagle on 2017/6/11.
 */
public class MyInterceptor1 implements HandlerInterceptor{
    //返回值，

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o 表示的是被拦截的请求的目标对象（InterceptorController）
     * @return 如果返回false,请求将被终止
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("执行了 preHandle1()...");
        return true;
    }

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView 可以通过modelAndview参数改变显示的视图，或修改发往视图的方法
     * @throws Exception
     */
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        Map<String, Object> model = modelAndView.getModel();
        Object msg = model.get("msg");
        System.out.println("从拦截器的postHandle方法中获取的视图数据msg:"+msg);
        model.put("msg","这是从拦截器的postHandle方法修改后的消息!");
        model.put("function","postHandle");
        modelAndView.addObject("clazz",this.getClass().getName());
//        modelAndView.setViewName("interceptor/hello2");
        System.out.println("执行了 postHandle1()...");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("执行了 afterCompletion1()...");

    }
}
