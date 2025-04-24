package dao.Impl;

import bean.User;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        user.setSex(rs.getString("sex"));
        user.setRole(rs.getString("role"));
        return user;
    };

    @Override
    public int addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user(name, age, sex, role) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS // 指定返回生成的主键
            );
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getRole());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(
                "UPDATE user SET name = ?, age = ?, sex = ?, role = ? WHERE id = ?",
                user.getName(), user.getAge(), user.getSex(), user.getRole(), user.getId()
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("delete from user where id = ?", id);
    }

    @Override
    public User queryUserById(int id) {
        return jdbcTemplate.queryForObject("select * from user where id = ?", rowMapper, id);
    }

    @Override
    public List<User> queryUserListById(int id) {
        return jdbcTemplate.query("select * from user where id = ?", rowMapper, id);
    }

    @Override
    public List<User> queryUserListByName(String name) {
        return jdbcTemplate.query("select * from user where name = ?", rowMapper, name);
    }

    @Override
    public List<User> queryUserListByAgeAndSex(int age, String sex) {
        return jdbcTemplate.query("select * from user where age = ? and sex = ?", rowMapper, age, sex);
    }
}
