package vip.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
