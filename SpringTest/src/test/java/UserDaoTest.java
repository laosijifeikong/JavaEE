
import bean.User;
import dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import static org.junit.Assert.*;

public class UserDaoTest {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserDao userDao = (UserDao) applicationContext.getBean("userDao");

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(25);
        user.setSex("women");
        user.setRole("Admin");
        int id = userDao.addUser(user);
        assertTrue(id > 0);
    }

    @Test
    public void testUpdateUser() {
        User user = userDao.queryUserById(1);
        user.setName("lisi");
        userDao.updateUser(user);
        User updatedUser = userDao.queryUserById(1);
        assertEquals("lisi", updatedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteById(1);
        User user = userDao.queryUserById(1);
        assertNull(user);
    }

    @Test
    public void testFindById() {
        User user = userDao.queryUserById(1);
        assertNotNull(user);
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.queryUserListByName("zhangsan");
        assertFalse(users.isEmpty());
    }

    @Test
    public void testFindByAgeAndSex() {
        List<User> users = userDao.queryUserListByAgeAndSex(25, "women");
        assertFalse(users.isEmpty());
    }
}
