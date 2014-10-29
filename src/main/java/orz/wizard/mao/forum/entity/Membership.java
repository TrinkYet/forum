package orz.wizard.mao.forum.entity;

import java.sql.Timestamp;

public class Membership {
    
    private long userId;
    private long groupId;
    private Timestamp joinTime;
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
    public Timestamp getJoinTime() {
        return joinTime;
    }
    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }
    
    

}
