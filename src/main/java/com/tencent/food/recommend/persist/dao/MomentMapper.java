package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Moment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MomentMapper {

    int insertSelective(Moment moment);

    List<Moment> selectAll ();

    Moment selectByMomentId (String momentId);
}
