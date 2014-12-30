package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class InviteMsg implements Serializable {
    private long msgId;
    private long fromUserId;
    private String nickname;
    private long toUserId;
    private long groupId;
    private String name;
    private boolean isReaded;
    private Timestamp msgTime;
    public long getMsgId() {
        return msgId;
    }
    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
    public long getToUserId() {
        return toUserId;
    }
    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }
    public boolean isReaded() {
        return isReaded;
    }
    public void setReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }
    public Timestamp getMsgTime() {
        return msgTime;
    }
    public void setMsgTime(Timestamp msgTime) {
        this.msgTime = msgTime;
    }
    public long getFromUserId() {
        return fromUserId;
    }
    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
