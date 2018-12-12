package qzlife.net.zhrs.entity.po;/**
 * @Author: Czz
 * @Date: 10/12/2018 4:58 PM
 */

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: zhrs
 * @description: App端用户token记录映射实体
 * @author: Czz
 * @create: 2018-12-10 16:58
 **/
@Table(name = "rs_app_token")
public class AppTokenPo implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String userId;

    private String token;

    private LocalDateTime gmtModified;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "AppTokenPo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
