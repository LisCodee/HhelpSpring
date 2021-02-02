package com.hhelp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.anno.PassToken;
import com.hhelp.domain.User;
import com.hhelp.service.IUserService;
import com.hhelp.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-14 21:51
 */

@RestController
@PassToken
public class LoginController {

    @Autowired
    private IUserService userService;

    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    /**
     * 获取验证码接口
     * @param email
     */
    @RequestMapping("/getVercode")
    @PassToken
    public void getVercode(String email){
        User user = new User();
        user.setEmail(email);
        userService.saveVercode(user);
        logger.info(user.toString());
        try {
            String verCode = user.getVercode();
            Util.sendMail(verCode, email);
            logger.info("发送验证码：" + verCode + "到：" + email);
        }catch (Exception e){
            logger.error("getVercode:" + e.getMessage());
            throw new RuntimeException("501");
        }
    }

    /**
     * 用户注册接口
     * @param email
     * @param password
     * @param vercode
     */
    @RequestMapping("/register")
    @PassToken
    public void register(String email, String password, String vercode){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setVercode(vercode);
        try{
            userService.userRegister(user);
            logger.info("用户注册：" + user);
        }catch (Exception e){
            logger.error("register:" + e.getMessage());
            throw new RuntimeException("502" + e.getMessage());
        }
    }

    /**
     * 登录接口，返回token
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login")
    @PassToken
    public JSONObject login(String email, String password){
        JSONObject jsonObject = new JSONObject();
        User user = new User(email, password);
        try {
            User u = userService.userLogin(user);
            String token = u.getToken(u);
            int user_id = u.getId();
            jsonObject.put("user_id", user_id);
            jsonObject.put("token", token);
            logger.info("登录：" + u + "token:" + token);
        }catch (Exception e){
            logger.error("login:"  + e.getMessage());
            throw new RuntimeException("503" + "用户名或密码错误");
        }
        return jsonObject.isEmpty()?null:jsonObject;
    }
    /**
     * 编辑个人信息
     * @param user_id
     * @param sex
     * @param nickname
     * @param head_img
     * @param age
     */
    @PostMapping("/edit_personal")
    @LoginToken
    public void editPersonal(int user_id, String sex, String nickname, String head_img, int age){
        User user = new User(user_id, sex, nickname, head_img, age);
        try {
            userService.editUser(user);
            logger.info("编辑用户信息" + user);
        }catch (Exception e){
            logger.error("editPersonal:" + e.getMessage());
            throw new RuntimeException("503" + e.getMessage());
        }
    }

    /**
     * 获取个人信息
     * @param user_id
     */
    @GetMapping("/get_personal")
    @PassToken
    public JSONObject getPersonal(int user_id){
        JSONObject jsonObject = new JSONObject();
//        User user = new User();
        try {
//            user = userService.findUserById(user_id);
            jsonObject.put("data", userService.findUserById(user_id));
            logger.info("获取用户信息" + jsonObject);
        }catch (Exception e){
            logger.error("getPersonal:" + e.getMessage());
            throw new RuntimeException("503" + e.getMessage());
        }
        return jsonObject.isEmpty()? null : jsonObject;
    }

}
