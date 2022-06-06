package com.tencent.food.recommend.service;

public interface PersonFoodService {

    /**
     * 根据openId和foodId删除PersonFood中的数据
     * @param openId
     * @param foodId
     * @return
     */
    void deleteByOpenIdAndFoodId(String openId,String foodId);
}
