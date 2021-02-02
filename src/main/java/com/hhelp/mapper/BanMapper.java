package com.hhelp.mapper;

import com.hhelp.domain.Ban;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:15
 */
@Mapper
public interface BanMapper {

    @Select("select * from ban where illness_name=#{illness_name}")
    List<Ban> get_avoids(String illness_name);

    @Select("select * from ban where id=#{id}")
    Ban getBanById(Integer id);
}
