package aopStudy.transaction;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


//
//public class AroundTest extends DbUtil implements MethodInterceptor {
//
//    @Override
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        conn.setAutoCommit(false);
//        System.out.println("【前置通知】开启事务，关闭自动提交");
//
//        try {
//            // 执行目标方法
//            Object result = invocation.proceed();
//
//            // 如果目标方法成功执行，则提交事务
//            conn.commit();
//            System.out.println("【后置通知】业务方法执行成功，提交事务");
//
//            return result;
//        } catch (Exception e) {
//            // 如果目标方法抛出异常，则回滚事务
//            System.out.println("【异常通知】业务方法执行失败，回滚事务");
//            conn.rollback();
//            // 可以选择将异常继续抛出，或者封装后抛出
//            throw e;
//        } finally {
//            // 无论成功还是失败，最终都应该关闭连接（如果需要的话）
//            // 注意：在连接池环境下，close()通常是归还连接，而非物理关闭
//            // System.out.println("【最终通知】关闭数据库连接");
//            // this.conn.close();
//        }
//    }
//
//}

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AroundTest extends DbUtil  {

    @Pointcut("execution(* aopStudy.transaction.AroundTest.*(..))")
    public void pointcut() {}
    @Around("pointcut()")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        this.conn.setAutoCommit(false);
        System.out.println("【AOP】将事务的自动提交变为手动提交");

        try {
            // 调用目标方法
            Object obj = proceedingJoinPoint.proceed();

            // 如果成功，提交事务
            this.conn.commit();
            System.out.println("【AOP】业务方法执行成功，进行手动提交");

            return obj;
        } catch (Throwable e) {
            // 如果失败，回滚事务
            System.out.println("【AOP】业务方法执行失败，进行事务回滚！");
            this.conn.rollback();
            throw e; // 继续抛出异常
        }
    }
}
