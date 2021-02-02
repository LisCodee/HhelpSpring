package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.PassToken;
import com.hhelp.domain.Ban;
import com.hhelp.service.IBanService;
import com.hhelp.service.impl.BanServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 16:21
 */
@RestController
@PassToken
public class BanController {

    @Autowired
    private IBanService banService;

    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    @RequestMapping("/get_avoids")
    public JSONArray getAvoids(String illness_name){
        try {
            JSONArray avoids = banService.get_avoids(illness_name);
            logger.info(avoids.toJSONString());
            return avoids;
        }catch (Exception e){
            throw new RuntimeException("出现问题");
        }
    }

    @RequestMapping("/get_avoid_detail")
    public JSONObject getAvoidDetail(Integer ban_id){
        JSONObject jsonObject = new JSONObject();
        try {
            Ban avoid_detail = banService.get_avoid_detail(ban_id);
            jsonObject.put("data", avoid_detail);
            logger.info(jsonObject.toJSONString());
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonObject.isEmpty()? null : jsonObject;
    }
}
