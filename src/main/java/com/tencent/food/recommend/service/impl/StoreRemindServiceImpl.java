package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.PersonStoreRemindMapper;
import com.tencent.food.recommend.persist.dao.StoreRemindMapper;
import com.tencent.food.recommend.persist.model.PersonStoreRemind;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreRemindServiceImpl implements StoreRemindService {

    @Autowired
    StoreRemindMapper storeRemindMapper;
    @Autowired
    PersonStoreRemindMapper personStoreRemindMapper;
    StoreRemind storeRemind;

    PersonStoreRemind personStoreRemind;


    /**
     * @Author:NathanYu
     * @Description: 向数据库添加囤货提醒
     * @Date: 2022/5/18 17:45
     * @param storeRemind
     * @return ResultData
     */
    @Override
    public int add(String openId,StoreRemind storeRemind) {
        //插入Store_remind表
        int result = storeRemindMapper.insert(storeRemind);
        //person_remind表
        int storeRemindId = storeRemind.getId();
        personStoreRemind = new PersonStoreRemind();
        personStoreRemind.setOpenId(openId);
        personStoreRemind.setStoreRemindId(storeRemindId);
        personStoreRemindMapper.insertSelective(personStoreRemind);
        return result;
    }

    @Override
    public StoreRemindResponse findRemindById(int id) {
        storeRemind = new StoreRemind();
        storeRemind = storeRemindMapper.selectByPrimaryKey(id);
        StoreRemindResponse storeRemindResponse = new StoreRemindResponse();
        Long day = (storeRemind.getRemindDate()-storeRemind.getCreatedDate()) / (1000 * 60 * 60 * 24);
        storeRemindResponse.setDay(day);
        storeRemindResponse.setRemark(storeRemind.getRemarks());

        return storeRemindResponse;
    }


}
