package orz.wizard.mao.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.SysMsg;
import orz.wizard.mao.forum.entity.Topic;
import orz.wizard.mao.forum.entity.User;

@Repository
public class MessageDao extends BaseDao {

    private static final String SQL_SELECT_MSG_TOPIC_LIST = ""
            + " select topic.topic_id as topic_id, topic.user_id as user_id, nickname"
            + " from topic, msg_topic, user"
            + " where to_user_id = ? and is_readed = 0 and msg_topic.topic_id = topic.topic_id and user.user_id = topic.user_id"
            + " order by msg_time desc";
    private static final String SQL_SET_TOPIC_READ = "update msg_topic set is_readed = 1 where topic_id = ? and to_user_id = ?";
    private static final String SQL_SELECT_MSG_CMT_LIST = ""
            + " select cmt_id, user.user_id as user_id, nickname, topic.topic_id as topic_id, title"
            + " from msg_cmt, comment, topic, user"
            + " where to_user_id = ? and is_readed = 0 and cmt_id = comment_id and comment.topic_id = topic.topic_id and comment.user_id = user.user_id"
            + " order by msg_time desc";
    private static final String SQL_SET_CMT_READ = "update msg_cmt set is_readed = 1 where cmt_id = ? and to_user_id = ?";
    private static final String SQL_SELECT_MSG_SYS_LIST = "select * from msg_sys where to_user_id = ? and is_readed = 0";
    private static final String SQL_SET_SYS_MSG_READ = "update msg_sys set is_readed = 1 where msg_id = ?";
    protected static final String SQL_INSERT_SYS_MSG = "insert into msg_sys values(null, ?, ?, 0, NOW())";

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

    public void setReadTopic(long topicId, long userId) {
        jdbcTemplate.update(SQL_SET_TOPIC_READ, topicId, userId);
    }

    public List<Comment> getCmtMsgList(long userId) {
        return jdbcTemplate.query(SQL_SELECT_MSG_CMT_LIST, new Object[] {userId}, new RowMapper<Comment>() {
            public Comment mapRow(ResultSet rs, int index) throws SQLException {
                Comment comment = new Comment();
                comment.setCommentId(rs.getLong("cmt_id"));
                comment.setUserId(rs.getLong("user_id"));
                comment.setNickname(rs.getString("nickname"));
                comment.setTopicId(rs.getLong("topic_id"));
                comment.setTitle(rs.getString("title"));
                return comment;
            }
        });
    }

    public void setReadCmt(long cmtId, long userId) {
        jdbcTemplate.update(SQL_SET_CMT_READ, cmtId, userId);
    }

    public List<SysMsg> getSysMsgList(long userId) {
        return jdbcTemplate.query(SQL_SELECT_MSG_SYS_LIST, new Object[] {userId}, new RowMapper<SysMsg>() {
            public SysMsg mapRow(ResultSet rs, int index) throws SQLException {
                SysMsg sysMsg = new SysMsg();
                sysMsg.setMsgId(rs.getLong("msg_id"));
                sysMsg.setContent(rs.getString("content"));
                sysMsg.setReaded(rs.getBoolean("is_readed"));
                sysMsg.setMsgTime(rs.getTimestamp("msg_time"));
                return sysMsg;
            }
        });
    }
    
    public void setReadSysMsg(long msgId) {
        jdbcTemplate.update(SQL_SET_SYS_MSG_READ, msgId);
    }
    
    public void insertSysMsg(final SysMsg sysMsg) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_SYS_MSG, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, sysMsg.getToUserId());
                ps.setString(2, sysMsg.getContent());
                return ps;
            }
        }, keyHolder);
        sysMsg.setMsgId(keyHolder.getKey().intValue());
    }
}
