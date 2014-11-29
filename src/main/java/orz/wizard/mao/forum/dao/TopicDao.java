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

import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.Topic;

@Repository
public class TopicDao extends BaseDao {
    
    private static final String SQL_SELECT_TOPIC_BY_ID = "select * from topic where topic_id = ?";
    private static final String SQL_INSERT_TOPIC = "insert into topic values(null, ?, ?, ?, ?, NOW(), 0, null)";
    private static final String SQL_SELECT_GROUP_TOPIC_BY_USER_ID = ""
            + " select topic.topic_id as topic_id, title, cmt_count, last_cmt_time, group.group_id as group_id, `name`"
            + " from `group`, topic"
            + " where group.group_id in (select group_id from membership where user_id = ?) and group.group_id = topic.group_id";
    private static final String SQL_SELECT_TOPIC_LIST_BY_GROUP_ID = ""
            + " select topic_id, title, cmt_count, last_cmt_time, user.user_id as user_id, nickname"
            + " from topic, user"
            + " where group_id = ? and topic.user_id = user.user_id";
    private static final String SQL_SELECT_COMMENT_LIST_BY_TOPIC_ID = "select * from comment where topic_id = ?";
    private static final String SQL_INSERT_COMMENT = "insert into comment values(null, ?, ?, ?, ?, now())";
    
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
    
    public Topic getTopicById(final long topicId) {
        final Topic topic = new Topic();
        jdbcTemplate.query(SQL_SELECT_TOPIC_BY_ID, new Object[] {topicId}, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                topic.setTopicId(rs.getLong("topic_id"));
                topic.setTitle(rs.getString("title"));
                topic.setContent(rs.getString("content"));
                topic.setUserId(rs.getLong("user_id"));
                topic.setNickname(rs.getString("nickname"));
                topic.setGroupId(rs.getLong("group_id"));
                topic.setGroupName(rs.getString("name"));
                topic.setPublishTime(rs.getTimestamp("publish_time"));
            }
        });
        return topic;
    }
    
    public void insertTopic(final Topic topic) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_TOPIC, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, topic.getTitle());
                ps.setString(2, topic.getContent());
                ps.setLong(3, topic.getUserId());
                ps.setLong(4, topic.getGroupId());
                return ps;
            }
        }, keyHolder);
        topic.setTopicId(keyHolder.getKey().intValue());
    }
    
    public List<Comment> getCommentListByTopicId(long topicId) {
        return jdbcTemplate.query(SQL_SELECT_COMMENT_LIST_BY_TOPIC_ID, new Object[] {topicId}, new RowMapper<Comment>() {
            public Comment mapRow(ResultSet rs, int index) throws SQLException {
                Comment comment = new Comment();
                comment.setCommentId(rs.getLong("comment_id"));
                comment.setTopicId(rs.getLong("topic_id"));
                comment.setUserId(rs.getLong("user_id"));
                comment.setToCommentId(rs.getLong("to_comment_id"));
                comment.setText(rs.getString("text"));
                comment.setCommentTime(rs.getTimestamp("comment_time"));
                return comment;
            }
        });
    }
    
    public void insertComment(final Comment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, comment.getTopicId());
                ps.setLong(2, comment.getUserId());
                ps.setLong(3, comment.getToCommentId());
                ps.setString(4, comment.getText());
                return ps;
            }
        }, keyHolder);
        comment.setCommentId(keyHolder.getKey().intValue());
    }
}