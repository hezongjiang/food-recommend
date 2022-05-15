package com.tencent.food.recommend.controller;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodRecommendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 食物推荐
 */
@RestController
public class RecommendController {

    @Autowired
    private PersonMapper personMapper;

    @GetMapping()
    public ResultData<FoodRecommendResponse> test() {
        FoodRecommendResponse foodRecommendResponse = new FoodRecommendResponse();
        foodRecommendResponse.setFoods(Arrays.asList("白菜", "萝卜"));
        return ResultData.success(foodRecommendResponse);
    }
}
