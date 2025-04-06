package aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AdviceAfter implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

        if (target != null) {
            System.out.println("将要结束" + target.getClass().getName() + ":" + method.getName() + "方法");
        }
    }
}
