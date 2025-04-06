package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AdviceAround implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("环绕开始");

        Object object = invocation.proceed();

        System.out.println("环绕结束");
        return object;
    }
}
