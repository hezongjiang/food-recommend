package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.StoreRemind;

import java.util.List;

public interface StoreRemindService {

    /**
     * 添加信息
     * @param storeRemind
     * @return
     */
    int addAndGetId(StoreRemind storeRemind);

    /**
     * 查询所有提醒信息，封装成 StoreRemindResponse 对象
     * @return
     */

    List<StoreRemind> findAllRemind(String openId);

    void deleteById (Integer id);


}
