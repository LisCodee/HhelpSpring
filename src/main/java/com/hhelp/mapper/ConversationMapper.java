package com.hhelp.mapper;

import com.hhelp.domain.Conversation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:15
 */
@Mapper
public interface ConversationMapper {

    @Select("select * from conversation where sender_id=#{sender_id} and receiver_id=#{receiver_id}")
    List<Conversation> findSenderMsg(Integer sender_id, Integer receiver_id);

    @Insert("insert into conversation(sender_id,receiver_id,content, time) values(#{sender_id}, #{receiver_id}, #{content}, #{time})")
    void addMsg(Conversation conversation);

    @Select("select receiver_id from conversation where sender_id=#{user_id}")
    List<Integer> getInquiryRecord(Integer user_id);
}
