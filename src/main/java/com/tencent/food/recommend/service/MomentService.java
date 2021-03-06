package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Moment;
import com.tencent.food.recommend.response.MomentDetailResponse;
import com.tencent.food.recommend.response.MomentListResponse;

public interface MomentService {
    /**
     * 添加动态
     */
    int createMoment (Moment moment);

    MomentListResponse selectAll(Integer page, Integer pageSize);

    MomentDetailResponse selectByMomentId (String momentId);
}