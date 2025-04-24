package service;

import dao.BookDao;
import lombok.Setter;
import manager.TransactionManagerExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
@Setter
public class LogService {

    private TransactionManagerExt transactionManagerExt;

    public void log(String message) {
        // 模拟 REQUIRES_NEW，使用新连接，不影响主事务
        Connection conn = null;
        try {
            conn = transactionManagerExt.getDataSource().getConnection();
            conn.setAutoCommit(false);

            // 手动执行插入（不使用 ThreadLocal 连接）
            PreparedStatement ps = conn.prepareStatement("INSERT INTO log (message) VALUES (?)");
            ps.setString(1, message);
            ps.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                // 打印失败日志
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}
