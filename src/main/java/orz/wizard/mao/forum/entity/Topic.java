package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Topic implements Serializable {
    private long topicId;
    private String title;
    private String content;
    private long userId;
    private String nickname;
    private long groupId;
    private String groupName;
    private Timestamp publishTime;
    private long cmtCount;
    private Timestamp lastCmtTime;
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
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Timestamp getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }
    public long getCmtCount() {
        return cmtCount;
    }
    public void setCmtCount(long cmtCount) {
        this.cmtCount = cmtCount;
    }
    public Timestamp getLastCmtTime() {
        return lastCmtTime;
    }
    public void setLastCmtTime(Timestamp lastCmtTime) {
        this.lastCmtTime = lastCmtTime;
    }
}
