package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.PersonFoodMapper;
import com.tencent.food.recommend.service.PersonFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonFoodServiceImpl implements PersonFoodService {

    @Autowired
    PersonFoodMapper personFoodMapper;

    @Override
    public void deleteByOpenIdAndFoodId(String openId, String foodId) {
        personFoodMapper.deleteByOpenIdAndFoodId(openId, foodId);

    }
}
