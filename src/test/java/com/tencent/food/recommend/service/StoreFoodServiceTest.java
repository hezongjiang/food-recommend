package com.tencent.food.recommend.service;

import com.tencent.food.recommend.common.utils.IdGenerate;
import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.response.FoodResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: gyt
 * @Date: 2022/5/20 14:52
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class StoreFoodServiceTest {
    @Autowired
    StoreFoodService storeFoodService;
    @Test
    void insertFood() {
        String openId="123";
        Food food=new Food();
        food.setFoodName("黄瓜");
        food.setFoodId(IdGenerate.generate("FOOD_ID"));
        food.setQuantity(2);
        food.setCreateDate(System.currentTimeMillis());
        storeFoodService.InsertFood(openId,food);

    }

    @Test
    void deleteByFoodId() {
    }

    @Test
    void updateByFoodId() {
    }

    @Test
    void selectByPersonId() {
        String openId="123";
        Food food=new Food();
        food.setFoodName("黄");
        FoodResponse foodResponse=storeFoodService.selectByPersonId(openId,food,System.currentTimeMillis()-10000000,null,0,0,new FoodResponse());
        for (Food fo:foodResponse.getList()) {
            System.out.println(fo);
            System.out.println("要求开始时间："+(System.currentTimeMillis()-10000000)+",food创建时间："+fo.getCreateDate());
        }
    }
}