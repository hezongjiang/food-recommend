package com.tencent.food.recommend.service.Impl;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.persist.dao.StoreRemindMapper;
import com.tencent.food.recommend.persist.model.StoreRemind;
import com.tencent.food.recommend.response.StoreRemindResponse;
import com.tencent.food.recommend.service.StoreRemindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoreRemindServiceImpl implements StoreRemindService {

    @Autowired
    StoreRemindMapper storeRemindMapper;
    StoreRemind storeRemind;


    /**
     * @Author:NathanYu
     * @Description: 向数据库添加囤货提醒
     * @Date: 2022/5/18 17:45
     * @param storeRemind
     * @return ResultData
     */
    @Override
    public int add(StoreRemind storeRemind) {
        int result = storeRemindMapper.insertSelective(storeRemind);
        return result;
    }

    @Override
    public StoreRemindResponse findRemindById(int id) {
        storeRemind = new StoreRemind();
        storeRemind = storeRemindMapper.selectByPrimaryKey(id);
        StoreRemindResponse storeRemindResponse = new StoreRemindResponse();
        Long day = (storeRemind.getRemindDate()-storeRemind.getCreatedDate()) / (1000 * 60 * 60 * 24);
        storeRemindResponse.setDay(day);
        System.out.println(day);
        storeRemindResponse.setRemark(storeRemind.getRemarks());

        return storeRemindResponse;
    }


}
