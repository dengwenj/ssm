package vip.annotation_bean.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"vip.annotation_bean.dao", "vip.annotation_bean.service"})
public class SpringConfig {

}
