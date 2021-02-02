package com.hhelp.service;

import com.hhelp.domain.User;

import java.util.List;


/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 21:29
 */
public interface IUserService {

    /**
     * 注册用户
     * @param user 需要注册的用户，只需要传入email
     * @return
     * @throws Exception
     */
    boolean addUser(User user) throws Exception;

    /**
     * 获取验证码
     * @param user
     * @return
     */
    String getVercode(User user);

    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    User userLogin(User user) throws Exception;

    /**
     * 更新用户信息
     * @param user
     * @throws Exception
     */
    void editUser(User user) throws Exception;

    /**
     * 保存并发送验证码
     * @param user
     */
    void saveVercode(User user);

    /**
     * 用户注册
     * @param user
     * @throws Exception
     */
    void userRegister(User user) throws Exception;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    User findUserByEmail(User user) throws Exception;

    List<User> searchExpert(String keywords);

    User findUserById(Integer id);
}
