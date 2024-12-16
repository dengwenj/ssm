package vip.dengwj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"vip.dengwj.controller", "vip.dengwj.config"})
// 该注解整合了多个功能，此处仅使用其中一部分功能，即 json 数据进行自动类型转换
@EnableWebMvc
public class SpringMVCConfig {
}
