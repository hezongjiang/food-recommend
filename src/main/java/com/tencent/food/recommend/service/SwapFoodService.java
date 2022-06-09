package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.SwapFood;
import com.tencent.food.recommend.response.SwapFoodListResponse;

/**
 * @Author: gyt
 * @Date: 2022/6/8 14:56
 * @Description:
 */
public interface SwapFoodService {

    public SwapFoodListResponse selectAll(SwapFoodListResponse swapFoodListResponse);

    public Boolean create(SwapFood swapFood,String openid);

    public Person Authorize(String openId);
}
