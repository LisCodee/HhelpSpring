package com.hhelp.mapper;

import com.hhelp.domain.Agenda;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:15
 */
@Mapper
public interface AgendaMapper {

    @Insert("insert into agenda(user_id,title,start_time,end_time,repeated) values(#{user_id},#{title},#{start_time},#{end_time},#{repeated})")
    void addAgenda(Agenda agenda);

    @Select("select * from agenda where user_id=#{user_id} and effective=0")
    List<Agenda> getAgendaByUserId(Integer user_id);

    @Update("update agenda set effective=1 where user_id=#{user_id} and id=#{agenda_id}")
    void deleteAgenda(Integer user_id, Integer agenda_id);
}
