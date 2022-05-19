package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Food;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: gyt
 * @Date: 2022/5/18 19:36
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class FoodMapperTest {
    @Autowired
    FoodMapper foodMapper;

    @Test
    void testDeleteByPrimaryKey() {
//        Food food=new Food();
//        food.setFoodName("大蒜");
//        food.setFoodId(0L);
//        food.setQuantity(2);
//        food.setCreateDate(System.currentTimeMillis());
        int status=foodMapper.deleteByPrimaryKey(1);
        System.out.println("状态:"+status);
    }

    @Test
    void testInsert() {
        Food food=new Food();
        food.setFoodName("大蒜");
        food.setFoodId("3L");
        food.setQuantity(2);
        food.setCreateDate(System.currentTimeMillis());
        int status=foodMapper.insert(food);
        System.out.println("状态:"+status);
    }

    @Test
    void testInsertSelective() {

    }

    @Test
    void testSelectByPrimaryKey() {

    }

    @Test
    void testUpdateByPrimaryKeySelective() {

    }

    @Test
    void testUpdateByPrimaryKey() {

    }

    @Test
    void testDeleteByFoodId() {
        Long foodId=0L;
        int status=foodMapper.deleteByFoodId(foodId);
        System.out.println("状态:"+status);
    }

    /**
     * update不通过 应该是字段问题
     */
    @Test
    void testUpdateByFoodId() {
        Food food=new Food();
        food.setFoodName("大蒜");
        food.setFoodId("3L");
        food.setQuantity(4);
        int status=foodMapper.updateByFoodId(food);
        System.out.println("状态:"+status);
    }

    @Test
    void testSelectByPersonId(){
        Integer openId=0;
        List<Food> foodList=foodMapper.selectByPersonId(openId);
        for (Food food:foodList) {
            System.out.println(food);
        }

    }
}