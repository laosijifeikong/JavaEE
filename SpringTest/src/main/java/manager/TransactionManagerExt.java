package manager;

import lombok.Setter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerExt {

    @Setter
    private DataSource dataSource;

    private final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public void beginTransaction(){
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            connectionThreadLocal.set(connection);
        } catch (SQLException e) {
            throw new RuntimeException("事务开启失败", e);
        }
    }

    public void commit(){
        Connection connection = connectionThreadLocal.get();
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e)
        {
            throw new RuntimeException("提交失败", e);
        } finally {
            connectionThreadLocal.remove();
        }
    }

    public void rollback(){
        Connection connection = connectionThreadLocal.get();
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("回滚失败", e);
        } finally {
            connectionThreadLocal.remove();
        }
    }

    public Connection getConnection(){
        return connectionThreadLocal.get();
    }
}
