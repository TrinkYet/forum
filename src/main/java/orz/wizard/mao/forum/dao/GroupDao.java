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
    
    private static final String SQL_SELECT_GROUP_BY_ID = "select * from `group` where id = ?";
    private static final String SQL_SELECT_GROUP_LIST_BY_USER_ID = "select * from `group` where user_id = ?";
    private static final String SQL_SAVE_GROUP = "insert into `group` values(null, ?, ?, ?, ?, now())";
    private static final String SQL_SELECT_GROUP_BY_NAME = "select * from `group` where name = ?";
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
    
    public Group saveGroup(final Group group, long userId){
    	getJdbcTemplate().update(SQL_SAVE_GROUP, userId, group.getName(), group.getIntro(), group.getCategory());
    	return getJdbcTemplate().queryForObject(
    	        SQL_SELECT_GROUP_BY_NAME,
    			new ParameterizedRowMapper<Group>(){
    				public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
    					group.setCreateTime(rs.getTimestamp("create_time"));
                        group.setId(rs.getLong("id"));
                        group.setIntro(rs.getString("intro"));
                        group.setName(rs.getString("name"));
                        group.setUserId(rs.getLong("user_id"));
                        group.setCategory(rs.getString("category"));
                        return group;
    				}
    			}, group.getName());
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
