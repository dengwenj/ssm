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
* 自动装配就可以不用写 <property name="" ref="" 了，回去自动装配对象中的引用属性了
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

### 核心容器的总结
### 容器相关
* 1、BeanFactory 是 IOC 容器的顶层接口，初始化 BeanFactory 对象时，加载的 bean 延迟加载
* 2、ApplicationContext 接口是 Spring 容器的核心接口，初始化时 bean 立即加载
* 3、ApplicationContext 接口提供基础的 bean 操作相关方法，通过其他接口扩展其功能
* 4、ApplicationContext 接口常用初始化类（创建容器）：ClassPathXmlApplicationContext、FileSystemXmlApplicationContext

### bean 相关
* bean 的 Id
* bean 别名
* bean 类型，静态工厂类，FactoryBean 类(实现 spring 接口)
* 控制 bean 的实例数量
* 生命周期初始化方法
* 生命周期销毁方法
* 自动装配类型
* bean 工厂方法，应用于静态工厂或实例工厂
* 实例工厂 bean
* 控制 bean 延迟加载
```xml
<bean
    id="bookDao"
    name="dao daoImpl"
    class="vip.di.dao.impl.CollectionDaoImpl"
    scope="singleton"
    init-method="init"
    destroy-method="destroy"
    autowire="byType"
    factory-method="getInstance"
    factory-bean=""
    lazy-init="true" 
/>
```

### 依赖注入相关
* 构造器注入引用类型 (<constructor-arg name="属性名" ref="引用值" />)
* 构造器注入简单类型
* 类型匹配与索引匹配
* setter 注入引用类型 (<property name="属性名" ref="参数的引用值" />)
* 集合注入 list （<property><list>...</list></property>）

### 注解开发定义 bean（@Component 用来定义 bean 对象的）
* 使用 @Component 定义 bean，@Component 就是创建 bean 对象的，放入容器中
* @Component("bookDao")
* public class BookDaoImpl implements BookDao {}
* 核心配置文件中通过组件扫描加载 bean
* （<context:component-scan base-package="vip.dengwenj"）
* Spring 提供 @Component 注解的三个衍生注解：@Controller、@Service、@Repository，它们的作用一样，只是见名知意
```java
//@Component("BookService")
// 不写 value，getBean 获取的时候通过 Class 类型获取
@Service
public class BookServiceImpl implements BookService {
    @Override
    public void save() {
        System.out.println("BookServiceImpl...");
    }
}
```

### 纯注解开发
* Spring3.0 开启了纯注解开发模式，使用 Java 类替代配置文件，开启了 Spring 快速开发赛道
* Java 类代替 Spring 核心配置文件
* @Configuration 注解用于设定当前类为配置类，相当于 xml 配置文件中的开头那些配置
* @ComponentScan 注解用于设定扫描路径，此注解只能添加一次，多个数据用数组格式，相当于 xml 配置文件中的 <context:component-scan base-package="vip.dengwenj"
* @ComponentScan({"vip.dengwj.dao", "vip.dengwj.service"})
* 读取 Spring 核心配置文件初始化容器对象切换为读取 java 配置类初始化容器对象
* ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class)

### bean 作用范围
* 使用 @Scope 定义 bean 作用范围
* @Scope("singleton") 单例
* @Scope("prototype") 多个
* public class BookDaoImpl {}

### bean 生命周期
* 使用 @PostConstruct、@PreDestroy 定义 bean 生命周期
```java
//@Component("BookDao")
@Repository("BookDao")
//@Scope("prototype")
@Scope("singleton")
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("BookDaoImpl...");
    }

    @PostConstruct
    public void init() {
        System.out.println("BookDaoImpl init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("BookDaoImpl.destroy...");
    }
}
```

### 依赖注入
* 使用 @Autowired 注解开启自动装配模式（按类型）
* 注意：
* 1、自动装配基于反射设计创建对象并暴力反射对应属性为私有属性初始化数据，因此无需提供 setter 方法
* 2、自动装配建议使用无参构造方法创建对象（默认），如果不提供对应构造方法，提供唯一的构造方法
* 使用 @Qualifier("BookDao") 注解开启指定名称装配 bean，必须配合 @Autowired 注解使用
```java
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    @Qualifier("BookDao") // 指定 哪个 bean
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("BookServiceImpl...");
        bookDao.save();
    }
}
```
* 使用 @Value 实现简单类型注入
* 使用 @PropertySource 注解加载 properties 文件，路径仅支持单一文件配置，多文件使用数组格式配置，不允许使用通配符*
```java
@Configuration
@ComponentScan({"vip.annotation_bean.dao", "vip.annotation_bean.service"})
@PropertySource({"jdbc.properties"})
public class SpringConfig {

}
public class BookDaoImpl implements BookDao { 
    @Value("${jdbc.username}")
    private String name;
}
```

