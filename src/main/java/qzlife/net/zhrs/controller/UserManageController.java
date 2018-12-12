package qzlife.net.zhrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qzlife.net.zhrs.pojo.JSONResultPojo;
import qzlife.net.zhrs.service.UserManageService;

/**
 * app用户管理controller
 */
@RestController
@RequestMapping("/api")
public class UserManageController {

    @Autowired
    private UserManageService userManageService;

    /**
     * 手机号注册
     *
     * @param phone
     * @param pwd
     * @return
     */
    @RequestMapping("/regist/phone")
    public JSONResultPojo phoneRegistered(@RequestParam(value = "phone", required = true) String phone,
                                          @RequestParam(value = "pwd", required = true) String pwd) {
        return userManageService.phoneRegistered(phone, pwd);
    }

    /**
     * 手机号+密码登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @RequestMapping("/login/phone")
    public JSONResultPojo phoneLogin(@RequestParam(value = "phone", required = true) String phone,
                                     @RequestParam(value = "pwd", required = true) String pwd) {
        return userManageService.phoneLogin(phone, pwd);

    }
}
