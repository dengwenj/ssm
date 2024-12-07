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