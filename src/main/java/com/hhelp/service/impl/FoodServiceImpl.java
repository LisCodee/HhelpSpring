package com.hhelp.service.impl;

import com.hhelp.domain.Food;
import com.hhelp.mapper.FoodMapper;
import com.hhelp.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 18:08
 */

@Service
public class FoodServiceImpl implements IFoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<Food> searchFoods(String title) {
        return foodMapper.searchFoods("%" + title + "%");
    }

    @Override
    public List<Food> getFoods(Integer li) {
        li = li == 0 ? 65535 : li;
        return foodMapper.getFoods(li);
    }

    @Override
    public Food getFood(Integer id) {
        return foodMapper.getFoodById(id);
    }

    @Override
    public void addFood(Food food) {
        foodMapper.addFood(food);
    }

    @Override
    public void favourFood(Food food) {
        foodMapper.favourFood(food);
    }

    @Override
    public void clickFood(Food food) {
        foodMapper.clickFood(food);
    }
}

