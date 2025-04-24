package dao.Impl;

import dao.BookDao;
import lombok.Setter;
import manager.SqlTemplateExt;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Setter
public class BookDaoImpl implements BookDao {

    private SqlTemplateExt sqlTemplateExt;

    @Override
    public double findBookPriceByIsbn(String isbn) {
        Connection conn = sqlTemplateExt.getTransactionManagerExt().getConnection();
        try (PreparedStatement ps = conn.prepareStatement("SELECT price FROM book WHERE isbn = ?")) {
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            } else {
                throw new RuntimeException("找不到该ISBN图书：" + isbn);
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询图书价格失败", e);
        }
    }

    @Override
    public int updateBookStock(String isbn) {
        String sql = "UPDATE book_stock SET stock = stock - 1 WHERE id = (SELECT id FROM book WHERE isbn = ?) AND stock > 0";
        return sqlTemplateExt.update(sql, isbn);
    }

    @Override
    public int updateUserAccount(int userId, double price) {
        String sql = "UPDATE user SET account = account - ? WHERE id = ? AND account >= ?";
        return sqlTemplateExt.update(sql, price, userId, price);
    }
}
