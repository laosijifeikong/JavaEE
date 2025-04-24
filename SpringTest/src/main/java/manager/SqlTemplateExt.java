package manager;

import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Setter
public class SqlTemplateExt {

    private TransactionManagerExt transactionManagerExt;

    public void update(String sql, Object... params){
        Connection connection = transactionManagerExt.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException("SQL执行失败", e);
        }
    }
}
