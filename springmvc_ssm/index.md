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