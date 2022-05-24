package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.common.utils.IdGenerate;
import com.tencent.food.recommend.persist.model.Food;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        food.setFoodName("黄瓜");
        food.setFoodId(IdGenerate.generate("FOOD_ID"));
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
        String foodId="0";
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
        food.setRemindDate(System.currentTimeMillis());
        int status=foodMapper.updateByFoodId(food);
        System.out.println("状态:"+status);
    }

    @Test
    void testSelectByPersonId(){
        String openId="0";
        List<Food> foodList=foodMapper.selectByPersonId(openId);
        for (Food food:foodList) {
            System.out.println(food);
        }

    }

    @Test
    void selectByPersonIdAndFoodName() {
        String foodName="瓜";
        String openId="123";
        List<Food> foodList=foodMapper.selectByPersonIdAndFoodName(openId,foodName);
        for (Food food:foodList) {
            System.out.println(food);
        }
    }

    @Test
    void selectByPersonIdAndDateScope(){
        String openId="123";
        List<Food> foodList=foodMapper.selectByPersonIdAndDateScope
                (openId,System.currentTimeMillis()-100000,System.currentTimeMillis()+100000);
        for (Food food:foodList) {
            System.out.println(food);
        }
    }
}