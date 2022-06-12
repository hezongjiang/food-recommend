package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Moment;

public interface MomentService {
    /**
     * 添加动态
     * @param moment
     * @return
     */
    int createMoment (Moment moment);
}
