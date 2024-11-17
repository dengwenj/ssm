package vip.annotation_bean.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import vip.annotation_bean.dao.BookDao;

import javax.sql.DataSource;

public class JdbcConf {
    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    // 定义一个方法获得要管理的对象
    // 添加 @bean，表示当前方法的返回值是一个 bean
    @Bean
    public DataSource getDataSource(BookDao bookDao) {
        System.out.println("JdbcConf -> " + bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
