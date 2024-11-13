package vip.annotation_bean.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"vip.annotation_bean.dao", "vip.annotation_bean.service"})
@PropertySource({"jdbc.properties"})
public class SpringConfig {

}
