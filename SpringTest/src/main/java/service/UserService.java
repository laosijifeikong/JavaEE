package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserService {

    private UserDao userDao;

//    public void GetUser(){
//        userDao.UserSaying();
//    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    public UserService(UserDao userDao)
    {
        this.userDao = userDao;
    }
}
