package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import orz.wizard.mao.forum.entity.User;

public class UserDao extends JdbcDaoSupport {
    
    private static final String SQL_SELECT_USER_BY_ID = "select * from user where id = ?";
    private static final String SQL_SAVE_USER = "insert into user values(null, ?, ?, 'closed')";
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from user where email = ?";
    
    
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
    
    public User getUserByEmail(String email){
    	return getJdbcTemplate().queryForObject(
    			SQL_SELECT_USER_BY_EMAIL,
    			new ParameterizedRowMapper<User>(){
    				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
    					User user = new User();
    					user.setId(rs.getLong(1));
                        user.setEmail(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        user.setStatus(rs.getString(4));
                        return user;
    				}
    			}, email);
    }
    
    public User saveUser(final User user){
    	getJdbcTemplate().update(SQL_SAVE_USER, user.getEmail(), user.getPassword());
    	return getJdbcTemplate().queryForObject(
    			SQL_SELECT_USER_BY_EMAIL,
    			new ParameterizedRowMapper<User>(){
    				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
    					user.setId(rs.getLong(1));
                        user.setStatus(rs.getString(4));
                        return user;
    				}
    			}, user.getEmail());
    }
}
