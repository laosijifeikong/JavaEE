package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorAspect {

    // 定义切入点：匹配 ICalculator 接口的所有方法
    @Pointcut("execution(* service.impl.ICalculator.*(..))")
    public void calculatorMethods() {}

    // 前置通知
    @Before("calculatorMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("方法执行前: " + joinPoint.getSignature().getName());
    }

    // 后置通知（无论是否异常）
    @After("calculatorMethods()")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("方法执行后: " + joinPoint.getSignature().getName());
    }

    // 环绕通知
    @Around("calculatorMethods()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕开始");
        Object result = joinPoint.proceed(); // 执行目标方法
        System.out.println("环绕结束");
        return result;
    }

    // 异常通知
    @AfterThrowing(pointcut = "calculatorMethods()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        System.out.println("方法抛出异常: " + ex.getMessage());
    }
}
