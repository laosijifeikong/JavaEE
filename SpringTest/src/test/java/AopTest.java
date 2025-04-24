import config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookService;
import service.StudentService;
import service.impl.ICalculator;

public class AopTest
{
    @Test
    public void TestApp()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//        ICalculator calculator = (ICalculator)applicationContext.getBean("calculatorProxy");
        ICalculator calculator = applicationContext.getBean("calculatorTarget", ICalculator.class);
        try
        {
            System.out.println(calculator.add(1,1));
            System.out.println(calculator.sub(1,1));
            System.out.println(calculator.mul(1,10));
            System.out.println(calculator.div(1,1));
            System.out.println(calculator.div(1,0));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Test
    public void TestApp2()
    {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = context.getBean(StudentService.class);
        studentService.transfer(1, 2, 50);
        context.close();
    }

    /**
     * 实验7测试
     */
    @Test
    public void testPurchase() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookService bookService = context.getBean(BookService.class);
        bookService.purchase(1, "1");
        context.close();
    }
}
