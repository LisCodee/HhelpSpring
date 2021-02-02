package com.hhelp.service;

import com.hhelp.domain.Food;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 18:08
 */
public interface IFoodService {

    List<Food> searchFoods(String title);

    List<Food> getFoods(Integer li);

    Food getFood(Integer id);

    void addFood(Food food);

    void favourFood(Food food);

    void clickFood(Food food);
}
