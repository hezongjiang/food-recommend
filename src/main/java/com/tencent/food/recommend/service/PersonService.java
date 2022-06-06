package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Person;

/**
 * @Author:NathanYu
 * @Description: PersonService接口
 * @Date: 2022/5/24 19:38
 * @param

 */
public interface PersonService {

    /**
     * 根据openId返回Person
     * @param openId
     * @return
     */
    Person findPersonByOpenId(String openId);
}
