package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
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
    @Autowired
    PersonMapper personMapper;
    @Override
    public int InsertFood(String openId, Food food) {
        try {
            PersonFood personFood=new PersonFood();
            personFood.setOpenId(openId);
            personFood.setFoodId(food.getFoodId());
//            先插入food表
            foodMapper.insert(food);
//             再插入person_food表
            try{
                personFoodMapper.insert(personFood);
            }catch (Exception e){
                //            A插入不了B先插入成功，要撤销B
                foodMapper.deleteByFoodId(food.getFoodId());
            }
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
            personFoodMapper.deleteByOpenIdAndFoodId(openId, foodId);
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
            foodMapper.updateByFoodId(food);
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
            try{
                foodResponse.setPages((int) Math.ceil(foodList.size()/pageSize));
            }catch (Exception e){
//                不需要分页
            }
            return foodResponse;
        }catch (Exception e){
            return null;
        }
    }

    public Person Authorize(String openId){
        Person person=new Person();
//        检验是否在数据库
        person=personMapper.selectByOpenId(openId);
        if (person!=null){
            person.setOpenId(openId);
            return person;
        }else {
            return null;
        }
    }
}
