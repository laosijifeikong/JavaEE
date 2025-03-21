import dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class Test {

    @org.junit.Test
    public void TestUser1() {
        // 加载Spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取Bean
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");

        // 调用方法
        userDao.UserSaying();
    }

    @org.junit.Test
    public void TestUser2() {
        // 加载Spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取Bean
        UserService userService = (UserService) applicationContext.getBean("userService");

        userService.GetUser();
    }
}
