package vip.dengwj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// 这里写 Component 或 Configuration 都可以加载到
//@Component
@Configuration
public class SpringMVCSupport extends WebMvcConfigurationSupport {
    // 设置对静态资源的访问放行，默认 SpringMVC 会拦截下来，走 Controller 里，因为 ServletConfig 里的 getServletMappings 写的 /
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 当访问 /pages/???时，走 /pages 目录下的内容
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }
}
