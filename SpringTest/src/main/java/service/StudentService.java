package service;

import dao.StudentDao;
import lombok.Setter;

@Setter
public class StudentService {

    private StudentDao studentDao;

    public void transfer(int fromId, int toId, double money){
        System.out.println("开始转账" + fromId + "->" + toId + "金额：" + money);
        studentDao.updateBalance(fromId, -money);
        studentDao.updateBalance(toId, money);
        // 模拟异常
        if (money >= 100) {
            throw new RuntimeException("转账金额过大，异常");
        }
    }
}
