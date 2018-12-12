package qzlife.net.zhrs.util.JWTUtile;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证和签发JWT操作类
 */
public class TokenMgr {

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(Constant.JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 传入salt生成加密key
     *
     * @param salt
     * @return
     */
    public static SecretKey generalKey(String salt) {
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(salt);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 创建jwt
     *
     * @param id        JWT的唯一标识，可以设置为一个不重复的值
     * @param issuer    jwt签发人
     * @param subject   JWT的主体，即它的所有人，这个是一个json格式的字符串，作为用户的唯一标志
     * @param ttlMillis 期时间
     * @param salt      盐
     * @return
     */
    public static String createJWT(String id, String issuer, String subject, long ttlMillis, String salt) {

        // 指定签名的时候使用的签名算法，也就是header部分，jwt已将这部份内容封装好了
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("uid", "123456");
//        claims.put("user_name", "admin");
//        claims.put("nick_name", "X-rapido");

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey secretKey = generalKey();
        if (StringUtils.isNoneBlank(salt)) {
            secretKey = generalKey(salt);
        }
        // 下面就是在为payload添加各种标准声明和私有声明了
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
//                .setClaims(claims)
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(id)
                // iat: jwt的签发时间
                .setIssuedAt(now)
                // issuer：jwt签发人
                .setIssuer(issuer)
                // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey);

        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }


    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) {

//        User user = new User("tingfeng", "bulingbuling", "1056856191");
//        String subject = new Gson().toJson(user);
//
//        try {
//            JwtUtil util = new JwtUtil();
//            String jwt = util.createJWT(Constant.JWT_ID, "Anson", subject, Constant.JWT_TTL);
//            System.out.println("JWT：" + jwt);
//
//            System.out.println("\n解密\n");
//
//            Claims c = util.parseJWT(jwt);
//            System.out.println(c.getId());
//            System.out.println(c.getIssuedAt());
//            System.out.println(c.getSubject());
//            System.out.println(c.getIssuer());
//            System.out.println(c.get("uid", String.class));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
