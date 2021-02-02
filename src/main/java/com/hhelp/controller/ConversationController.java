package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.Conversation;
import com.hhelp.service.IConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:29
 */

@RestController
@LoginToken
public class ConversationController {

    @Autowired
    private IConversationService conversationService;
    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    @RequestMapping("/get_dialogue")
    @LoginToken
    public JSONObject getDialogue(Integer sender_id, Integer receiver_id){
        JSONObject jsonObject = new JSONObject();
        try {
            List<Conversation> sender_msgs = conversationService.findSenderMsg(sender_id, receiver_id);  //查询自己发送的消息
            List<Conversation> receiver_msgs = conversationService.findSenderMsg(receiver_id, sender_id); //查询自己接受的消息
            JSONArray sender_msg = new JSONArray();
            JSONArray receiver_msg = new JSONArray();
            sender_msg.addAll(sender_msgs);
            receiver_msg.addAll(receiver_msgs);
            jsonObject.put("sender_msg", sender_msg);
            jsonObject.put("receiver_msg", receiver_msg);
            logger.info(jsonObject.toJSONString());
        }catch (Exception e){
            logger.error("getDialogue error" + e.getMessage());
            throw new  RuntimeException(e.getMessage());
        }
        return jsonObject.isEmpty() ? null : jsonObject;
    }

    @PostMapping("/send_msg")
    @LoginToken
    public void sendMsg(@RequestBody Conversation conversation){
        try {
            conversation.setTime(new Date());
            logger.info(conversation.toString());
            conversationService.addMsg(conversation);
        }catch (Exception e){
            logger.error("sendMsg error" + e.getMessage());
        }
    }
}
