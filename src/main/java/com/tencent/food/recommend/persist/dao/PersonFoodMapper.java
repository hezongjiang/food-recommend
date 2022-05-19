package com.tencent.food.recommend.persist.dao;

import com.tencent.food.recommend.persist.model.Food;
import com.tencent.food.recommend.persist.model.PersonFood;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonFoodMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 已通过测试
     * @param personId
     * @param foodId
     * @return
     */
    @Delete("delete from person_food where food_id=#{foodId} and open_id=#{personId}")
    int deleteByPersonIdAndFoodId(Integer personId,Long foodId);

    int insert(PersonFood record);

    int insertSelective(PersonFood record);

    List<Food> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonFood record);

    int updateByPrimaryKey(PersonFood record);
}