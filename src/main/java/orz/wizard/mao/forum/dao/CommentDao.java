package orz.wizard.mao.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import orz.wizard.mao.forum.entity.Comment;
import orz.wizard.mao.forum.entity.Topic;

public class CommentDao extends JdbcDaoSupport {
    
    private static final String SQL_SELECT_COMMENT_LIST_BY_TOPIC_ID = "select * from comment where topic_id = ?";
    private static final String SQL_SAVE_COMMENT = "insert into comment values(null, ?, ?, ?, ?, now())";
    
    public List<Comment> getCommentListById(long id) {
        return getJdbcTemplate().query(
                SQL_SELECT_COMMENT_LIST_BY_TOPIC_ID,
                new ParameterizedRowMapper<Comment>() {
                    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Comment comment = new Comment();
                        comment.setId(rs.getLong("id"));
                        comment.setUserId(rs.getLong("user_id"));
                        comment.setTopicId(rs.getLong("topic_id"));
                        comment.setToId(rs.getLong("to_id"));
                        comment.setText(rs.getString("text"));
                        comment.setCommentTime(rs.getTimestamp("comment_time"));
                        return comment;
                    }
                }, id);
    }
    
    public void saveComment(final Comment comment){
    	getJdbcTemplate().update(SQL_SAVE_COMMENT, comment.getToId(), comment.getUserId(), comment.getToId(), comment.getText());
    }
}
