package service;

import dao.UserDao;

public class UserService {

    private UserDao userDao;

    // Setter方法（用于XML配置注入）
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void execute() {
        userDao.UserSaying();
    }
}
