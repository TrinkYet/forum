package orz.wizard.mao.forum.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class UserInfo {
    private long userId;
    private String nickname;
    private String phone;
    private String address;
    private Timestamp registerTime;
    private String gender;
    private Date birthday;
    private boolean isPublic;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Timestamp getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
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
    public boolean isPublic() {
        return isPublic;
    }
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
