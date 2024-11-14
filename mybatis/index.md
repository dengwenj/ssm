### Mybatis
* MyBatis 是一款优秀的持久层框架，用于假话 JDBC 开发

### Mybatis 快速入门
* 创建 Mybatis 核心配置文件
* 编写 SQL 映射文件
* 编码：
* 1、定义 POJO 类
* 2、加载核心配置文件，获取 SqlSessionFactory 对象
* 3、获取 SqlSession 对象，执行 SQL 语句
* 4、释放资源
```java
public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        // 加载核心配置文件，获取 SqlSessionFactory 对象
        String resource = "./mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 获取 SqlSession 对象，执行 SQL 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.selectAll");
        System.out.println(users);
        // 释放资源
        sqlSession.close();
    }
}
```