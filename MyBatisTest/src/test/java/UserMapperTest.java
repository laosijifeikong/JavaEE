import bean.Student;
import bean.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testInsertUser() {
        User user = new User("wangwu", 18, "man");
        int result = userMapper.insertUser(user);
        sqlSession.commit();
        System.out.println("插入结果：" + result);
        System.out.println("生成的主键ID：" + user.getId());
    }

    @Test
    public void testCountUsers() {
        int count = userMapper.countUsers();
        System.out.println("用户总数：" + count);
    }

    @Test
    public void testSelectUserById() {
        User user = userMapper.selectUserById(1);
        System.out.println("查询结果：" + user);
    }

    @Test
    public void testUpdateUser() {
        User user = userMapper.selectUserById(3);
        user.setName("zhangsan");
        int result = userMapper.updateUser(user);
        sqlSession.commit();
        System.out.println("更新结果：" + result);
    }

    @Test
    public void testDeleteUser() {
        int result = userMapper.deleteUser(3);
        sqlSession.commit();
        System.out.println("删除结果：" + result);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }
}
