package vip.dengwj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import vip.dengwj.controller.interceptor.ProjectInterceptor;
import vip.dengwj.controller.interceptor.ProjectInterceptor2;
import vip.dengwj.controller.interceptor.ProjectInterceptor3;

@Configuration
public class SpringMVCSupport extends WebMvcConfigurationSupport {
    @Autowired
    ProjectInterceptor projectInterceptor;
    @Autowired
    ProjectInterceptor2 projectInterceptor2;
    @Autowired
    ProjectInterceptor3 projectInterceptor3;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 当路径为 /books 或 /books/???，会走拦截器
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books", "books/*");
        registry.addInterceptor(projectInterceptor2).addPathPatterns("/books", "books/*");
        registry.addInterceptor(projectInterceptor3).addPathPatterns("/books", "books/*");
    }
}
