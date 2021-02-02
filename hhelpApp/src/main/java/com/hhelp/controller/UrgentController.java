package com.hhelp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhelp.anno.LoginToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 21:43
 */

@RestController
public class UrgentController {

    private static Map<String, String> urgent = new HashMap<>();

    @RequestMapping("/get_urgent")
    public JSONObject getUrgent(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(urgent);
        return jsonObject;
    }

    @PostMapping("/send_urgent")
    @LoginToken
    public void sendUrgent(String lo, String la, String name){
        urgent.put("lo", lo);
        urgent.put("la", la);
        urgent.put("name", name);
    }
}
