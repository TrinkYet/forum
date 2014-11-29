package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private long commentId;
    private long topicId;
    private long userId;
    private String nickname;
    private long toCommentId;
    private String text;
    private Timestamp commentTime;
    public long getCommentId() {
        return commentId;
    }
    public void setCommentId(long commentId) {
        this.commentId = commentId;
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
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public long getToCommentId() {
        return toCommentId;
    }
    public void setToCommentId(long toCommentId) {
        this.toCommentId = toCommentId;
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
