package qzlife.net.zhrs.entity.po;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 手机用户验证用户类
 */
@Table(name = "rs_phone_auth")
public class PhoneAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String userId;
    // 手机号
    private String phone;
    // 密码
    private String password;
    // 盐
    private String salt;
    // 创建时间
    private LocalDateTime gmtCreate;

    // 更新时间
    private LocalDateTime gmtModified;

    public PhoneAuth() {
    }

    public PhoneAuth(Builder builder) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public static class Builder {
        private Long id;
        private String userId;
        private String phone;
        private String password;
        private String salt;
        private LocalDateTime gmtCreate;
        private LocalDateTime gmtModified;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public Builder gmtCreate(LocalDateTime gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public Builder gmtModified(LocalDateTime gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public PhoneAuth builder() {
            return new PhoneAuth(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "PhoneAuth{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
