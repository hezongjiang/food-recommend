package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.FirstEatService;
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
public class FirstEatServiceImpl implements FirstEatService{
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    PersonFoodMapper personFoodMapper;
    @Autowired
    PersonMapper personMapper;


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

    @Override
    public Person Authorize(String openId) {
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
