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