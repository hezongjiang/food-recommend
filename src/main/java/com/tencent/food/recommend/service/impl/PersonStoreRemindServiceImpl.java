package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.persist.dao.PersonStoreRemindMapper;
import com.tencent.food.recommend.persist.model.PersonStoreRemind;
import com.tencent.food.recommend.service.PersonStoreRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonStoreRemindServiceImpl implements PersonStoreRemindService {

    @Autowired
    PersonStoreRemindMapper personStoreRemindMapperr;



    @Override
    public void insertPersonStoreRemind(String openId, Integer storeRemindId) {
        PersonStoreRemind personStoreRemind = new PersonStoreRemind();

        personStoreRemind.setOpenId(openId);
        personStoreRemind.setStoreRemindId(storeRemindId);

        personStoreRemindMapperr.insertSelective(personStoreRemind);

    }
}
