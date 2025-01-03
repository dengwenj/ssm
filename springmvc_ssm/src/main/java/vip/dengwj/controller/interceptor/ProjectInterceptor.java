package vip.dengwj.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date 2024/12/22 22:48
 * @author 朴睦
 * @description 拦截器
 */
@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");
        String contentType = request.getHeader("Content-Type");
        System.out.println("contentType -> " + contentType);
        System.out.println(handler);
        System.out.println(handler.getClass());
        HandlerMethod hm = (HandlerMethod) handler;
        String name = hm.getMethod().getName();
        System.out.println("method name -> " + name);
        // 返回 false 就不忘后面执行了
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
