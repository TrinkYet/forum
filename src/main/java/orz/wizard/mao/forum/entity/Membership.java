package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Membership implements Serializable {
    private long groupId;
    private long userId;
    private Timestamp joinTime;
    public long getGroupId() {
        return groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public Timestamp getJoinTime() {
        return joinTime;
    }
    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }
    
}