### 管理第三方 bean
* @Import 注解可以用于在配置类中导入其他配置类，@Import在一个配置类中只能使用一次，多个类用数组的方式
```java
@Configuration
@ComponentScan({"vip.annotation_bean.dao", "vip.annotation_bean.service"})
@PropertySource({"jdbc.properties"})
@Import({JdbcConf.class}) // 可以写多个类
// 错误
//@Import({JdbcConf.class})
//@Import({JdbcConf.class})
public class SpringConfig {

}

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
```

### 第三方 bean 注入资源（依赖注入）
* 引用类型：方法形参方式
* 简单类型：成员变量方式
* 引用类型注入只需要为 bean 定义方法设置形参即可，容器会根据类型自动装配对象
```java
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
```

### XML 配置比对注解配置
* 功能                   XML 配置                               注解
* 定义 bean              bean标签，id属性，class属性              @Component，@ComponentScan
* 设置依赖注入             setter、构造器、自动装配                 @Autowired@Qualifier，@Value
* 配置第三方 bean         bean标签，静态工厂，实例工厂，FactoryBean  @Bean
* 作用范围                scope属性                              @Scope
* 生命周期                init-method，destroy-method            @PostConstructor，@PreDestroy

### Spring 整合 Mybatis
```java
public class App2 {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    UserMapper userMapper = context.getBean(UserMapper.class);
    List<User> users = userMapper.selectAll();
    System.out.println(users);
  }
}

@Configuration
@ComponentScan("vip.spring_mybatis")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
public class SpringConfig {
    
}

public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    // mybatisConfig 中的 Mappers 标签
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("vip.spring_mybatis.mapper");
        return mapperScannerConfigurer;
    }
}
```

### Spring 整合 junit
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestUser {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        List<User> users = userMapper.selectAll();
        System.out.println("users -> " + users);
    }
}
```

### AOP 简介
* AOP(Aspect Oriented Programming) 面向切面编程，一种编程范式，指导开发者如何组织程序结构
* 作用：在不惊动原始设计的基础上为其进行功能增强
* Spring 理念：勿入侵式

### AOP 核心概念
* 连接点（JoinPoint）：程序执行过程中的任意位置，粒度为执行方法、抛出异常、设置变量等
* 在 SpringAOP 中，理解为方法的执行
* 
* 切入点（Pointcut）：匹配连接点的式子
* 在 SpringAOP 中，一个切入点可以只描述一个具体方法，也可以匹配多个方法
* 
* 通知（Advice）：在切入点处执行的操作，也就是共性功能
* 在 SpringAOP 中，功能最终以方法的形式呈现
* 
* 通知类：定义通知的类
* 切面（Aspect）：描述通知与切入点的对应关系
```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void vip.aop.dao.BookDao.update())")
    private void pt() {}

    @Before("pt()")
    public void method() {
        System.out.println(System.currentTimeMillis());
    }
}
```

### AOP 工作流程
* 1、Spring 容器启动
* 2、读取所有切面配置中的切入点
* 3、初始化 bean，判断 bean 对应的类中的方法是否匹配到任意切入点
* 匹配失败，创建对象
* 匹配成功，创建原始对象(目标对象)的代理对象
* 4、获取 bean 执行方法
* 获取bean，调用方法并执行，完成操作
* 获取的 bean 是代理对象时，根据代理对象的运行模式运行原始方法与增强的内容，完成操作
* 目标对象(Target)：原始功能去掉共性功能对应的类产生的对象，这种对象是无法直接完成最终工作的
* 代理(Proxy)：目标对象无法直接完成功能，需要对其进行功能回填，通过原始对象的代理对象实现

### AOP 切入点表达式
* 切入点：要进行增强的方法
* 切入点表达式：要进行增强的方法的描述方式
* 切入点表达式标准格式：动作关键字(访问修饰符 返回值 包名.类/接口名.方法名(参数) 异常名)
* execution(public User vip.dengwj.Dao.UserDao.findById(int))
* 动作关键字：描述切入点的行为动作，例如 execution 表示执行到指定切入点

### 使用通配符描述切入点，快速描述
* 1、*：单个独立的任意符合，可以独立出现，也可以作为前缀或者后缀的匹配符出现
* execution(public * vip.dengwj.*.*Service.find\*(\*))
* 2、..：多个连续的任意符号，可以独立出现，常用于简化包名与参数的书写
* execution(public User vip..UserService.findById(..))
* 匹配 vip 包下的任意包中的 UserService 类或者接口中所有名称为 findById 的方法
* 3、+：专用于匹配子类类型
* execution(* *..\*Service+.\*(..))

### 书写技巧
* 所有代码按照标准规范开发，否则以下技巧全部失效
* 描述切入点通常描述接口，而不描述实现类
* 访问控制修饰符针对接口开发均采用 public 描述(可省略)
* 返回值类型对于增删改类使用精准类型加速匹配，对于查询类使用*通配快速描述
* 包名书写尽量不使用..匹配，效率过低，常用*做单个包描述匹配，或精准匹配

### AOP 通知类型
* AOP 通知描述了抽取的共性功能，根据共性功能抽取的位置不同，最终运行代码时要将其加入到合理的位置
* AOP 通知共分为 5 种类型：
* 前置通知、后置通知、环绕通知、返回后通知、抛出异常后通知

### @Before
* 作用：设置当前通知方法与切入点之间的绑定关系，当前通知方法在原始切入点方法前运行
* 切入点方法名()，格式为类名.方法名()

### @After
* 作用：设置当前通知方法与切入点之间的绑定关系，当前通知方法在原始切入点方法后运行

### @Around(常用)
* 作用：设置当前通知方法与切入点之间的绑定关系，当前通知方法在原始切入点方法前后运行
* 注意事项：
* 1、环绕通知必须依赖形参 ProceedingJoinPoint 才能实现对原始方法的调用，进而实现原始方法调用前后同时添加通知
* 2、通知中如果未使用 ProceedingJoinPoint 对原始方法进行调用将跳过元素方法的执行（可以做些判断，是否运行调用）
* 3、对元素方法的调用可以不接受返回值，通知方法设置成 void 即可，如果接收返回值，必须设定为 Object 类型
* 4、原始方法的返回值如果是 void 类型，通知方法的返回值类型可以设置成 void，也可以设置成 Object
* 5、由于无法预知原始方法运行后是否会抛出异常，因此环绕通知方法必须抛出 Throwable 对象

### @AfterReturning
* 当前通知方法在原始切入点方法正常执行完毕后运行(返回结果后)

### AfterThrowing
* 当前通知方法在原始切入点方法运行抛出异常后执行
```java
@Before("pt2()")
public void before() {
    System.out.println("原始方法执行前运行");
}

