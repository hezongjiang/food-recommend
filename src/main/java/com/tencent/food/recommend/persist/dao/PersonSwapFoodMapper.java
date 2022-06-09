package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.PersonSwapFood;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonSwapFoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonSwapFood record);

    int insertSelective(PersonSwapFood record);

    PersonSwapFood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonSwapFood record);

    int updateByPrimaryKey(PersonSwapFood record);
}