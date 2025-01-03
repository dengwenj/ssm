package vip.dengwj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"vip.dengwj.controller", "vip.dengwj.config"})
@EnableWebMvc
public class SpringMVCConfig {
}
