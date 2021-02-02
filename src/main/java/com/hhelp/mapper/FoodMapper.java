package com.hhelp.mapper;

import com.hhelp.domain.Food;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:16
 */
@Mapper
public interface FoodMapper {

    @Select("select * from food where title like #{title}")
    List<Food> searchFoods(String title);

    @Select("select * from food limit #{li}")
    List<Food> getFoods(Integer li);

    @Select("select * from food where id=#{id}")
    Food getFoodById(Integer id);

    @Insert("insert into food(pub_user,title,introduction,content,pub_time) values (#{pub_user},#{title},#{introduction},#{content},#{pub_time})")
    void addFood(Food food);

    @Update("update food set favour_num=#{favour_num} where id=#{id}")
    void favourFood(Food food);

    @Update("update food set click_num=#{click_num} where id=#{id}")
    void clickFood(Food food);
}
