package com.tencent.food.recommend.service;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;

import java.util.List;

public interface StoreRemindService {

    /**
     * 添加信息
     * @param storeRemind
     * @return
     */
    int add (StoreRemind storeRemind);

    /**
     * 查询所有提醒信息，封装成 StoreRemindResponse 对象
     * @return
     */
    StoreRemindResponse findRemindById (int id);
}
