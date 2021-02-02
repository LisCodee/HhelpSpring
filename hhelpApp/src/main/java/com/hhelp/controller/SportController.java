package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.Sport;
import com.hhelp.service.ISportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 16:50
 */

@RestController
public class SportController {

    @Autowired
    private ISportService sportService;
    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    @RequestMapping("/search_sports")
    public JSONArray searchSports(String title){
        JSONArray jsonArray = new JSONArray();
        try {
            List<Sport> sports = sportService.searchSports(title);
            jsonArray.addAll(sports);
            logger.info("搜索到：" + jsonArray);
        }catch (Exception e){
            logger.error("searchSports error!" + e.getMessage());
            throw new RuntimeException("搜索出错" + e.getMessage());
        }
        return jsonArray.isEmpty()? null : jsonArray;
    }

    @RequestMapping("/get_sports")
    public JSONArray getSports(Integer limit){
        JSONArray jsonArray = new JSONArray();
        try{
            List<Sport> sports = sportService.getSports(limit);
            jsonArray.addAll(sports);
            logger.info("搜索到：" + jsonArray);
        }catch (Exception e){
            logger.error("getSports Error!!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonArray.isEmpty()? null : jsonArray;
    }

    @RequestMapping("/get_sport")
    public JSONObject getSportById(Integer sport_id){
        JSONObject json = new JSONObject();
        try {
            Sport sport = sportService.getSportById(sport_id);
            json.put("data", sport);
            int num = sport.getClick_num()+1;
            sport.setClick_num(num);
            sportService.clickSport(sport);         // 点击量+1
            logger.info(sport.toString());
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return json.isEmpty()? null : json;
    }

    @PostMapping(value = "/send_sport",consumes = "application/json")
    @LoginToken
    public void sendSport(@RequestBody Sport sport){
        try{
            sport.setPub_time(new Date());
            logger.info(sport.toString());
            sportService.addSport(sport);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @RequestMapping("/favour_sport")        //点赞接口
    @LoginToken
    public JSONObject favourSport(int id){
        try{
            Sport sport = sportService.getSportById(id);
            int num = sport.getFavour_num()+1;
            sport.setFavour_num(num);
            sportService.favourSport(sport);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("favour_num", num);
            logger.info(sport.toString());
            return jsonObject;
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new RuntimeException("500 位置错误");
        }
    }
}
