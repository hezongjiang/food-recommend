package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.PersonFood;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.StoreFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: gyt
 * @Date: 2022/5/18 16:30
 * @Description:
 */
@Service
public class StoreFoodServiceImpl implements StoreFoodService {
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    PersonFoodMapper personFoodMapper;

    @Override
    public int InsertFood(String openId, Food food) {
        try {
//            先插入food表
            foodMapper.insert(food);
//             再插入person_food表
            PersonFood personFood=new PersonFood();
            personFood.setOpenId(openId);
            personFoodMapper.insert(personFood);
//          成功返回1
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int deleteByFoodId(String openId,String foodId) {
        try {
//            先删除Person_Food
            personFoodMapper.deleteByPersonIdAndFoodId(openId, foodId);
//            再删除Food表
            foodMapper.deleteByFoodId(foodId);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int updateByFoodId(Food food) {
//        仅需要改food表
        try {
            foodMapper.updateByPrimaryKeySelective(food);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }


    @Override
    public FoodResponse selectByPersonId(String openId, Food food,
                                  Integer page, Integer pageSize, FoodResponse foodResponse) {
        try {
            List<Food> foodList=new LinkedList<>();
            foodList=foodMapper.selectByPersonId(openId);
            foodResponse.setList(foodList);
            foodResponse.setTotal(foodList.size());
            foodResponse.setPages((int) Math.ceil(foodList.size()/pageSize));

            return foodResponse;
        }catch (Exception e){
            return null;
        }
    }
}
