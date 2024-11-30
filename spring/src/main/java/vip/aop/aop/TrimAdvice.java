package vip.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TrimAdvice {
    @Pointcut("execution(* vip.aop.service.*Service.*(..))")
    public void servicePj() {}

    @Around("TrimAdvice.servicePj()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取参数
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            // 是否是字符串
            if (arg.getClass().equals(String.class)) {
                args[i] = arg.toString().trim();
            }
        }
        return joinPoint.proceed(args);
    }
}
