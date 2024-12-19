package vip.dengwj.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 表现层统一处理异常
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 设置指定异常的处理方案，功能等同于控制器方法，出现异常后终止原始控制器执行，并转入当前方法执行
    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        System.out.println(e.getClass() + ":" + e.getMessage());
        return new Result(1001, null, "统一处理异常，所有的异常都会来这里");
    }
}
