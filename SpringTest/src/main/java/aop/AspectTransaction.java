package aop;

import lombok.Setter;
import manager.TransactionManagerExt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Setter
@Aspect
public class AspectTransaction {

    private TransactionManagerExt transactionManagerExt;

    @Around("execution(* service.StudentService.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        transactionManagerExt.beginTransaction();
        System.out.println("开始转账事务");
        try {
            Object result = joinPoint.proceed();
            transactionManagerExt.commit();
            System.out.println("转账成功");
            return result;
        } catch (Exception e)
        {
            System.out.println("转账失败");
            System.out.println("执行回滚");
            try {
                transactionManagerExt.rollback();
            } catch (Exception e1) {
                System.out.println("回滚失败");
            }
            throw e;
        }
    }
}
