package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author:NathanYu
 * @Description: food服务类
 * @Date: 2022/5/24 22:03
 * @param

 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodMapper foodMapper;


    @Override
    public void deleteByFoodId(String foodId) {
        foodMapper.deleteByFoodId(foodId);
    }


    @Override
    public List<Food> selectByPersonId(String openId) {
        List<Food> foodList = foodMapper.selectByPersonId(openId);
        return foodList;
    }
}
