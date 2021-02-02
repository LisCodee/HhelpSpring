package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.Food;
import com.hhelp.domain.Sport;
import com.hhelp.service.IFoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 18:09
 */

@RestController
public class FoodController {

    @Autowired
    private IFoodService foodService;

    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);


    @RequestMapping("search_foods")
    public JSONArray searchFoods(String title){
        JSONArray jsonArray = new JSONArray();
        try {
            List<Food> foods = foodService.searchFoods(title);
            jsonArray.addAll(foods);
            logger.info(jsonArray.toJSONString());
        }catch (Exception e){
            logger.error("searchFoods Error!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonArray.isEmpty() ? null: jsonArray;
    }

    @RequestMapping("/get_foods")
    public JSONArray getFoods(Integer limit){
        JSONArray jsonArray = new JSONArray();
        try {
            List<Food> foods = foodService.getFoods(limit);
            jsonArray.addAll(foods);
            logger.info(jsonArray.toJSONString());
        }catch (Exception e){
            logger.error("getFoods ERROR!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonArray.isEmpty() ? null : jsonArray;
    }

    @RequestMapping("/get_food")
    public JSONObject getFood(Integer food_id){
        JSONObject jsonObject = new JSONObject();
        try {
            Food food = foodService.getFood(food_id);
            int num = food.getClick_num() + 1;
            food.setClick_num(num);
            foodService.clickFood(food);                //点击量+1
            jsonObject.put("data", food);
            logger.info(jsonObject.toJSONString());
        }catch (Exception e){
            logger.error("getFood Error!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonObject.isEmpty() ? null : jsonObject;
    }

    @RequestMapping("/send_food")
    @LoginToken
    public void addFood(@RequestBody Food food){
        try {
            food.setPub_time(new Date());
            foodService.addFood(food);
            logger.info(food.getTitle() + "已发布");
        }catch (Exception e){
            logger.error("addFood Error!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @RequestMapping("/favour_food")        //点赞接口
    @LoginToken
    public JSONObject favourSport(int id){
        try{
            Food food = foodService.getFood(id);
            int num = food.getFavour_num()+1;
            food.setFavour_num(num);
            foodService.favourFood(food);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("favour_num", num);
            logger.info(food.toString());
            return jsonObject;
        }catch (Exception e){
            logger.info(e.getMessage());
            throw new RuntimeException("500 位置错误");
        }
    }

}
