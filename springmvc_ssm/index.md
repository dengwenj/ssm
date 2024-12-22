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