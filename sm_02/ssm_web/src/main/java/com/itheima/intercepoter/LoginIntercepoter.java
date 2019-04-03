package com.itheima.intercepoter;

import com.itheima.domain.User;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginIntercepoter implements HandlerInterceptor {//登录的拦截器

    /**
     * 方法执行之前执行
     * @param request
     * @param response
     * @param handler
     * @return  true--放行   false--不放行
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("执行拦截操作"+handler);
        if(handler instanceof DefaultServletHttpRequestHandler){
            return true;
        }
        //在什么情况下可以继续访问：session不为空
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            return true;
        }
        try {
            //request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 方法执行之后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("方法之后执行");
    }
}
