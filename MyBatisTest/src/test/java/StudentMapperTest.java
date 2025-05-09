import bean.Student;
import mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class StudentMapperTest {
    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @Test
    public void testSelectByCondition() {
        Student student = new Student();
        student.setName("zhangsan");

        List<Student> result = studentMapper.selectByCondition(student);
        System.out.println("查询结果：");
        for (Student s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void testUpdateByCondition() {
        Student student = new Student();
        student.setId(1); // 确保 id=1 的记录存在
        student.setBalance(Double.valueOf("88.88"));

        studentMapper.updateByCondition(student);
        sqlSession.commit();
    }

    @Test
    public void testSelectByIds() {
        List<Integer> ids = Arrays.asList(1, 2);
        List<Student> students = studentMapper.selectByIds(ids);
        System.out.println("批量查询结果：");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    @Test
    public void testBatchInsert() {
        Student s1 = new Student(101, "Alice", Double.valueOf("1000.00"));
        Student s2 = new Student(102, "Bob", Double.valueOf("1200.50"));
        List<Student> students = Arrays.asList(s1, s2);

        int result = studentMapper.batchInsert(students);
        sqlSession.commit();
        System.out.println("批量插入结果：" + result);
    }

    @After
    public void tearDown() {
        sqlSession.close();
    }
}
