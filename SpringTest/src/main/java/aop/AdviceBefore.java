package aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AdviceBefore implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (target != null) {
            System.out.println("将要执行" + target.getClass().getName() + ":" + method.getName() + "方法");
        }
    }
}
