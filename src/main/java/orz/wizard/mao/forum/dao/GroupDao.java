package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;

public class GroupDao extends JdbcDaoSupport {
    
    private static final String SQL_SELECT_GROUP_BY_ID = "select * from group where id = ?";
    private static final String SQL_SELECT_GROUP_LIST_BY_USER_ID = "select * from group where user_id = ?";
    private static final String SQL_SAVE_USER = "insert into user values(null, ?, ?, 'closed')";
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from user where email = ?";
    private static final String SQL_SELECT_USER_INFO_BY_ID = "select * from user_info where user_id = ?";
    private static final String SQL_SAVE_USER_INFO = "update user_info set nickname = ?, phone = ?, address = ?, gender = ?, birthday = ? where user_id = ?";
    
    public Group getGroupById(long id) {
        return getJdbcTemplate().queryForObject(
                SQL_SELECT_GROUP_BY_ID,
                new ParameterizedRowMapper<Group>() {
                    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Group group = new Group();
                        group.setId(rs.getLong("id"));
                        group.setUserId(rs.getLong("user_id"));
                        group.setIntro(rs.getString("intro"));
                        group.setName(rs.getString("name"));
                        group.setCreateTime(rs.getTimestamp("create_time"));
                        return group;
                    }
                }, id);
    }
    
    public List<Group> getGroupListById(long id) {
        return getJdbcTemplate().query(
                SQL_SELECT_GROUP_LIST_BY_USER_ID,
                new ParameterizedRowMapper<Group>() {
                    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Group group = new Group();
                        group.setCreateTime(rs.getTimestamp("create_time"));
                        group.setId(rs.getLong("id"));
                        group.setIntro(rs.getString("intro"));
                        group.setName(rs.getString("name"));
                        group.setUserId(rs.getLong("user_id"));
                        return group;
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

    public UserInfo getUserInfoById(long id) {
        return getJdbcTemplate().queryForObject(
                SQL_SELECT_USER_INFO_BY_ID,
                new ParameterizedRowMapper<UserInfo>(){
                    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException{
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserId(rs.getLong("user_id"));
                        userInfo.setAddress(rs.getString("address"));
                        userInfo.setBirthday(rs.getDate("birthday"));
                        userInfo.setGender(rs.getString("gender"));
                        userInfo.setNickname(rs.getString("nickname"));
                        userInfo.setPhone(rs.getString("phone"));
                        userInfo.setPublic(rs.getBoolean("is_public"));
                        userInfo.setRegisterTime(rs.getTimestamp("register_time"));
                        return userInfo;
                    }
                }, id);
    }

    public void saveUserInfo(UserInfo userInfo, long id) {
        getJdbcTemplate().update(
                SQL_SAVE_USER_INFO,
                userInfo.getNickname(),
                userInfo.getPhone(),
                userInfo.getAddress(),
                userInfo.getGender(),
                userInfo.getBirthday(),
                id);
    }
}
