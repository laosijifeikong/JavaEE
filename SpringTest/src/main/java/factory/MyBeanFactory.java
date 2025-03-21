package factory;

import dao.UserDao;

public class MyBeanFactory {
    // 静态工厂方法
    public static UserDao createDaoBean() {
        return new UserDao();
    }
}
