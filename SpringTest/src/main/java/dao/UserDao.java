package dao;

import bean.User;

import java.util.List;

public interface UserDao {

//    public void UserSaying()
//    {
//        System.out.println("UserDao");
//    }

    int addUser(User user);

    void updateUser(User user);

    void deleteById(int id);

    User queryUserById(int id);

    List<User> queryUserListById(int id);

    List<User> queryUserListByName(String name);

    List<User> queryUserListByAgeAndSex(int age, String sex);
}
