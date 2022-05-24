package com.tencent.food.recommend.service;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.persist.model.PersonStoreRemind;

/**
 * @Author:NathanYu
 * @Description: 用户和囤货提醒联系Service
 * @Date: 2022/5/24 19:53
 * @param

 */
public interface PersonStoreRemindService {

    /**
     * 向数据库中加入用户和囤货提醒联系
     * @param openId 用户唯一标识符
     * @param storeRemindId 囤货提醒对应的id
     */
    void insertPersonStoreRemind (String openId, Integer storeRemindId);
}
