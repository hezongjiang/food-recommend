package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Food;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMapper {
    int deleteByPrimaryKey(Integer id);

    @Delete("delete from food where food_id=#{id}")
    int deleteByFoodId(Long id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    List<Food> selectByPersonId(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    int updateByFoodId(Food food);

}