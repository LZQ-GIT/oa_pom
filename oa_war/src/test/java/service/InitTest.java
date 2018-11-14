package service;

import com.qf.oa.entity.Emp;
import com.qf.oa.service.IEmpService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Authoer lzq
 * @DateTime 2018/10/31  20:45
 * @Version 1.0
 */
public class InitTest {
    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext-*.xml");
        IEmpService empService = ctx.getBean(IEmpService.class);
        List<Emp> empList = empService.getEmpByKeyword("1",1);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }
}
