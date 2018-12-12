package qzlife.net.zhrs.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroSalt {

//    private String shiroSalt;
//
//    public ShiroSalt(String source, String salt) {
//        int hashIteration = 3;
//        Md5Hash md5Hash = new Md5Hash(source, salt, hashIteration);
////        new SimpleHash("md5", source,salt,hashIteration);
//        this.shiroSalt = md5Hash.toString();
//    }

    /**
     * 创建盐值
     *
     * @param source 需要加密的参数
                * @param salt   盐，随机数或者用户名
                * @return
     */
        public static String shiroEncrypt(String source, String salt) {
            int hashIteration = 3;
            Md5Hash md5Hash = new Md5Hash(source, salt, hashIteration);
            return md5Hash.toString();
    }
}
