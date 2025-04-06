import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
}
