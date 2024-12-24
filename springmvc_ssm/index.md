### 异常处理器
* 各个层级均出现异常，异常处理代码书写在那一层：所有的异常均抛出到表现层进行处理
* 表现层处理异常，每个方法中单独书写，代码书写量巨大，如何解决： AOP思想
* 异常处理器：集中的、统一的处理项目中出现的异常
```java
// 表现层统一处理异常
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e) {
        return new Result(e.getCode(), e.getMessage());
    }

    // 系统异常
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e) {
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员
        return new Result(e.getCode(), e.getMessage());
    }

    // 其他异常
    // 设置指定异常的处理方案，功能等同于控制器方法，出现异常后终止原始控制器执行，并转入当前方法执行
    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        System.out.println(e.getClass() + ":" + e.getMessage());
        return new Result(1001, null, "统一处理异常，所有的异常都会来这里");
    }
}
```

### 项目异常处理方案
* 业务异常（BusinessException）
* 系统异常（SystemException）
* 其他异常（Exception）
```java
public class BusinessException extends RuntimeException {
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
```

### 拦截器概念
* 拦截器（Interceptor）是一种动态拦截方法调用的机制
* 作用：
* 1、在指定的方法调用前后执行预先设定的代码
* 2、阻止原始方法的执行

### 拦截器与过滤器区别
* 归属不同：Filter 属于 Servlet 技术，Interceptor 属于 SpringMVC 技术
* 拦截内容不同：Filter 对所有访问进行增强，Interceptor 仅针对 SpringMVC 的访问进行增强


### 拦截器定义和配置
```java
@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle...");
        // 返回 false 就不忘后面执行了
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}

@Configuration
public class SpringMVCSupport extends WebMvcConfigurationSupport {
    @Autowired
    ProjectInterceptor projectInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 当路径为 /books 或 /books/???，会走拦截器
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books", "books/*");
    }
}
```

### 拦截器参数
* 前置处理：
* 1、request 请求对象
* 2、response 响应对象
* 3、handler：被调用的处理器对象，本质上是一个方法对象，对反射技术中的 Method 对象进行了再封装
* 返回值：返回值为 false，被拦截的处理器将不执行

### 多拦截器执行顺序
* 当配置多个拦截器时，形成拦截器链
* 拦截器链的运行顺序参照拦截器添加顺序为准
* 当拦截器中出现对原始处理器的拦截，后面的拦截器均终止运行
* 当拦截器运行中断，仅运行配置在前面的拦截器的 afterCompletion 操作
* pre1 -> pre2 -> pre3 -> controller -> post3 -> post2 -> post1 -> after3 -> after2 -> after1

### 分模块开发意义
* 将原始模块按照功能拆分成若干个子模块，方便模块间的相互调用，接口共享

### 分模块开发
* 1、创建 Maven 模块
* 2、书写模块代码
* 3、通过 maven 指令安装模块到本地仓库（install 指令）

### 依赖管理
* 依赖传递（依赖具有传递性）：
* 1、直接依赖：在当前项目中通过依赖配置建立的依赖关系
* 2、简介依赖：被资源的资源如果依赖其他资源，当前项目间接依赖其他资源

### 依赖传递冲突问题
* 路径优先：当依赖中出现相同的资源时，层级越深，优先级越低，层级越浅，优先级越高
* 声明优先：当资源在相同层级被依赖时，配置顺序靠前的覆盖配置顺序靠后的
* 特殊优先：当同级配置了相同资源的不同版本，后配置的覆盖先配置的

### 可选依赖
* 可选依赖指对外隐藏当前所依赖的资源----不透明，隐藏后对应资源将不具有依赖传递性
* <optional>true</optional>

### 排除依赖
* 排除依赖指主动断开依赖的资源，被排除的资源无需指定版本-----不需要
* <exclusions><exclusion><groupId>log4j</groupId><artifactId>log4j</artifactId></exclusion></exclusions>

### 聚合
* 聚合：将多个模块组织成一个整体，同时进行项目构建的过程称为聚合（parent）
* 聚合工程：通常是一个不具有业务功能的”空“工程（只有一个pom文件）
* 作用：使用聚合工程可以将多个工程编组，通过对聚合工程进行构建，实现对所包含的模块进行同步构建。当工程中
* 某个模块发生更新时，必须保证工程中与已更新模块关联的模块同步更新，此时可以使用聚合工程来解决批量模块同步构建的问题

### 聚合工程开发
* 1、创建 Maven 模块，设置打包类型为 pom
* <packaging>pom</packaging>，每个 maven 工程都有对应的打包方式，默认为 jar，web工程打包方式为 war
* 2、设置当前聚合工程所包含的子模块名称
* <modules><module>../maven_pojo</module></modules>