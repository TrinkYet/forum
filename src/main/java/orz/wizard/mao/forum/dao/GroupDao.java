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
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Membership;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;

@Repository
public class GroupDao extends BaseDao {
    
    private static final String SQL_SELECT_GROUP_BY_ID = "select * from `group` where group_id = ?";
    private static final String SQL_INSERT_GROUP = "insert into `group` values(null, ?, ?, ?, ?, now())";
    private static final String SQL_SAVE_MEMBERSHIP = "insert into `membership` values(?, ?, now())";
    private static final String SQL_SELECT_MEMBERSHIP_BY_TWO_ID = "select * from `membership` where group_id = ? and user_id = ?";
    private static final String SQL_SELECT_GROUP_BY_NAME = "select * from `group` where name = ?";
    private static final String SQL_SELECT_USER_INFO_BY_ID = "select * from user_info where user_id = ?";
    private static final String SQL_SAVE_USER_INFO = "update user_info set nickname = ?, phone = ?, address = ?, gender = ?, birthday = ? where user_id = ?";
    
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
                ps.setString(3, group.getCategory());
                ps.setLong(4, group.getUserId());
                return ps;
            }
        }, keyHolder);
        group.setGroupId(keyHolder.getKey().intValue());
    }
    
    public Membership getMembershipByTwoId(long userId, long groupId) {
        List<Membership> list = jdbcTemplate.query(
                SQL_SELECT_MEMBERSHIP_BY_TWO_ID,
                new ParameterizedRowMapper<Membership>(){
                    public Membership mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Membership membership = new Membership();
                        membership.setGroupId(rs.getLong("group_id"));
                        membership.setUserId(rs.getLong("user_id"));
                        membership.setJoinTime(rs.getTimestamp("join_time"));
                        return membership;
                    }
                }, groupId, userId);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    public void joinGroup(long userId, long groupId) {
        jdbcTemplate.update(SQL_SAVE_MEMBERSHIP, groupId, userId);
    }
}
