package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.FirstEatService;
import com.tencent.food.recommend.service.FoodService;
import com.tencent.food.recommend.service.PersonFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: gyt
 * @Date: 2022/5/18 16:30
 * @Description:
 */
@Service
@Slf4j
public class FirstEatServiceImpl implements FirstEatService{



    @Autowired
    FoodService foodService;
    @Autowired
    PersonFoodService personFoodService;


    @Override
    public void deleteByFoodId(String openId,String foodId) {
        //先删除personFood表
        personFoodService.deleteByOpenIdAndFoodId(openId, foodId);
        //再删除Food表
        foodService.deleteByFoodId(foodId);

    }



    @Override
    public FoodResponse selectByPersonId(String openId, Food food,
                                  Integer page, Integer pageSize ) {

            FoodResponse foodResponse = new FoodResponse();

            List<Food> foodList = new LinkedList<>();

            foodList = foodService.selectByPersonId(openId);

            //根据提醒时间升序排列
            foodList.sort(Comparator.comparing(Food :: getRemindDate));
            foodResponse.setList(foodList);
            foodResponse.setTotal(foodList.size());
            //分页
            try{
                foodResponse.setPages((int) Math.ceil(foodList.size()/pageSize));
            }catch (Exception e){
//                不需要分页
            }
            return foodResponse;

    }



}
