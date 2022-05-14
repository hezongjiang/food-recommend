package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Person;

public interface PersonMapper {
    int insert(Person record);

    int insertSelective(Person record);
}