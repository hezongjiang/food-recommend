package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.response.FoodResponse;

/**
 * @Author: gyt
 * @Date: 2022/5/18 16:29
 * @Description:
 */
public interface StoreFoodService {
    public int InsertFood(
            String openId,
            Food food);

    public int deleteByFoodId(String openId,String foodId);

    public int updateByFoodId(Food food);

    public FoodResponse selectByPersonId(String openId,
                                         Food food,
                                         Integer page,
                                         Integer pageSize,
                                         FoodResponse foodResponse);
}
