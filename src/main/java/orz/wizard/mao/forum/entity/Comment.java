package orz.wizard.mao.forum.entity;

import java.sql.Timestamp;

public class Comment {
    private long id;
    private long topicId;
    private long userId;
    private long toId;
    private String text;
    private Timestamp commentTime;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTopicId() {
        return topicId;
    }
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getToId() {
        return toId;
    }
    public void setToId(long toId) {
        this.toId = toId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Timestamp getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }
    
}
