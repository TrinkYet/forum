package orz.wizard.mao.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.entity.UserInfo;

@Repository
public class UserDao extends BaseDao {
    
    private static final String SQL_SELECT_USER_BY_ID = "select * from user where user_id = ?";
    private static final String SQL_INSERT_USER = "insert into user values(null, ?, ?, ?, 'images/user_normal.jpg', 'unactivated', NOW())";
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from user where email = ?";
    private static final String SQL_SELECT_USER_INFO_BY_ID = "select * from user_info where user_id = ?";
    private static final String SQL_UPDATE_USER_INFO = "update user_info set gender = ?, birthday = ?, residence = ?, hometown = ? where user_id = ?";
    private static final String SQL_SELECT_GROUP_LIST_BY_USER_ID = "select * from `group` where group_id in (select group_id from membership where user_id = ?)";
    private static final String SQL_SELECT_FOLLOWER_LIST = "select * from user where user_id in (select from_user_id from follow where to_user_id = ?)";
    private static final String SQL_SELECT_FOLLOWEE_LIST = "select * from user where user_id in (select to_user_id from follow where from_user_id = ?)";
    private static final String SQL_FIND_FOLLOW = "select count(*) from follow where from_user_id = ? and to_user_id = ?";
    private static final String SQL_INSERT_FOLLOW = "insert into follow values(null, ?, ?)";
    private static final String SQL_DELETE_FOLLOW = "delete from follow where from_user_id = ? and to_user_id = ?";
    private static final String SQL_UPDATE_USER_AVATAR = "update user set avatar = ? where user_id = ?";
    private static final String SQL_INSERT_CODE = "insert into activation_code values(?, ?)";
    private static final String SQL_FIND_CODE = "select count(*) from activation_code where user_id = ? and code = ?";
    private static final String SQL_UPDATE_USER_STATUS = "update user set status = 'activated' where user_id = ?";
    private static final String SQL_SELECT_CREATED_LIST = "select * from `group` where user_id = ?";
    
    public User getUserById(final long userId) {
        final User user = new User();
        jdbcTemplate.query(SQL_SELECT_USER_BY_ID, new Object[] {userId}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                user.setUserId(rs.getLong("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getString("status"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
            }
        });
        return user;
    }
    
    public User getUserByEmail(final String email) {
        final User user = new User();
    	jdbcTemplate.query(SQL_SELECT_USER_BY_EMAIL, new Object[] {email}, new RowCallbackHandler() {
    	    public void processRow(ResultSet rs) throws SQLException {
    	        user.setUserId(rs.getLong("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getString("status"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
    	    }
    	});
    	return user;
    }
    
    public void insertUser(final User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getNickname());
                return ps;
            }
        }, keyHolder);
        user.setUserId(keyHolder.getKey().intValue());
    }

    public UserInfo getUserInfoById(long userId) {
        final UserInfo userInfo = new UserInfo();
        jdbcTemplate.query(SQL_SELECT_USER_INFO_BY_ID, new Object[] {userId}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                userInfo.setUserId(rs.getLong("user_id"));
                userInfo.setGender(rs.getString("gender"));
                userInfo.setBirthday(rs.getDate("birthday"));
                userInfo.setResidence(rs.getString("residence"));
                userInfo.setHometown(rs.getString("hometown"));
            }
        });
        return userInfo;
    }

    public void updateUserInfo(UserInfo userInfo) {
        jdbcTemplate.update(
                SQL_UPDATE_USER_INFO,
                userInfo.getGender(),
                userInfo.getBirthday(),
                userInfo.getResidence(),
                userInfo.getHometown(),
                userInfo.getUserId());
    }
    
    public List<Group> getGroupListByUserId(final long userId) {
        return jdbcTemplate.query(SQL_SELECT_GROUP_LIST_BY_USER_ID, new Object[] {userId}, new RowMapper<Group>() {
            public Group mapRow(ResultSet rs, int index) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("group_id"));
                group.setName(rs.getString("name"));
                group.setIntro(rs.getString("intro"));
                group.setCategory(rs.getString("category"));
                group.setUserId(rs.getLong("user_id"));
                group.setCreateTime(rs.getTimestamp("create_time"));
                return group;
            }
        });
    }

    public List<User> getFollowerList(long userId) {
        return jdbcTemplate.query(SQL_SELECT_FOLLOWER_LIST, new Object[] {userId}, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int index) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getString("status"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
                return user;
            }
        });
    }
    
    public List<User> getFolloweeList(long userId) {
        return jdbcTemplate.query(SQL_SELECT_FOLLOWEE_LIST, new Object[] {userId}, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int index) throws SQLException {
                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getString("status"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
                return user;
            }
        });
    }
    
    public int findFollow(long fromUserId, long toUserId) {
        Integer count = jdbcTemplate.queryForObject(SQL_FIND_FOLLOW, Integer.class, fromUserId, toUserId);
        return count == null ? 0 : count;
    }

    public void doFollow(long fromUserId, long toUserId) {
        jdbcTemplate.update(SQL_INSERT_FOLLOW, fromUserId, toUserId);
    }
    
    public void cancelFollow(long fromUserId, long toUserId) {
        jdbcTemplate.update(SQL_DELETE_FOLLOW, fromUserId, toUserId);
    }

    public void updateAvatar(long userId) {
        jdbcTemplate.update(SQL_UPDATE_USER_AVATAR, "avatar/user/" + userId + ".jpg", userId);
    }

    public void insertCode(long userId, String code) {
        jdbcTemplate.update(SQL_INSERT_CODE, userId, code);
    }

    public boolean activate(long userId, String code) {
        Integer count = jdbcTemplate.queryForObject(SQL_FIND_CODE, Integer.class, userId, code);
        if (count == null || count < 1) {
            return false;
        }
        jdbcTemplate.update(SQL_UPDATE_USER_STATUS, userId);
        return true;
    }

    public List<Group> getCreatedList(long userId) {
        return jdbcTemplate.query(SQL_SELECT_CREATED_LIST, new Object[] {userId}, new RowMapper<Group>() {
            public Group mapRow(ResultSet rs, int index) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("group_id"));
                group.setName(rs.getString("name"));
                group.setIntro(rs.getString("intro"));
                group.setCategory(rs.getString("category"));
                group.setUserId(rs.getLong("user_id"));
                group.setCreateTime(rs.getTimestamp("create_time"));
                return group;
            }
        });
    }
}
