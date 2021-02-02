package com.hhelp.mapper;

import com.hhelp.domain.Food;
import com.hhelp.domain.Sport;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:17
 */
@Mapper
public interface SportMapper {

    @Select("select * from sport where title like #{title}")
    List<Sport> searchSports(@Param("title") String title);

    @Select("select * from sport limit #{li}")
    List<Sport> getSports(Integer li);

    @Select("select * from sport where id=#{id}")
    Sport getSportById(@Param("id") Integer id);

    @Insert("insert into sport (title,introduction,content,pub_user,pub_time) values (#{title},#{introduction},#{content},#{pub_user},#{pub_time})")
    void addSport(Sport sport);

    @Update("update sport set favour_num=#{favour_num} where id=#{id}")
    void favourSport(Sport sport);

    @Update("update sport set click_num=#{click_num} where id=#{id}")
    void clickSport(Sport sport);
}
