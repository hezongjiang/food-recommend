package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.dao.PersonStoreRemindMapper;
import com.tencent.food.recommend.persist.dao.StoreRemindMapper;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.PersonStoreRemind;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class StoreRemindServiceImpl implements StoreRemindService {

    @Autowired
    StoreRemindMapper storeRemindMapper;
    @Autowired
    PersonStoreRemindMapper personStoreRemindMapper;
    @Autowired
    PersonMapper personMapper;

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


    /**
     * 查询当前用户下的所有的囤货提醒
     * @return
     */
    @Override
    public List<StoreRemind> findAllRemind(String openId) {
        List<StoreRemind> remindList = new LinkedList<>();
        remindList = storeRemindMapper.selectByPersonId(openId);
        return remindList;
    }

    @Override
    public Person Authorize(String openId) {
        Person person=new Person();
//        检验是否在数据库
        person=personMapper.selectByOpenId(openId);
        if (person!=null){
            person.setOpenId(openId);
            return person;
        }else {
            return null;
        }
    }


}
