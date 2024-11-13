package vip.annotation_bean.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConf {
    // 定义一个方法获得要管理的对象
    // 添加 @bean，表示当前方法的返回值是一个 bean
    @Bean
    public DataSource getDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/vip");
        ds.setUsername("pumu");
        ds.setPassword("123456");
        return ds;
    }
}
