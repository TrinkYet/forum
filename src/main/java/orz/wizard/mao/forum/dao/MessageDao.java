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
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;

@Repository
public class MessageDao extends BaseDao {

    private static final String SQL_SELECT_MSG_TOPIC_LIST = ""
            + " select topic.topic_id as topic_id, topic.user_id as user_id, nickname"
            + " from topic, msg_topic"
            + " where to_user_id = ? and is_readed = 0 and msg_topic.topic_id = topic.topic_id"
            + " order by msg_time desc";
    private static final String SQL_SET_TOPIC_READ = "update msg_topic set is_readed = 1 where topic_id = ? and to_user_id = ?";

    public List<Topic> getTopicMsgList(long userId) {
        return jdbcTemplate.query(SQL_SELECT_MSG_TOPIC_LIST, new Object[] {userId}, new RowMapper<Topic>() {
            public Topic mapRow(ResultSet rs, int index) throws SQLException {
                Topic topic = new Topic();
                topic.setTopicId(rs.getLong("topic_id"));
                topic.setUserId(rs.getLong("user_id"));
                topic.setNickname(rs.getString("nickname"));
                return topic;
            }
        });
    }

    public void setRead(long topicId, long userId) {
        jdbcTemplate.update(SQL_SET_TOPIC_READ, topicId, userId);
    }
}
