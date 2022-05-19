package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.PersonFood;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.StoreFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int InsertFood(String personId, Food food) {
        try {
//            先插入food表
            foodMapper.insert(food);
//             再插入person_food表
            PersonFood personFood=new PersonFood();
            personFood.setOpenId(personId);
            personFoodMapper.insert(personFood);
//          成功返回1
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int deleteByFoodId(Integer personId,Long foodId) {
        try {
//            先删除Person_Food
            personFoodMapper.deleteByPersonIdAndFoodId(personId, foodId);
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
    public FoodResponse selectByPersonId(Integer personId, Food food,
                                  Integer page, Integer pageSize, FoodResponse foodResponse) {
        try {
//先从person_food找
//            PersonFoodExample personFoodExample=new PersonFoodExample();
//            List<PersonFood> personFoodList=personFoodMapper.selectByExample(personFoodExample);
////            再从food找
//            FoodExample foodExample=new FoodExample();
//            FoodExample.Criteria criteria = foodExample.createCriteria();
//            增加查询条件
//            criteria.a
//            List<Food> foodList=foodMapper.selectByExample(foodExample);

//            foodResponse.setList(foodList);
//            foodResponse.setTotal(foodList.size());
//            foodResponse.setPages((int) Math.ceil(foodList.size()/pageSize));

            return foodResponse;
        }catch (Exception e){
            return null;
        }
    }
}
