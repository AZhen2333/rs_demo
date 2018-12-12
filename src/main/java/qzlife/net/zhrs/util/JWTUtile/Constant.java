package qzlife.net.zhrs.util.JWTUtile;

import java.util.UUID;

public class Constant {
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "woyebuzhidaoxiediansha";

    /**
     * 过期时长 millisecond
     */
    public static final int JWT_TTL = 60 * 60 * 1000;
}
