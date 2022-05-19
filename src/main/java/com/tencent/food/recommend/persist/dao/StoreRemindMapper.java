package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.StoreRemind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StoreRemindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreRemind record);

    int insertSelective(StoreRemind record);

    StoreRemind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreRemind record);

    int updateByPrimaryKey(StoreRemind record);

    List<StoreRemind> selectByPersonId(String id);

}