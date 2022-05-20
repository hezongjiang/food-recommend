package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.response.FoodRecommendResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 食物推荐
 */
@RestController
public class RecommendController {

    @GetMapping()
    public ResultData<FoodRecommendResponse> test() {
        FoodRecommendResponse foodRecommendResponse = new FoodRecommendResponse();
        foodRecommendResponse.setFoods(Arrays.asList("可乐", "啤酒🍺", "苹果🍎"));
        return ResultData.success(foodRecommendResponse);
    }
}
