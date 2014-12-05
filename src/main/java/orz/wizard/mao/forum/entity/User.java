package orz.wizard.mao.forum.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class User implements Serializable {
    private long userId;
    
    @Email(message = "邮箱格式错误")
    private String email;
    
    @Size(min = 6, max = 20, message = "密码长度必须在6到20之间")
    private String password;
    
    @NotNull(message = "昵称不能为空")
    private String nickname;
    
    private String avatar;
    private String status;
    private Timestamp registerTime;
    
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
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Timestamp getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
