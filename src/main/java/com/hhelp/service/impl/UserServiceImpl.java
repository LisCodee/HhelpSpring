package com.hhelp.service.impl;

import com.hhelp.domain.User;
import com.hhelp.mapper.UserMapper;
import com.hhelp.service.IUserService;
import com.hhelp.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;


/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 21:29
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) throws Exception {
        try {
            userMapper.addUser(user);
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public String getVercode(User user) {
        return userMapper.getVercode(user);
    }

    /**
     * 用户点击发送验证码
     * @param user
     */
    @Override
    public void saveVercode(User user) {
        String vercode = Util.getVerCode();
        user.setVercode(vercode);
        userMapper.saveVercode(user);
    }

    @Override
    public void userRegister(User user) throws Exception {
        String vercode = userMapper.getVercode(user);
        if (user.getVercode().length() == 0 || !user.getVercode().equals(vercode))
            throw new Exception("验证码为空或错误");
        userMapper.userRegister(user);
    }

    @Override
    @Transient
    public User userLogin(User user) throws Exception {
        User u = userMapper.userLogin(user);
        if (u == null)
            throw new Exception("用户名或密码错误");
        u.setLast_login(new Date());
        userMapper.flushLoginTime(u);
        return u;
    }

    @Override
    public void editUser(User user) throws Exception {
        userMapper.editUser(user);
    }

    @Override
    public User findUserByEmail(User user) throws Exception {
        return userMapper.findUserByEmail(user);
    }

    @Override
    public List<User> searchExpert(String keywords) {
        return userMapper.searchExpert(keywords);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }
}
