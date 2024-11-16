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

### Mybatis 核心配置文件详解
* 配置各个标签时，需要遵循前后顺序

### mybatis 配置文件完成增删改查
* 三步：编写接口方法 -> 编写 SQL -> 执行方法

### 查询所有
* 实体类属性名和数据库表列名不一致，不能自动封装数据
* 1、起别名：在 SQL 语句中，对不一样的列名起别名，别名和实体类属性名一样。*可以定义 《sql》片段，提升复用性
* 2、resultMap：定义 《resultMap》完成不一致的属性名和列名的映射
```xml
    <!--
        id：唯一标识，type：映射的类型，支持别名。
        在 <select> 标签中，使用 resultMap 属性替换 resultType 属性
    -->
    <resultMap id="brand" type="vip.dengwj.pojo.Brand">
        <!--        id标签：完成主键字段的映射。column：表的列名，property：实体类的属性名-->
        <!--        result标签：完成一般字段的映射-->
        <result column="brand_name" property="brandName" />
        <result column="company_name" property="companyName" />
    </resultMap>
    <select id="selectAll" resultMap="brand">
        select id, brand_name, company_name, ordered, description, status
        from tb_brand;
    </select>
```