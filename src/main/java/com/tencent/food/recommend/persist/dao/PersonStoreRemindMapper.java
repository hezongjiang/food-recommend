package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.PersonStoreRemind;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonStoreRemindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonStoreRemind record);

    int insertSelective(PersonStoreRemind record);

    PersonStoreRemind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonStoreRemind record);

    int updateByPrimaryKey(PersonStoreRemind record);
}