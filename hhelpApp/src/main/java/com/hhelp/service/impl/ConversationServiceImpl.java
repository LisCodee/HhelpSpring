package com.hhelp.service.impl;

import com.hhelp.domain.Conversation;
import com.hhelp.mapper.ConversationMapper;
import com.hhelp.service.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:28
 */

@Service
public class ConversationServiceImpl implements IConversationService {

    @Autowired
    private ConversationMapper conversationMapper;

    @Override
    public List<Conversation> findSenderMsg(Integer sender_id, Integer receiver_id) {
        return conversationMapper.findSenderMsg(sender_id, receiver_id);
    }

    @Override
    public void addMsg(Conversation conversation) {
        conversationMapper.addMsg(conversation);
    }

    @Override
    public List<Integer> getInquiryRecord(Integer user_id) {
        return conversationMapper.getInquiryRecord(user_id);
    }
}
