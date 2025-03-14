import dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void testUserDao() {
        // 加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取Bean
        UserDao userDao = (UserDao) context.getBean("userDao");

        // 调用方法
        userDao.UserSaying();
    }
}
