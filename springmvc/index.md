### SpringMVC

### SpringMVC 概述
* SpringMVC 技术与 Servlet 技术功能等同，均属于 web 层开发技术
* SpringMVC 是一种基于 Java 实现 MVC 模型的轻量级 web 框架（表现层框架技术）
* 优点：1、使用简单，开发便捷(相比于 Servlet)，2、灵活性强

### SpringMVC
```java
// 定义一个 servlet 容器启动的配置类, tomcat 容器启动的时候会加载这个类
public class ServletConfig extends AbstractDispatcherServletInitializer {
    // 加载 SpringMVC 配置文件
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMVCConfig.class);
        return context;
    }

    // 允许哪些路径进行访问
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // 加载 Spring 配置文件
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}

// 使用 Controller 定义 bean
@Controller
public class UserController {
    // 设置访问路径
    @RequestMapping("/save")
    // 返回的数据，把响应的东西返回出去
    @ResponseBody
    public String save() {
        System.out.println("user save...");
        return "{'module': 'info'}";
    }
}
```

### 工作流程分析
* 启动服务器初始化过程：
* 1、tomcat服务器启动，执行 ServletContainersInitConfig 类，初始化 web 容器
* 2、执行 createServletApplicationContext 方法，创建了 WebApplicationContext 对象
* 3、加载 SpringMvcConfig
* 4、执行 @ComponentScan 加载对应的 bean
* 5、加载 UserController，每个 @RequestMapping 的名称对应一个具体的方法
* 6、执行 getServletMappings 方法，定义所有的请求都通过 SpringMVC
* 单次请求过程：
* 1、发送请求 localhost/save
* 2、web 容器(tomcat) 发现所有请求都经过 SpringMVC，将请求交给 SpringMVC 处理
* 3、解析请求路径 /save
* 4、由 /save 匹配执行对应的方法 save()
* 5、执行 save()
* 6、检测到有 @ResponseBody 直接将 save() 方法的返回值作为响应体返回给请求方
* Web容器 -> ServletContext -> WebApplicationContext -> UserController -> /save save()

### Spring 和 SpringMVC bean 加载控制
* SpringMVC 相关 bean(表现层 bean)
* Spring 控制的 bean：业务bean(Service)、功能bean(DataSource等)

### SpringMVC 相关 bean 加载控制
* SpringMVC 加载的 bean 对应的包均在 ...controller 包内

### Spring 相关 bean 加载控制
* 方式一：Spring 加载的 bean 设定扫描范围为 vip.dengwj，排除 controller 包内的bean
* 方式二：Spring 加载的 bean 设定扫描范围为精准范围，例如 service 包、dao包等
* 方式三：不区分 Spring 与 SpringMVC 的环境，加载到同一个环境中
* @Configuration 文件中只要扫到这个注解就会以配置的方式加载
```java
@Configuration
// 方式一
//@ComponentScan({"vip.dengwj2.services", "vip.dengwj2.mapper"})
// 方式二
@ComponentScan(value = "vip.dengwj2", excludeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION, // 通过注解去排除
    classes = {Controller.class} // 具体哪个注解
))
public class SpringConfig {
}
```

### 请求路径映射
* 名称：@RequestMapping
* 类型：方法注解 类注解
* 位置：SpringMVC 控制器方法定义上方
* 作用：设置当前控制器方法请求访问路径，如果设置在类上统一设置当前控制器方法请求访问路径前缀
* 属性：value(默认)，请求访问路径，或访问路径前缀
```java
@Controller
@RequestMapping("/book")
public class BookController {
    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        return "book/save";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        return "book/delete";
    }
}
```

### 请求与响应
### Get 请求参数
* 普通参数：url 地址传参，地址参数名与形参变量名相同，定义形参即可接收参数
* 普通参数：请求参数名与形参变量名不同，使用@RequestParam 绑定参数关系
* POJO参数：请求参数名与形参对象属性名相同，定义 POJO 类型形参即可接收参数
* 数组参数：请求参数名与形参对象属性名相同且请求参数为多个，定义数组类型形参即可接收参数
* 集合保存普通参数：请求参数名与形参集合对象名相同且请求参数为多个，@RequestParam 绑定参数关系

