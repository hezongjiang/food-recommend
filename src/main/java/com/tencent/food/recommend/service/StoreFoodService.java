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
            String personId,
            Food food);

    public int deleteByFoodId(Integer personId,Long foodId);

    public int updateByFoodId(Food food);

    public FoodResponse selectByPersonId(Integer personId,
                                         Food food,
                                         Integer page,
                                         Integer pageSize,
                                         FoodResponse foodResponse);
}
