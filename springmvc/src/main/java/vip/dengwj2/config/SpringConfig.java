package vip.dengwj2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
// 方式一
//@ComponentScan({"vip.dengwj2.services", "vip.dengwj2.mapper"})
// 方式二
@ComponentScan(value = "vip.dengwj2", excludeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION,
    classes = {Controller.class}
))
public class SpringConfig {
}
