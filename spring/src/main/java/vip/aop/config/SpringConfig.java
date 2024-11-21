package vip.aop.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("vip.aop")
// 告诉 spring 用的注解方式启动 aop
@EnableAspectJAutoProxy
@Import({JdbcConfig.class, MybatisConfig.class})
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {
}
