### SSM

### Spring
* Spring 发展到今天已经成了一种开发的生态圈，Spring 提供了若干个项目，每个项目用于完成特定的功能

### 核心概念
* IOC（Inversion of Control）控制反转
* 使用对象时，由主动 new 产生对象转换为由外部提供对象，此过程中对象创建控制权由程序转移到外部，此思想称为控制反转
* Spring 技术对 IOC 思想进行了实现：
* 1、Spring 提供了一个容器，称为IOC容器，用来充当 IOC 思想中的“外部”
* 2、IOC 容器负责对象的创建、初始化等一系列工作，被创建或被管理的对象在 IOC 容器中统称为 Bean
* DI（Dependency Injection）依赖注入：
* 1、在容器中建立 bean 与 bean 之间的依赖关系的整个过程，称为依赖注入

### 目标（充分解耦）
* 使用 IOC 容器管理 bean（IOC）
* 在 IOC 容器内将有依赖关系的 bean 进行关系绑定（DI）
* 最终效果：使用对象时不仅可以直接从 IOC 容器中获取，并且获取到的 bean 已经绑定了所有的依赖关系

### bean 基础配置
* 功能：定义 Spring 核心容器管理的对象
* 属性列表：
* 1、id：bean 的 id，使用容器可以通过 id 值获取对应的 bean，在一个容器中 id 值唯一
* 2、class：bean 的类型，即配置的 bean 的全路径类名
* (<bean id="bookDao" class="vip.dengwj.dao.impl.BookDaoImpl" />)

### bean 别名配置
* name 属性：定义 bean 的别名，可定义多个，使用逗号 分号 空格分隔均可
* (<bean id="bookService" name="bookService2 bookService3" class="vip.dengwj.service.impl.BookServiceImpl" />)

### bean 作用范围配置
* scope 属性：定义 bean 的作用范围，singleton 单列，prototype 非单列
* (<bean id="bookService" name="bookService2 bookService3" class="vip.dengwj.service.impl.BookServiceImpl" scope="prototype" />)

### bean 作用范围说明
* 为什么 bean 默认为单例？节省资源，非单例的话每获取一个 bean 都会创建
* 适合交给容器进行管理的 bean： 表现层对象、业务层对象、数据层对象、工具对象
* 不适合交给容器进行管理的 bean：封装实体的域对象

### bean 实例化 
* bean 本质上就是对象，创建 bean 使用构造方法完成
* 1、构造方法方式：无参构造方法如果不存在，将抛出异常 BeanCreationException
* 2、静态工厂方式：(<bean id="bookDao1" class="vip.dengwj.factory.BookDaoFactory" factory-method="getBookDao" />)
* 3、实例工厂的方式：
* (<bean id="bookDaoFactory" class="vip.dengwj.factory.InBookDaoFactory" />
  <bean id="bookDao2" factory-bean="bookDaoFactory" factory-method="getBookDao" />)
* 4、FactoryBean 接口的方式(实用)
```java
public class BookDaoFactoryImpl implements FactoryBean<BookDao> {
    @Override
    public BookDao getObject() throws Exception {
        return new BookDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return BookDao.class;
    }
}
```

### bean 生命周期
* 生命周期：从创建到消亡的完整过程
* bean 生命周期：bean从创建到销毁的整个过程
* bean 生命周期控制：在 bean 创建后到销毁前做一些事情

### 执行顺序
* 初始化容器：
* 1、创建对象（内存分配）
* 2、执行构造方法
* 3、执行属性注入（set属性）
* 4、执行 bean 初始化方法
* 使用 bean：
* 1、执行业务操作
* 关闭/销毁容器
* 1、执行 bean 销毁方法

### bean 销毁时机
* 容器关闭前触发 bean 的销毁
* 关闭容器方式：
* 手工关闭容器：close 操作
* 注册关闭钩子，在虚拟机退出前先关闭容器再退出虚拟机：registerShutdownHook()

### 依赖注入方式
* 向一个类中传递数据的方式有几种?
* 1、普通方法（set 方法）
* 2、构造方法
* setter 注入：简单类型、引用类型
* 构造器注入：简单类型、引用类型

### setter 注入
* 配置中使用 property 标签 ref 属性注入引用类型对象
* 配置中使用 property 标签 value 属性注入简单类型数据

