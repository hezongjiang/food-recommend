package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}