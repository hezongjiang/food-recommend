package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.StoreRemind;

public interface StoreRemindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreRemind record);

    int insertSelective(StoreRemind record);

    StoreRemind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreRemind record);

    int updateByPrimaryKey(StoreRemind record);
}