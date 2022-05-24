package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.dao.PersonStoreRemindMapper;
import com.tencent.food.recommend.persist.dao.StoreRemindMapper;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.persist.model.PersonStoreRemind;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


@Service
@Slf4j
public class StoreRemindServiceImpl implements StoreRemindService {

    @Autowired
    StoreRemindMapper storeRemindMapper;
    @Autowired
    PersonStoreRemindMapper personStoreRemindMapper;




    /**
     * @Author:NathanYu
     * @Description: 向数据库添加囤货提醒
     * @Date: 2022/5/18 17:45
     * @param storeRemind
     * @return ResultData
     */
    @Override
    public int addAndGetId(StoreRemind storeRemind) {
        //插入Store_remind表
        storeRemindMapper.insert(storeRemind);

        //获取加入数据库的id
        int storeRemindId = storeRemind.getId();
        log.info("插入Store_remind表,id为：" + storeRemindId);

        return storeRemindId;
    }


    /**
     * 查询当前用户下的所有的囤货提醒
     * @return
     */
    @Override
    public List<StoreRemind> findAllRemind(String openId) {

        List<StoreRemind> remindList = new LinkedList<>();
        remindList = storeRemindMapper.selectByPersonId(openId);

        //根据提醒时间升序排列
        remindList.sort(Comparator.comparing(StoreRemind :: getRemindDate));

        return remindList;
    }



}
