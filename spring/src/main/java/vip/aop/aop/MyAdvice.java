package vip.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAdvice {
    //@Pointcut("execution(void vip.aop.dao.BookDao.update())")
    @Pointcut("execution(* vip.aop.*.*Dao.update(..))")
    private void pt() {}

    @Pointcut("execution(int vip.aop.dao.BookDao.select(..))")
    private void pt2() {}

    @Before("pt()")
    public void method() {
        System.out.println(System.currentTimeMillis());
    }

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
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("around 前");
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
}
