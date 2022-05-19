package com.tencent.food.recommend.service;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.persist.model.StoreRemind;

public interface StoreRemindService {

    /**
     * 添加信息
     * @param storeRemind
     * @return
     */
    ResultData add (StoreRemind storeRemind);
}
