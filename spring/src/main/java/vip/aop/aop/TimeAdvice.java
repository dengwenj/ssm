package vip.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAdvice {
    @Pointcut("execution(* vip.aop.service.*Service.*(..))")
    private void servicePt() {
    }

    @Around("servicePt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
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
}
