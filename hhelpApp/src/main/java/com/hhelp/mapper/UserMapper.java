package com.hhelp.mapper;

import com.hhelp.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:17
 */
@Mapper
public interface UserMapper {

    @Select("select vercode from user where email=#{email}")
    String getVercode(User user);

    @Insert("insert into user (email) values(#{email})")
    void addUser(User user);

    @Update("update user set password=#{password}, vercode='' where email=#{email}")
    void userRegister(User user);

    @Select("select * from user where email=#{email} and password=#{password}")
    User userLogin(User user);

    @Update("update user set sex=#{sex},nickname=#{nickname},head_img=#{head_img},age=#{age} where id=#{id} or email=#{email}")
    void editUser(User user);

    @Update("insert into user(email,vercode) values(#{email},#{vercode})")
    void saveVercode(User user);

    @Select("select * from user where email=#{email}")
    User findUserByEmail(User user);

    @Update("update user set last_login=#{last_login} where email=#{email} or id=#{id}")
    void flushLoginTime(User user);

    @Select("select * from user where keywords like #{keywords}")
    List<User> searchExpert(String keywords);

    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);


}
