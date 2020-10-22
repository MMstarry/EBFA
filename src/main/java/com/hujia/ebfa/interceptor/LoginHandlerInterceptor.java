package com.hujia.ebfa.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bin.lee
 * @date 2020/10/21 0021 15:32
 * @Email: libinjava@163.com
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        Object user = request.getSession().getAttribute("userName");
//        System.out.println("afterCompletion----" + user + " ::: " + request.getRequestURL());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        Object user = request.getSession().getAttribute("userName");
//        System.out.println("postHandle----" + user + " ::: " + request.getRequestURL());

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        System.out.println("preHandle----" + user + " ::: " + request.getRequestURL());

        if (user == null) {
            request.setAttribute("msg", "无权限请先登录");
            // 获取request返回页面到登录页
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        return true;
    }


}
