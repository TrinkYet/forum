package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.Group;
import orz.wizard.mao.forum.entity.Membership;

@Repository
public class GroupDao extends BaseDao {
    
    private static final String SQL_SELECT_GROUP_BY_ID = "select * from `group` where id = ?";
    private static final String SQL_SAVE_GROUP = "insert into `group` values(null, ?, ?, ?, ?, now())";
    private static final String SQL_SAVE_MEMBERSHIP = "insert into `membership` values(?, ?, now())";
    private static final String SQL_SELECT_MEMBERSHIP_BY_TWO_ID = "select * from `membership` where group_id = ? and user_id = ?";
    private static final String SQL_SELECT_GROUP_BY_NAME = "select * from `group` where name = ?";
    private static final String SQL_SELECT_USER_INFO_BY_ID = "select * from user_info where user_id = ?";
    private static final String SQL_SAVE_USER_INFO = "update user_info set nickname = ?, phone = ?, address = ?, gender = ?, birthday = ? where user_id = ?";
    
    public Group getGroupById(long id) {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_GROUP_BY_ID,
                new ParameterizedRowMapper<Group>() {
                    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Group group = new Group();
                        group.setGroupId(rs.getLong("group_id"));
                        group.setUserId(rs.getLong("user_id"));
                        group.setIntro(rs.getString("intro"));
                        group.setName(rs.getString("name"));
                        group.setCreateTime(rs.getTimestamp("create_time"));
                        return group;
                    }
                }, id);
    }
    
    public Group saveGroup(final Group group, long userId){
    	jdbcTemplate.update(SQL_SAVE_GROUP, userId, group.getName(), group.getIntro(), group.getCategory());
    	return jdbcTemplate.queryForObject(
    	        SQL_SELECT_GROUP_BY_NAME,
    			new ParameterizedRowMapper<Group>(){
    				public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
    					group.setCreateTime(rs.getTimestamp("create_time"));
                        group.setGroupId(rs.getLong("group_id"));
                        group.setIntro(rs.getString("intro"));
                        group.setName(rs.getString("name"));
                        group.setUserId(rs.getLong("user_id"));
                        group.setCategory(rs.getString("category"));
                        return group;
    				}
    			}, group.getName());
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
