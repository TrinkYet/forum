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

@Repository
public class GroupDao extends BaseDao {
    
    private static final String SQL_SELECT_GROUP_BY_ID = "select * from `group` where group_id = ?";
    private static final String SQL_INSERT_GROUP = "insert into `group` values(null, ?, ?, ?, ?, ?, NOW())";
    private static final String SQL_INSERT_MEMBERSHIP = "insert into `membership` values(?, ?, NOW())";
    private static final String SQL_FIND_MEMBERSHIP = "select count(*) from membership where group_id = ? and user_id = ?";
    private static final String SQL_DELETE_MEMBERSHIP = "delete from membership where group_id = ? and user_id = ?";
    private static final String SQL_SELECT_RECENT_USER_LIST = "select user.* from membership, user where group_id = ? and membership.user_id = user.user_id order by join_time desc limit 8";
    private static final String SQL_SELECT_USER_LIST = "select user.* from membership, user where group_id = ? and membership.user_id = user.user_id";
    private static final String SQL_SELECT_USER_COUNT = "select count(*) from membership where group_id = ?";
    //private static final String SQL_SEARCH_GROUP_LIST = "select * from `group` where `group`.`name` like ?";
    private static final String SQL_SEARCH_GROUP_LIST = "select *, count(distinct(membership.`user_id`)) as mbr_count from `group` left join membership on `group`.group_id = membership.group_id where `group`.`name` like ? group by `group`.`group_id`";
    private static final String SQL_UPDATE_GROUP = "update `group` set name = ?, intro = ?, category = ?, avatar = ? where group_id = ?";
    
    public Group getGroupById(final long groupId) {
        final Group group = new Group();
        jdbcTemplate.query(SQL_SELECT_GROUP_BY_ID, new Object[] {groupId}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                group.setGroupId(rs.getLong("group_id"));
                group.setName(rs.getString("name"));
                group.setIntro(rs.getString("intro"));
                group.setCategory(rs.getString("category"));
                group.setUserId(rs.getLong("user_id"));
                group.setCreateTime(rs.getTimestamp("create_time"));
            }
        });
        return group;
    }
    
    public void insertGroup(final Group group) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, group.getName());
                ps.setString(2, group.getIntro());
                ps.setString(3, group.getAvatar());
                ps.setString(4, group.getCategory());
                ps.setLong(5, group.getUserId());
                return ps;
            }
        }, keyHolder);
        group.setGroupId(keyHolder.getKey().intValue());
    }
    
    public int findMembership(long groupId, long userId) {
        Integer count = jdbcTemplate.queryForObject(SQL_FIND_MEMBERSHIP, Integer.class, groupId, userId);
        return count == null ? 0 : count;
    }
    
    public void joinGroup(long groupId, long userId) {
        jdbcTemplate.update(SQL_INSERT_MEMBERSHIP, groupId, userId);
    }
    
    public void quitGroup(long groupId, long userId) {
        jdbcTemplate.update(SQL_DELETE_MEMBERSHIP, groupId, userId);
    }

    public List<User> getRecentUserList(long groupId) {
        return jdbcTemplate.query(SQL_SELECT_RECENT_USER_LIST, new Object[] {groupId}, new RowMapper<User>() {
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

    public int getUserCount(long groupId) {
        Integer count = jdbcTemplate.queryForObject(SQL_SELECT_USER_COUNT, Integer.class, groupId);
        return count == null ? 0 : count;
    }

    public List<User> getUserListById(long groupId) {
        return jdbcTemplate.query(SQL_SELECT_USER_LIST, new Object[] {groupId}, new RowMapper<User>() {
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

    public List<Group> searchGroup(String q){
        return jdbcTemplate.query(SQL_SEARCH_GROUP_LIST, new Object[] {"%%"+q+"%%"}, new RowMapper<Group>() {
            public Group mapRow(ResultSet rs, int index) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("group_id"));
                group.setName(rs.getString("name"));
                group.setIntro(rs.getString("intro"));
                group.setCategory(rs.getString("category"));
               // group.setAvatar(rs.getString("avatar"));
                group.setMbrCount(rs.getLong("mbr_count"));
                group.setUserId(rs.getLong("user_id"));
                group.setCreateTime(rs.getTimestamp("create_time"));
                return group;
            }
        });
    }

    public void saveGroup(final Group group) {
       // jdbcTemplate.update(SQL_UPDATE_GROUP, group.getName(), group.getIntro(), group.getCategory(), group.getAvatar(), group.getGroupId());
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_GROUP);
                ps.setString(1, group.getName());
                ps.setString(2, group.getIntro());
                ps.setString(3, group.getCategory());
                ps.setString(4, group.getAvatar());
                ps.setLong(5, group.getGroupId());
                return ps;
            }
        });
    }
}
