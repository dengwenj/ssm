package vip.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("vip.aop")
// 告诉 spring 用的注解方式启动 aop
@EnableAspectJAutoProxy
public class SpringConfig {
}
