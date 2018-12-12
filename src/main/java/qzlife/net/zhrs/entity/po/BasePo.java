package qzlife.net.zhrs.entity.po;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基本po
 */
public class BasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 创建时间
    private LocalDateTime gmtCreat;

    // 更新时间
    private LocalDateTime gmtModified;

    public LocalDateTime getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(LocalDateTime gmtCreat) {
        this.gmtCreat = gmtCreat;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}
