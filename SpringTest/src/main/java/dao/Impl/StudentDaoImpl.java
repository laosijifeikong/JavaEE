package dao.Impl;

import dao.StudentDao;
import lombok.Setter;
import manager.SqlTemplateExt;


@Setter
public class StudentDaoImpl implements StudentDao {

    private SqlTemplateExt sqlTemplateExt;

    @Override
    public void updateBalance(int studentId, double amount) {
        String sql = "update student set balance = balance + ? where id = ?";
        sqlTemplateExt.update(sql, amount, studentId);
//        int a = 1 / 0; // 模拟异常
    }
}
