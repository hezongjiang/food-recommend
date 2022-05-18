package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.PersonFood;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonFoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonFood record);

    int insertSelective(PersonFood record);

    PersonFood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonFood record);

    int updateByPrimaryKey(PersonFood record);
}