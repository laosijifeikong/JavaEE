package aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class AdviceThrow implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Throwable ex) {
        System.out.println("方法 " + method.getName() + " 抛出异常: " + ex.getMessage());
    }
}
