package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class SysMsg implements Serializable {
    private long msgId;
    private long toUserId;
    private String content;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    
}
