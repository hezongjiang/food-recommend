package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.MomentMapper;
import com.tencent.food.recommend.persist.model.Moment;
import com.tencent.food.recommend.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    MomentMapper momentMapper;


    @Override
    public int createMoment(Moment moment) {
        momentMapper.insertSelective(moment);
        return 1;
    }
}
