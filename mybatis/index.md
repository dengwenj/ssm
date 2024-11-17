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

### 查询详情
* 参数占位符：
* 1、#{}：执行 SQL 时，会将 #{} 占位符替换成 ?，将来自动设置参数值
* 2、${}：拼 SQL，会存在 SQL 注入问题
* 使用时机：参数传递都是用 #{}，如果要对表名、列名进行动态设置，只能使用 ${} 进行 sql 拼接
* parameterType：用于设置参数类型，该参数可以省略
* SQL 语句中特殊字符处理：转义字符、《!【CDATA【内容】】》
```xml
<!--    查询详情 &lt; 小于-->
    <select id="selectById" resultMap="brand">
        select * from tb_brand where id = #{id};
    </select>
```

### 条件查询
* SQL 语句设置多个参数有几种方式
* 1、散装参数：需要使用 @Param("SQL 中的参数占位符名称")
* 2、实体类封装参数：只需要保证 SQL 中的参数名和实体类属性名对应上，即可设置成功
* 3、map集合：只需要保证 SQL 中的参数名和 map 集合的键的名称对应上，即可设置成功
```xml
<select id="selectByCondition" resultMap="brand">
    select *
    from tb_brand
    where status = #{status}
    and company_name like concat('%', #{companyName}, '%')
    and brand_name like concat('%', #{brandName}, '%');
</select>
```

### 多条件的动态查询
* SQL 语句会随着用户的输入或外部条件的变化而变化，我们称为动态 SQL
* 动态 SQL：
* 1、if：用于判断参数是否有值，使用 test 属性进行条件判断
* 存在的问题：第一个条件不需要逻辑运算符
* 解决方案：1、使用恒等式让所有条件格式都一样 where 1 = 1
* 2、《where》标签替换 where 关键字。只有一个 if 的时候会去掉 and
```xml
<select id="selectByCondition" resultMap="brand">
    select *
    from tb_brand
    <where>
        <if test="status != null">
            and status = #{status}
        </if>
        <if test="companyName != null and companyName != ''">
            and company_name like concat('%', #{companyName}, '%')
        </if>
        <if test="brandName != null and brandName != ''">
            and brand_name like concat('%', #{brandName}, '%')
        </if>
    </where>
</select>
```

### 单条件的动态条件查询
* 从多个条件中选择一个
* choose(when, otherwise):选择，类似 java 中的 switch 语句。otherwise 相当于 default
```xml
<select id="selectByConditionSingle" resultMap="brand">
    select *
    from tb_brand
    <where>
        <choose>
            <when test="status != null">
                status = #{status}
            </when>
            <when test="companyName != null and companyName != ''">
                company_name like concat('%', #{companyName}, '%')
            </when>
            <when test="brandName != null and brandName != ''">
                brand_name like concat('%', #{brandName}, '%')
            </when>
        </choose>
    </where>
</select>
```

### 添加
* Mybatis 事务：
* 1、openSession()：默认开启事务，进行增删改操作后需要使用 sqlSession.commit(); 手动提交事务
* 2、openSession(true)：可以设置为自动提交事务（关闭事务）
* 主键返回：<insert id="add" useGeneratedKeys="true" keyProperty="id">
```xml
<insert id="add" useGeneratedKeys="true" keyProperty="id">
    insert into tb_brand (brand_name, company_name, ordered, description, status)
    values (#{brandName}, #{companyName}, #{ordered}, #{description}, #{status});
</insert>
```

### 更新
```xml
<!--动态更新-->
<update id="updateById">
    update tb_brand
    <set>
        <if test="brandName != null and brandName != ''">
            brand_name = #{brandName},
        </if>
        <if test="companyName != null and companyName != ''">
            company_name = #{companyName},
        </if>
        <if test="status != null">
            status = #{status},
        </if>
        <if test="description != null and description != ''">
            description = #{description},
        </if>
        <if test="ordered != null">
            ordered = #{ordered},
        </if>
    </set>
    where id = #{id}
</update>
```

### 删除
* 批量删除：mybatis 会将数组参数，封装为一个 Map 集合。默认：array = 数组，使用 @Param 注解改变 map 集合的默认 key 的名称
```xml
<!--    删除单个-->
    <delete id="deleteById">
        delete
        from tb_brand
        where id = #{id};
    </delete>
<!--    删除多个-->
    <delete id="deleteByIds">
        delete from tb_brand
        where id in
        <foreach collection="ids" item="id" separator="," open="(" close=")">
            #{id}
        </foreach>
    </delete>
```

### Mybatis 参数传递
* Mybatis 接口方法中可以接收各种各样的参数，Mybatis 底层对于这些参数进行不同的封装处理方式
* Mybatis 提供了 ParamNameResolve 类来进行参数封装
* 单个参数：
* 1、POJO类型(实体类)：直接使用，属性名和参数占位符名称一致
* 2、Map 集合：直接使用，键名和参数占位符名称一致
* 3、Collection：封装为 Map 集合，可以使用 @Param 注解，替换 Map 集合中默认的 arg0 键名
* map.put("arg0", collection集合)
* map.put("collection", collection集合)
* 4、List：封装为 Map 集合，可以使用 @Param 注解，替换 Map 集合中默认的 arg0 键名
* map.put("arg0", list集合)
* map.put("collection", list集合)
* map.put("list", list集合)
* 5、Array：封装为 Map 集合，可以使用 @Param 注解，替换 Map 集合中默认的 arg0 键名
* map.put("arg0", 数组)
* map.put("array", 数组)
* 6、其他类型：直接使用
* 多个参数：封装为 Map 集合，可以使用 @Param 注解，替换 Map 集合中默认的 arg 键名
* map.put("arg0", 参数值1)
* map.put("param1", 参数值1)
* map.put("arg1", 参数值2)
* map.put("param2", 参数值2)
* ------------@Param("username")----------
* map.put("username", 参数值1)
* map.put("param1", 参数值1)
* map.put("arg1", 参数值2)
* map.put("param2", 参数值2)
