package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: gyt
 * @Date: 2022/5/20 9:52
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class PersonMapperTest {
    @Autowired
    PersonMapper personMapper;

    @Test
    void selectByOpenId() {
        String openId="123";
        Person person=personMapper.selectByOpenId(openId);
        System.out.println(person+" openId:"+person.getOpenId());
    }
}