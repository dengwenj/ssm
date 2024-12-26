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

### 继承
* 继承描述的是两个工程间的关系，与 java 中的继承相似，子工程可以继承父工程中的配置信息，常见于依赖关系的继承
* 作用：1、简化配置，2、减少版本冲突

### 继承步骤
* 1、创建 Maven 模块，设置打包类型为 pom
* 2、在父工程的 pom 文件中配置依赖关系（子工程将沿用父工程中的依赖关系）
* 3、配置子工程中可选的依赖关系
* 4、在子工程中配置当前工程所继承的父工程
* 子工程中使用父工程中的可选依赖时，仅需要提供群组 id 和项目 id，无需提供版本，版本由父工程统一提供，避免版本冲突子工程中还可以定义父工程中没有定义的依赖关系

### 聚合与继承的区别
* 作用：
* 聚合用于快速构建项目（对父工程进行构建子工程跟着一块构建）
* 继承用于快速配置
* 相同点：
* 聚合与继承的 pom.xml 文件打包方式均为 pom，可以将两种关系制作到同一个 pom 文件中
* 聚合与继承均属于设计型模块，并无实际的模块内容
* 不同点：
* 聚合是在当前模块中配置关系，聚合可以感知到参与聚合的模块有哪些
* 继承是在子模块中配置关系，父模块无法感知哪些子模块继承了自己

### 属性配置
```xml
<properties>
    <spring.version>5.2.0</spring.version>
<!--    ...-->
</properties>
```
* groupId是项目组织的唯一标识符，通常使用反向域名来表示
* artifactId是项目的唯一名称，用于在同一个groupId下区分不同的项目。比如我这里的(mybatis，spring，springmvc，springmvc_ssm)

### 版本管理
* 工程版本：
* SNAPSHOT(快照版本)：项目开发过程中临时输出的版本，称为快照版本，快照版本会随着开发的进展不断更新
* RELEASE(发布版本)：稳定的版本

### maven 多环境配置
```xml
<!--    多环境配置-->
    <profiles>
<!--        生产环境-->
        <profile>
            <id>env_pro</id>
            <properties>
                <jdbc.url>...</jdbc.url>
            </properties>
        </profile>
        <!--        开发环境-->
        <profile>
            <id>env_dev</id>
            <properties>
                <jdbc.url>...</jdbc.url>
            </properties>
        </profile>
    </profiles>
```