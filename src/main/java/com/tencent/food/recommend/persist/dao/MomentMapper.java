package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Moment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MomentMapper {

    int insertSelective(Moment moment);
}
