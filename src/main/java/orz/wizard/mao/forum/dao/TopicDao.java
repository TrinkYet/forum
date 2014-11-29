package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.Topic;

@Repository
public class TopicDao extends BaseDao {
    
    private static final String SQL_SELECT_TOPIC_BY_ID = "select * from topic where id = ?";
    private static final String SQL_SAVE_TOPIC = "insert into topic values(null, ?, ?, null, ?, ?, now())";
    private static final String SQL_SELECT_TOPIC_BY_TITLE = "select * from topic where title = ?";
    private static final String SQL_SELECT_GROUP_TOPIC_BY_USER_ID = ""
            + " select topic.topic_id as topic_id, title, cmt_count, last_cmt_time, group.group_id as group_id, `name`"
            + " from `group`, topic"
            + " where group.group_id in (select group_id from membership where user_id = ?) and group.group_id = topic.group_id";
    private static final String SQL_SELECT_TOPIC_LIST_BY_GROUP_ID = ""
            + " select topic_id, title, cmt_count, last_cmt_time, user.user_id as user_id, nickname"
            + " from topic, user"
            + " where group_id = ? and topic.user_id = user.user_id";
    
    public List<Topic> getGroupTopicListByUserId(final long userId) {
        return jdbcTemplate.query(SQL_SELECT_GROUP_TOPIC_BY_USER_ID, new Object[] {userId}, new RowMapper<Topic>() {
            public Topic mapRow(ResultSet rs, int index) throws SQLException {
                Topic topic = new Topic();
                topic.setTopicId(rs.getLong("topic_id"));
                topic.setTitle(rs.getString("title"));
                topic.setCmtCount(rs.getLong("cmt_count"));
                topic.setLastCmtTime(rs.getTimestamp("last_cmt_time"));
                topic.setGroupId(rs.getLong("group_id"));
                topic.setGroupName(rs.getString("name"));
                return topic;
            }
        });
    }
    
    public List<Topic> getTopicListByGroupId(long groupId) {
        return jdbcTemplate.query(SQL_SELECT_TOPIC_LIST_BY_GROUP_ID, new Object[] {groupId}, new RowMapper<Topic>() {
            public Topic mapRow(ResultSet rs, int index) throws SQLException {
                Topic topic = new Topic();
                topic.setTopicId(rs.getLong("topic_id"));
                topic.setTitle(rs.getString("title"));
                topic.setUserId(rs.getLong("user_id"));
                topic.setNickname(rs.getString("nickname"));
                topic.setCmtCount(rs.getLong("cmt_count"));
                topic.setLastCmtTime(rs.getTimestamp("last_cmt_time"));
                return topic;
            }
        });
    }
    
    public Topic getTopicById(long id){
        return jdbcTemplate.queryForObject(
                SQL_SELECT_TOPIC_BY_ID,
                new ParameterizedRowMapper<Topic>(){
                    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException{
                        Topic topic = new Topic();
                        topic.setTopicId(rs.getLong("topic_id"));
                        topic.setGroupId(rs.getLong("group_id"));
                        topic.setPublishTime(rs.getTimestamp("publish_time"));
                        topic.setContent(rs.getString("content"));
                        topic.setTitle(rs.getString("title"));
                        topic.setUserId(rs.getLong("user_id"));
                        return topic;
                    }
                }, id);
    }
    
    public Topic saveTopic(long groupId, long userId, final Topic topic){
        jdbcTemplate.update(SQL_SAVE_TOPIC, topic.getTitle(), topic.getContent(), userId, groupId);
        return jdbcTemplate.queryForObject(
                SQL_SELECT_TOPIC_BY_TITLE,
                new ParameterizedRowMapper<Topic>(){
                    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Topic topic = new Topic();
                        topic.setTopicId(rs.getLong("topic_id"));
                        topic.setUserId(rs.getLong("user_id"));
                        topic.setGroupId(rs.getLong("group_id"));
                        topic.setTitle(rs.getString("title"));
                        topic.setContent(rs.getString("content"));
                        topic.setPublishTime(rs.getTimestamp("publish_time"));
                        return topic;
                    }
                }, topic.getTitle());
    }
}
