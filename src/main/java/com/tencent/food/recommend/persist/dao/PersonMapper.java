package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {
    int insert(Person record);

    int insertSelective(Person record);

    Person getById(int id);
}