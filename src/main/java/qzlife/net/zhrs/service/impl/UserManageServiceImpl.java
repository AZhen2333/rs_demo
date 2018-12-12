package qzlife.net.zhrs.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import qzlife.net.zhrs.entity.dto.AppUserDto;
import qzlife.net.zhrs.entity.po.AppTokenPo;
import qzlife.net.zhrs.entity.po.PhoneAuth;
import qzlife.net.zhrs.entity.po.UserPo;
import qzlife.net.zhrs.mapper.AppTokenMapper;
import qzlife.net.zhrs.mapper.PhoneAuthMapper;
import qzlife.net.zhrs.mapper.UserMapper;
import qzlife.net.zhrs.pojo.JSONResultPojo;
import qzlife.net.zhrs.service.UserManageService;
import qzlife.net.zhrs.util.JWTUtile.Constant;
import qzlife.net.zhrs.util.JWTUtile.TokenMgr;
import qzlife.net.zhrs.util.RandomUtil;
import qzlife.net.zhrs.util.RedisUtil;
import qzlife.net.zhrs.util.ShiroSalt;

import java.time.LocalDateTime;

/**
 * @program: zhrs
 * @description: 用户管理类
 * @author: Czz
 * @create: 2018-12-09 11:21
 **/
@Service
public class UserManageServiceImpl implements UserManageService {

    ThreadLocal<AppUserDto> threadLocal = new InheritableThreadLocal<AppUserDto>();

    @Autowired
    private PhoneAuthMapper phoneAuthMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AppTokenMapper appTokenMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 昵称
     */
    @Value("${user.nickName}")
    private String nickName;
    /**
     * 头像路径
     */
    @Value("${user.headUrl}")
    private String headUrl;
    /**
     * 性别：0-女，1-男,2-保密
     */
    @Value("${user.sex}")
    private String sex;

    /**
     * 手机注册
     *
     * @param phone
     * @param pwd
     * @return
     */
    @Override
    public JSONResultPojo phoneRegistered(String phone, String pwd) {
        PhoneAuth phoneAuth = new PhoneAuth();
        phoneAuth.setPhone(phone);
        PhoneAuth user = phoneAuthMapper.selectOne(phoneAuth);
        if (user == null) {
            // 随机产生10位use rId
            String userId = RandomUtil.getLongRandomStr(10);
            // 生成盐
            String salt = RandomUtil.getRandomStr(20);
            // 密码加密
            String pw = ShiroSalt.shiroEncrypt(pwd, salt);
            phoneAuth.setUserId(userId);
            phoneAuth.setPassword(pw);
            phoneAuth.setSalt(salt);
            phoneAuth.setGmtCreate(LocalDateTime.now());
            phoneAuth.setGmtModified(LocalDateTime.now());

            // 新增手机注册记录
            int insert = phoneAuthMapper.insert(phoneAuth);
            if (insert == 1) {
                UserPo userPo = new UserPo();
                userPo.setUserId(userId);
                userPo.setPhone(phone);
                userPo.setNickName("昵称");
                userPo.setHeadUrl(headUrl);
                userPo.setSex(sex);
                userPo.setGmtCreate(LocalDateTime.now());
                userPo.setGmtModified(LocalDateTime.now());
                // 新增用户基础信息
                insert = userMapper.insert(userPo);
                if (insert == 1) {
                    return JSONResultPojo.ok();
                }
            }
        } else {
            return JSONResultPojo.build(1001, "手机号已注册", null);
        }
        return JSONResultPojo.errorMsg("注册失败");
    }


    /**
     * 手机号+密码登录
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public JSONResultPojo phoneLogin(String phone, String password) {
        // 校验参数，待完善
        PhoneAuth phoneAuthPo = new PhoneAuth.Builder().phone(phone).builder();
        PhoneAuth phoneAuth = phoneAuthMapper.selectOne(phoneAuthPo);
        if (phoneAuth != null) {
            String salt = phoneAuth.getSalt();
            String saltPwd = ShiroSalt.shiroEncrypt(password, salt);
            if (saltPwd.equals(phoneAuth.getPassword())) {
                String userId = phoneAuth.getUserId();
                AppUserDto appUser = new AppUserDto.Builder().userId(userId).loginTime(System.currentTimeMillis()).builser();
                // 生成盐
                String tokenSalt = RandomUtil.getRandomStr(20);
                // 生成token
                String token = TokenMgr.createJWT(Constant.JWT_ID, userId, JSONObject.toJSONString(appUser), Constant.JWT_TTL, tokenSalt);
                // 保存token到数据库
                AppTokenPo appTokenPo = new AppTokenPo();
                appTokenPo.setUserId(userId);
                // 查找token表
                AppTokenPo tokenPo = appTokenMapper.selectOne(appTokenPo);
                appTokenPo.setToken(token);
                appTokenPo.setGmtModified(LocalDateTime.now());
                int effect = 0;
                if (tokenPo != null) {
                    appTokenPo.setId(tokenPo.getId());
                    effect = appTokenMapper.updateByPrimaryKey(appTokenPo);
                } else {
                    effect = appTokenMapper.insert(appTokenPo);
                }
                if (effect == 1) {
                    // 保存登录用户信息
                    threadLocal.set(appUser);
                    // 缓存token盐
                    redisUtil.set("salt_" + userId, salt, Constant.JWT_TTL);
                    // 返回token
                   return JSONResultPojo.ok(token);
                }

            }
        }
        return JSONResultPojo.errorMsg("登录失败");
    }


}
