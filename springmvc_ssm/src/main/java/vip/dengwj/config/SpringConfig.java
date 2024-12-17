package vip.dengwj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"vip.dengwj.services"})
@PropertySource("classpath:jdbc.properties")
// jdbc, mybatis
@Import({JdbcConfig.class, MybatisConfig.class})
public class SpringConfig {
}
