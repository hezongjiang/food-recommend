package com.tencent.food.recommend.service;

import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

public interface StoreRemindService {

    /**
     * 添加信息
     * @param storeRemind
     * @return
     */
    int add (String openId,StoreRemind storeRemind);

    /**
     * 查询所有提醒信息，封装成 StoreRemindResponse 对象
     * @return
     */

    List<StoreRemind> findAllRemind(String openId);

    /**
     * 检查该opinid是不是在数据库中
     * @param openId
     * @return
     */
    public Person Authorize(String openId);
}
