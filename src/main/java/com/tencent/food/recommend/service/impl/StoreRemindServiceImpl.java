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
    public List<StoreRemindResponse> findAllRemind(String openId) {

        StoreRemind nowStoreRemind = new StoreRemind();

        Long nowDate = System.currentTimeMillis();

        List<StoreRemind> remindList = new LinkedList<>();
        remindList = storeRemindMapper.selectByPersonId(openId);

        //创建新的返回表
        List<StoreRemindResponse> responsesList = new LinkedList<>();
        if (remindList != null ) {
            for (int i = 0; i < remindList.size(); i ++) {

                 StoreRemindResponse storeRemindResponse  = new StoreRemindResponse();
                 nowStoreRemind = remindList.get(i);
                 //时间转换

                 Long day = (nowStoreRemind.getRemindDate() - nowDate) / (1000 * 60 * 60 * 24);

                 storeRemindResponse.setDay(day);
                 storeRemindResponse.setRemark(nowStoreRemind.getRemarks());

                 responsesList.add(storeRemindResponse);
            }
        }
        //根据天数降序排列
        responsesList.sort(Comparator.comparing(StoreRemindResponse :: getDay));
        return responsesList;
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
