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

### Mapper 代理开发
* 目的：解决原生方式中的硬编码，简化后期执行 sql
* 使用 Mapper 代理：
* 1、定义与 SQL 映射文件同名的 Mapper 接口，并且将 Mapper 接口和 SQL 映射文件放置在同一个目录下
* 2、设置 SQL 映射文件的 namespace 属性为 Mapper 接口全限定名
* 3、在 Mapper 接口中定义方法，方法名就是 SQL 映射文件中 sql语句的 id，并保持参数类型和返回值类型一致
* 4、编码：通过 SqlSession 的 getMapper 方法获取 Mapper 接口的代理对象，调用对应方法完成 sql 的执行