@After("pt2()")
public void after() {
    System.out.println("原始方法执行后运行");
}

@Around("pt2()")
public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("around 前");
    //if (...) {
    //  joinPoint.proceed();
    //}
    Integer proceed = (Integer) joinPoint.proceed();
    System.out.println("around 后");
    return proceed + 19;
}

@AfterReturning("pt2()")
public void afterReturning() {
    System.out.println("原始方法返回后通知");
}

@AfterThrowing("pt2()")
public void afterThrowable() {
    System.out.println("原始方法抛出错误后通知");
}
```

### AOP 通知获取数据
* 获取切入点方法的参数：
* JoinPoint：适用于前置、后置、返回后、抛出异常后通知
* ProceedJoinPoint：适用于环绕通知
* 获取切入点方法返回值：返回后通知、环绕通知
* 获取切入点方法运行异常信息：抛出异常后通知、环绕通知
```java
@Component
@Aspect
public class TimeAdvice {
    @Pointcut("execution(* vip.aop.service.*Service.*(..))")
    private void servicePt() {
    }

    @Before("servicePt()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取参数
        Object[] args = joinPoint.getArgs();
    }

    @After("servicePt()")
    public void after(JoinPoint joinPoint) {
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    @Around("servicePt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        // 这里面有很多信息
        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getName();
        long start = System.currentTimeMillis();
        Object result = null;
        for (int i = 0; i < 10000; i++) {
            result = joinPoint.proceed();
        }
        long end = System.currentTimeMillis();
        System.out.println(declaringTypeName + "." + name + " -> " + (end - start) + "毫秒");
        return result;
    }

    @AfterReturning(value = "servicePt()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("获取返回值" + result);
    }

    @AfterThrowing(value = "servicePt()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println(e.getMessage());
    }
}
```
### 切入点匹配的是描述方法的式子

### Spring 事务简介
* 事务作用：在数据层保障一系列的数据库操作同成功同失败
* Spring 事务作用：在数据层或业务层保障一系列的数据库操作同成功同失败
* Spring 注解式事务通常添加在业务层接口中而不会添加到业务层实现类中，降低耦合
* 注解式事务可以添加到业务方法上表示当前方法开启事务，也可以添加到接口上表示当前接口所有方法开启事务
* 事务管理器要根据实现技术进行选择，Mybatis 框架使用的是 JDBC 事务
* 有些异常默认是不参与回滚的（IO）

### Spring 事务角色
* 事务管理员：发起事务方，在 Spring 中通常指代业务层开启事务的方法
* 事务协调员：加入事务方，在 Spring 中通常指代数据层方法，也可以是业务层方法

### 事务相关配置
* readOnly：设置是否为只读事务
* timeout：设置事务超时时间
* rollbackFor：设置事务回滚异常（class）
* rollbackForClassName：设置事务回滚异常（String）
* noRollbackFor：设置事务不回滚异常（class）
* noRollbackForClassName：设置事务不回滚异常（String）
* propagation：设置事务传播行为

### 事务传播行为
* 事务传播行为：事务协调员对事物管理员所携带事务的处理态度
* @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)