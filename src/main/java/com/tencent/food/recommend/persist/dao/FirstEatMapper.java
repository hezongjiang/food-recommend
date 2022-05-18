package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.FirstEat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FirstEatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FirstEat record);

    int insertSelective(FirstEat record);

    FirstEat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FirstEat record);

    int updateByPrimaryKey(FirstEat record);
}