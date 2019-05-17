package com.ironass.concurrent.threadlocal.context;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author lixin
 * @date 2019-05-16 22:21
 **/
public class ContextInterceptor extends HandlerInterceptorAdapter {

    public ContextInterceptor() {
        super();
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle: ");
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandler: ");
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("aferCompletion： ");
//        super.afterCompletion(request, response, handler, ex);
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("afterConcurrentHandlingStarted: ");
//        super.afterConcurrentHandlingStarted(request, response, handler);
//    }
}
