package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.FoodMapper;
import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.response.FoodResponse;
import com.tencent.food.recommend.service.FirstEatService;
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
            //获取现在的时间
            Long nowDate = System.currentTimeMillis();
            //新建一个链表来存储比较之后的元素
            List<Food> newFoodList = new LinkedList<>();

            List<Food> foodList = new LinkedList<>();
            foodList=foodMapper.selectByPersonId(openId);
            //当食物链表中不为空
            if (foodList != null) {
                for (int i = 0; i < foodList.size(); i ++) {
                    Food nowfood = foodList.get(i);
                    //过期日期大于或者等于就加入新链表

                    if (nowfood.getRemindDate() >= nowDate) {

                        newFoodList.add(nowfood);
                    }
                }
            }
            //根据提醒时间升序排列
            newFoodList.sort(Comparator.comparing(Food :: getRemindDate));
            foodResponse.setList(newFoodList);
            foodResponse.setTotal(newFoodList.size());
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
