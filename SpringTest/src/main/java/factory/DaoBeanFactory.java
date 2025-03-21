package factory;

import dao.UserDao;

public class DaoBeanFactory {
    // 实例工厂方法
    public UserDao createDaoBean() {
        return new UserDao();
    }
}
