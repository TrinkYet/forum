package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Topic implements Serializable {
    private long topicId;
    private String title;
    private String content;
    private long userId;
    private long groupId;
    private Timestamp publishTime;
    public long getTopicId() {
        return topicId;
    }
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public Timestamp getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
    
}
