package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class UserInfo implements Serializable {
    private long userId;
    private String gender;
    private Date birthday;
    private String residence;
    private String hometown;
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getResidence() {
        return residence;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }
    public String getHometown() {
        return hometown;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    
}