### 构造器注入
```xml
<!--    构造器注入-->
<bean id="userDao" class="vip.di.dao.impl.UserDaoImpl">
  <!--        这个 name 是参数名，要对应-->
  <!--        <constructor-arg name="name" value="李雷" />-->
  <!--        <constructor-arg name="age" value="20" />-->
  <!--        位置的方式-->
  <constructor-arg index="0" value="李雷" />
  <constructor-arg index="1" value="20" />
  <!--        type 的方式-->
</bean>
<bean id="userService" class="vip.di.service.impl.UserServiceImpl">
<constructor-arg name="userDao" ref="userDao" />
</bean>
```

### 依赖自动装配
* IOC 容器根据 bean 所依赖的资源在容器中自动查找并注入到 bean 中的过程称为自动装配
* 自动装配方式：按类型（常用）、按名称、按构造方法
*  autowire 属性：（<bean id="userService" class="vip.di.service.impl.UserServiceImpl" autowire="byType" />）

### 依赖自动装配特征
* 自动装配用于引用类型依赖注入，不能对简单类型进行操作
* 使用按类型装配时（byType）必须保证容器中相同类型的 bean 唯一，推荐使用
* 使用按名称装配时（byName）必须保证容器中具有指定名称的 bean，因变量名与配置耦合，不推荐使用
* 自动装配优先级低于 setter 注入与构造器注入，同时出现时自动装配配置失效

### 集合注入
* 数组、List、Set、Map、Properties
```xml
<!--    集合注入-->
    <bean id="collectionDao" class="vip.di.dao.impl.CollectionDaoImpl">
        <property name="arr">
            <array>
                <value>10</value>
                <value>20</value>
                <value>30</value>
            </array>
        </property>

        <property name="list">
            <list>
                <value>朴睦</value>
                <value>李雷</value>
                <value>韩梅梅</value>
            </list>
        </property>

        <property name="set">
            <set>
                <value>朴睦</value>
                <value>朴睦</value>
                <value>朴睦</value>
                <value>王阳明</value>
            </set>
        </property>

        <property name="map">
            <map>
                <entry key="name" value="朴睦" />
                <entry key="address" value="上海市" />
            </map>
        </property>

        <property name="properties">
            <props>
                <prop key="age">24</prop>
                <prop key="sex">男</prop>
            </props>
        </property>
    </bean>
```

### 数据源对象管理（第三方）
```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/c" />
        <property name="username" value="pumu" />
        <property name="password" value="123456" />
     </bean>
```

### 加载 properties 文件
* 1、开启 context 命名空间
* 2、使用 context 命名空间，加载指定 properties 文件
* 3、使用 ${} 读取加载的属性值
* 4、不加载系统属性 (<context:property-placeholder location="classpath*:*.properties" system-properties-mode="NEVER" />)
* 5、加载多个 properties 文件 (<context:property-placeholder location="jdbc.properties,ww.properties" />)
* 5、加载 properties 文件标准格式 (<context:property-placeholder location="classpath:*.jdbc.properties" />)
* 6、从类路径或 jar 包中搜索并加载 properties 文件 (<context:property-placeholder location="classpath:*.*.properties" />)
* 7、加载所有 properties 文件 (<context:property-placeholder location="*.properties" />)
```xml
<!--1、开启 context 命名空间-->
xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
<!--2、使用 context 命名空间，加载指定 properties 文件-->
<context:property-placeholder location="classpath*:*.properties" system-properties-mode="NEVER" />
<!--3、使用 ${} 读取加载的属性值-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
    </bean>
```

### 容器
* 创建容器的方式
* 1、类路径加载配置文件：ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
* 2、文件路径加载配置文件：ApplicationContext ctx = new FileSystemXmlApplicationContext("绝对路径");
* 3、加载多个配置文件：ApplicationContext ctx = new ClassPathXmlApplicationContext("bean1.xml", "bean2.xml");

### 获取 bean
* 1、使用 bean 名称获取：UserDao userDao = (UserDao) ctx.getBean("userDao");
* 2、使用 bean 名称获取并指定类型：UserDao userDao = ctx.getBean("userDao", UserDao.class);
* 3、使用 bean 类型获取：UserDao userDao = ctx.getBean(UserDao.class);

### BeanFactory 初始化
* Resource re = new ClassPathResource("applicationContext.xml");
* BeanFactory bf = new XmlBeanFactory(re);
* BeanFactory 创建完毕后，所有的 bean 均为延迟加载