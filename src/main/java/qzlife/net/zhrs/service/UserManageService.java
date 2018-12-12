package qzlife.net.zhrs.service;

import qzlife.net.zhrs.pojo.JSONResultPojo;

public interface UserManageService {

    /**
     * 手机+密码注册
     *
     * @param phone
     * @param pwd
     * @return
     */
    public JSONResultPojo phoneRegistered(String phone, String pwd);

    /**
     * 手机+密码登录
     *
     * @param phone
     * @param password
     * @return
     */
    public JSONResultPojo phoneLogin(String phone, String password);
}