### Post 请求参数
* 普通参数：form 表单 post 请求传参，表单参数名与形参变量名相同，定义形参即可接收参数
* post请求中文乱码处理：为 web 容器添加过滤器并指定字符集，Spring-web 包中提供了专用的字符过滤器
```java
// get post 都可以接收到
@RequestMapping("/commonParams")
@ResponseBody
public String commonParams(String name, Integer age) {
    System.out.println("姓名：" + name + ", " + "年龄：" + age);
    return "{'module': 'commonParams'}";
}
// 请求参数名与形参不同
@RequestMapping("/diffParams")
@ResponseBody
public String diffParams(@RequestParam("name") String username, Integer age) {
    System.out.println("username -> " + username);
    System.out.println("age -> " + age);
    return "{'module': 'diffParams'}";
}

// pojo 对象
@RequestMapping("/pojoobject")
@ResponseBody
public String pojoobject(User user) {
    System.out.println(user);
    return "{'module': 'pojoobject'}";
}

// 数组
@RequestMapping("/array")
@ResponseBody
public String array(String[] names) {
    System.out.println(Arrays.toString(names));
    return "{'module': 'array'}";
}

// List集合
@RequestMapping("/list")
@ResponseBody
public String list(@RequestParam List<String> list) {
    System.out.println(list + " <- list");
    return "{'module': 'list'}";
}

// json 方式传递参数
@RequestMapping("/jsonlist")
@ResponseBody
public String jsonlist(@RequestBody List<String> list) {
    System.out.println("list -> " + list);
    return "{'module': 'jsonlist'}";
}

@RequestMapping("/jsonpojo")
@ResponseBody
public String jsonpojo(@RequestBody User user) {
    System.out.println("user -> " + user);
    return "{'module': 'jsonpojo'}";
}

@RequestMapping("/jsonlistpojo")
@ResponseBody
public String jsonlistpojo(@RequestBody List<User> list) {
    System.out.println("list user ->" + list);
    return "{'module': 'jsonlistpojo'}";
}

// 日期传递
@RequestMapping("/date")
@ResponseBody
public String date(Date date,
                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date3) {
    System.out.println("date -> " + date);
    System.out.println("date2 -> " + date2);
    System.out.println("date3 -> " + date3);
    return "{'module': 'date'}";
}

// 响应字符串
@RequestMapping("returnstring")
@ResponseBody
public String returnstring() {
    return "returnstring";
}

@RequestMapping("reutrnpojo")
@ResponseBody
public User reutrnpojo() {
    User user = new User();
    user.setName("朴睦");
    user.setAge(18);
    return user;
}

@RequestMapping("reutrnlistpojo")
@ResponseBody
public List<User> reutrnlistpojo() {
    List<User> list = new ArrayList<>();
    list.add(new User("朴睦", 24, null));
    list.add(new User("李雷", 18, null));
    return list;
}
```

### @RequestBody
* 形参注解，将请求中请求体所包含的数据传递给请求参数，此注解一个处理器方法只能使用一次

### @EnableWebMvc
* 配置类注解，开启 SpringMVC 多项辅助功能

### @RequestBody 和 @RequestParam 区别
* 1、@RequestParam 用于接收 url 地址传参，表单传参【application/x-www-form-urlencoded】
* 2、@RequestBody 用于接收 json 数据【application/json】
* 应用：
* 1、后期开发中，发送 json 格式数据为主，@RequestBody 应用较广
* 2、如果发送非 json 格式数据，选用 @RequestParam 接收请求参数

### 日期类型传递
* @DateTimeFormat
* 类型：形参注解，设定日期时间型数据格式， @DateTimeFormat(pattern = "yyyy-MM-dd")

### 类型转换器
* Converter 接口，根据类型匹配对应的转换器

### @ResponseBody
* 方法注解，设置当前控制器返回值作为响应体
* HttpMessageConverter 接口，响应做转换

### REST 简介
* REST（Representational State Transfer）。表现形式状态转换
* 优点：隐藏资源的访问行为，无法通过地址得知对资源是何种操作，书写简化

### @RequestBody @RequestParam @PathVariable
* 区别：
* 1、@RequestParam 用于接收 url 地址传参或表单传参
* 2、@RequestBody 用于接收 json 数据
* 3、@PathVariable 用于接收路径参数，使用 {参数名称} 描述路径参数