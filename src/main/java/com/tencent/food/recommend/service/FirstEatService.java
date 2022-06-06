package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;

/**
 *
 */
public interface FirstEatService {


    /**
     * 删除food
     * @param openId
     * @param foodId
     * @return
     */
    void deleteByFoodId(String openId,String foodId);


    /**
     * 根openid返回食物
     * @param openId
     * @param food
     * @param page
     * @param pageSize
     * @return
     */
    FoodResponse selectByPersonId(String openId,
                                         Food food,
                                         Integer page,
                                         Integer pageSize);


}
