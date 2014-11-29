package orz.wizard.mao.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.Group;

@Repository
public class GroupDao extends BaseDao {
    
    private static final String SQL_SELECT_GROUP_BY_ID = "select * from `group` where group_id = ?";
    private static final String SQL_INSERT_GROUP = "insert into `group` values(null, ?, ?, ?, ?, now())";
    private static final String SQL_INSERT_MEMBERSHIP = "insert into `membership` values(?, ?, now())";
    private static final String SQL_FIND_MEMBERSHIP = "select count(*) from membership where group_id = ? and user_id = ?";
    private static final String SQL_DELETE_MEMBERSHIP = "delete from membership where group_id = ? and user_id = ?";
    
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
}
