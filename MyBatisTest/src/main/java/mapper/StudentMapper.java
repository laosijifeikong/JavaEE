package mapper;

import bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> selectByCondition(Student student);

    void updateByCondition(Student student);

    List<Student> selectByIds(@Param("ids") List<Integer> ids);

    int batchInsert(@Param("list") List<Student> students);
}
