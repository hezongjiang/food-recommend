package com.tencent.food.recommend.service.impl;

import com.tencent.food.recommend.common.ResultData;
import com.tencent.food.recommend.common.enums.ReturnCode;
import com.tencent.food.recommend.persist.dao.PersonMapper;
import com.tencent.food.recommend.persist.model.Person;
import com.tencent.food.recommend.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public Person findPersonByOpenId(String openId) {
        Person person = new Person();

        person = personMapper.selectByOpenId(openId);

        if (person != null) {
            log.info("查询到openId为：" + openId + "的用户");
            person.setOpenId(openId);
            return person;
        } else {
            log.info("数据库没有openId为：" + openId + "的用户");
            return null;
        }
    }
}
