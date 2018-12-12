package qzlife.net.zhrs.entity.dto;

/**
 * app端登录用户信息实体
 */
public class AppUserDto {

    private String userId;
    private String phone;
    private long loginTime;

    public AppUserDto(Builder builder) {
        this.userId = userId;
        this.phone = phone;
        this.loginTime = loginTime;
    }

    public static class Builder {
        private String userId;
        private String phone;
        private long loginTime;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder loginTime(long loginTime) {
            this.loginTime = loginTime;
            return this;
        }

        public AppUserDto builser() {
            return new AppUserDto(this);
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public long getLoginTime() {
        return loginTime;
    }
}
