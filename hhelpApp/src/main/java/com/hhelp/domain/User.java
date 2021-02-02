package com.hhelp.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 16:59
 */

@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private String sex;
    private int age;
    private String head_img;
    private int is_doctor;
    private Date last_login;
    private String token;
    private String vercode;
    private String real_name;
    private String keywords;

    public User(int id, String sex, String nickname, String head_img, int age){
        this.id = id;
        this.sex = sex;
        this.nickname = nickname;
        this.head_img = head_img;
        this.age = age;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(){
        super();
    }

    public String getToken(User user){
        String token = "";
        //生成token
        token = JWT.create().withAudience(user.getEmail()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
