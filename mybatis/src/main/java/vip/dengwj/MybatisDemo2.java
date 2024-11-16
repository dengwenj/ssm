package vip.dengwj;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import vip.dengwj.mapper.UserMapper;
import vip.dengwj.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo2 {
    public static void main(String[] args) throws IOException {
        // 加载核心配置文件，获取 SqlSessionFactory 对象
        String resource = "./mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 获取 SqlSession 对象，执行 SQL 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 使用 Mapper 代理的方式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println("users ->" + users);
        // 释放资源
        sqlSession.close();
    }
}
