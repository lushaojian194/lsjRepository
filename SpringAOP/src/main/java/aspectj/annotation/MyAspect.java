package aspectj.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect  //声明一个切面
@Component  //让此切面成为Spring容器管理的Bean
public class MyAspect {

    //定义切入点，通知增强哪些方法
    @Pointcut("execution(* aspectj.dao.*.*(..))")
    private void myPointCut(){

    }

    //前置通知
    @Before("myPointCut()")
    public void before(JoinPoint jp){
        System.out.print("前置通知：模拟权限控制");
        System.out.println(",目标对象："+jp.getTarget()+",被增强处理的方法："+jp.getSignature().getName());
    }

    //后置返回通知
    @AfterReturning("myPointCut()")
    public void afterReturing(JoinPoint jp){
        System.out.print("后置返回通知："+"模拟删除临时文件");
        System.out.println(",被增强处理的方法："+jp.getSignature().getName());
    }

    //环绕通知
    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        //开始
        System.out.println("环绕开始：执行目标方法前，模拟开启事务");
        //执行当前目标方法
        Object obj = pjp.proceed();
        //结束
        System.out.println("环绕结束：执行目标方法后，模拟关闭事务");
        return obj;
    }

    //异常通知
    @AfterThrowing(value="myPointCut()",throwing="e")
    public void except(Throwable e){
        System.out.println("异常通知："+"程序执行异常"+e.getMessage());
    }

    //后知（最终）通知
    @After("myPointCut()")
    public void after(){
        System.out.println("最终通知：模拟释放资源");
    }
}
