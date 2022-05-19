package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.PersonFood;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: gyt
 * @Date: 2022/5/19 11:10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PersonFoodMapperTest {
    @Autowired
    PersonFoodMapper personFoodMapper;

    @Test
    void inset(){
        PersonFood personFood=new PersonFood();
        personFood.setOpenId("0");
        personFood.setFoodId("0L");
        personFoodMapper.insert(personFood);
    }
    @Test
    void deleteByPersonIdAndFoodId() {
        Integer personId=0;
        Long foodId=0L;
        personFoodMapper.deleteByPersonIdAndFoodId(personId,foodId);
    }


}