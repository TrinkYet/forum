package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Group implements Serializable {
    private long groupId;
    private String name;
    private String intro;
    private String category;
    private String avatar;
    private long mbrCount;
    private long userId;
    private Timestamp createTime;
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
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public long getMbrCount() {
        return mbrCount;
    }
    public void setMbrCount(long mbrCount) {
        this.mbrCount = mbrCount;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
}
