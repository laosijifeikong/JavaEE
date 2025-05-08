package mapper;

import bean.User;

public interface UserMapper {
    int insertUser(User user);
    int countUsers();
    User selectUserById(Integer id);
    int updateUser(User user);
    int deleteUser(Integer id);
}
