package manager;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
public class SqlTemplateExt {

    private TransactionManagerExt transactionManagerExt;

    public int update(String sql, Object... params){
        int res;
        Connection connection = transactionManagerExt.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            res = ps.executeUpdate();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException("SQL执行失败", e);
        }
    }
}
