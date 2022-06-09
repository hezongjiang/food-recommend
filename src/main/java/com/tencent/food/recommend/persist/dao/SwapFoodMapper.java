package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.SwapFood;
import com.tencent.food.recommend.response.SwapFoodDetailResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SwapFoodMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteById(String openid);

    int insert(SwapFood record);

    int insertSelective(SwapFood record);

    SwapFood selectByPrimaryKey(Integer id);

    List<SwapFoodDetailResponse> selectAll();

    SwapFoodDetailResponse selectOne(String swapId);

    int updateByPrimaryKeySelective(SwapFood record);

    int updateByPrimaryKeyWithBLOBs(SwapFood record);

    int updateByPrimaryKey(SwapFood record);
}