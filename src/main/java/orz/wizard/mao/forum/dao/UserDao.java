package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.User;

public class UserDao extends JdbcDaoSupport {
    
    private static final String SQL_SELECT_USER_BY_ID = "select * from user where id = ?";
    
    public User getUserById(long id) {
        return getJdbcTemplate().queryForObject(
                SQL_SELECT_USER_BY_ID,
                new ParameterizedRowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong(1));
                        user.setEmail(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setStatus(rs.getString(4));
                        return user;
                    }
                }, id);
    }
}
