package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.User;
import com.hhelp.service.IConversationService;
import com.hhelp.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:15
 */

@RestController
public class ExpertController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IConversationService conversationService;

    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    @RequestMapping("/search_expert")
    public JSONArray searchExpert(String keywords){
        JSONArray jsonArray = new JSONArray();
        try {
            List<User> expert = userService.searchExpert(keywords);
            jsonArray.addAll(expert);
            logger.info(jsonArray.toJSONString());
        }catch (Exception e){
            logger.error("searchExpert error" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonArray.isEmpty() ? null : jsonArray;
    }

    @RequestMapping("/get_inquiry_record")
    @LoginToken
    public JSONArray getInquiryRecord(Integer user_id){
        List<Integer> receiver_ids = conversationService.getInquiryRecord(user_id); //获取到都给谁发了消息
        HashSet<User> set = new HashSet();
        for (int id : receiver_ids){
            User user = userService.findUserById(id);
            set.add(user);
        }
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.addAll(set);
            logger.info(jsonArray.toJSONString());
        }catch (Exception e){
            logger.error("getInquiryRecord error" + e.getMessage());
        }

        return jsonArray.isEmpty() ? null : jsonArray;
    }
}
