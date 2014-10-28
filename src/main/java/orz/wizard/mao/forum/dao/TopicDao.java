package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import orz.wizard.mao.forum.entity.Topic;

public class TopicDao extends JdbcDaoSupport {
    
    private static final String SQL_SELECT_TOPIC_BY_ID = "select * from topic where id = ?";
    private static final String SQL_SELECT_TOPIC_LIST_BY_GROUP_ID = "select * from topic where group_id = ?";
    private static final String SQL_SAVE_TOPIC = "insert into topic values(null, ?, ?, ?, null, ?, now())";
    private static final String SQL_SELECT_TOPIC_COMMENT_GROUP_LIST_BY_USER_ID =
            "select topic.title, count(comment.id), max(comment.comment_time), group.name" + 
            "from topic join comment join group" + 
            "on topic.id = comment.topic_id and topic.group_id = group.id" +
            "where topic.group_id in" +
            "(" + 
            "select group.id from" +
            "group join membership on group.id = membership.group_id" +
            "where membership.user_id = ?" +
            ")" +
            "group by topic.title";
    
    public Topic getTopicById(long id){
        return getJdbcTemplate().queryForObject(
                SQL_SELECT_TOPIC_BY_ID,
                new ParameterizedRowMapper<Topic>(){
                    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException{
                        Topic topic = new Topic();
                        topic.setId(rs.getLong("id"));
                        topic.setGroupId(rs.getLong("group_id"));
                        topic.setPublishTime(rs.getTimestamp("publish_time"));
                        topic.setText(rs.getString("text"));
                        topic.setTitle(rs.getString("title"));
                        topic.setUserId(rs.getLong("user_id"));
                        return topic;
                    }
                }, id);
    }
    
    public List<Topic> getTopicListById(long id) {
        return getJdbcTemplate().query(
                SQL_SELECT_TOPIC_LIST_BY_GROUP_ID,
                new ParameterizedRowMapper<Topic>() {
                    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Topic topic = new Topic();
                        topic.setId(rs.getLong("id"));
                        topic.setUserId(rs.getLong("user_id"));
                        topic.setGroupId(rs.getLong("group_id"));
                        topic.setTitle(rs.getString("title"));
                        topic.setText(rs.getString("text"));
                        topic.setPublishTime(rs.getTimestamp("publish_time"));
                        return topic;
                    }
                }, id);
    }
    
    public void saveTopic(final Topic topic){
    	getJdbcTemplate().update(SQL_SAVE_TOPIC, topic.getUserId(), topic.getTitle(), topic.getText(), topic.getGroupId());
    }
}
