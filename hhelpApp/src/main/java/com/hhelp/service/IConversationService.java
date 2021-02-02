package com.hhelp.service;

import com.hhelp.domain.Conversation;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:25
 */
public interface IConversationService {

    List<Conversation> findSenderMsg(Integer sender_id, Integer receiver_id);

    void addMsg(Conversation conversation);

    List<Integer> getInquiryRecord(Integer user_id);
}
