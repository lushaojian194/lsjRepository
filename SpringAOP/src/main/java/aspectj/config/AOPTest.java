package aspectj.config;

import aspectj.dao.TestDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPTest {
        public static void main(String[] args){
            //初始化Spring容器
            AnnotationConfigApplicationContext appCon = new AnnotationConfigApplicationContext(AspectjAOPConfig.class);
            TestDao testDaoAdvice = appCon.getBean(TestDao.class);
            //执行方法
            testDaoAdvice.save();
            System.out.println("==========================");
            testDaoAdvice.modify();
            System.out.println("==========================");
            testDaoAdvice.delete();
            appCon.close();

        }

}
