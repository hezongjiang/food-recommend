package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Food;

import java.util.List;

public interface FoodService {

    /**
     * 根据foodid删除food
     * @param foodId
     */
    void deleteByFoodId(String foodId);


    /**
     * 根据openid查询food
     * @param openId
     * @return
     */
    List<Food> selectByPersonId(String openId);
}
