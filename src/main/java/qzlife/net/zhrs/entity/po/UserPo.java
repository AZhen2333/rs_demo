package qzlife.net.zhrs.entity.po;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "rs_user")
public class UserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    // 用户id
    private String userId;
    // 绑定的手机号
    private String phone;
    // 昵称
    private String nickName;
    // 头像路径
    private String headUrl;
    // 邮箱
    private String email;
    // 性别：0-女，1-男
    private String sex;

    // 创建时间
    private LocalDateTime gmtCreate;

    // 更新时间
    private LocalDateTime gmtModified;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
